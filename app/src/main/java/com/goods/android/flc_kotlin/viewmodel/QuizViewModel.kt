package com.goods.android.flc_kotlin.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.goods.android.flc_kotlin.database.DatabaseRepository
import com.goods.android.flc_kotlin.model.Answer
import com.goods.android.flc_kotlin.model.Question

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DatabaseRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val questionsLiveData: LiveData<List<Question>>
    lateinit var questions: List<Question>
    var mCurrentQuestion: MediatorLiveData<Question> = MediatorLiveData()
    var mCurrentAnswerChoices: MediatorLiveData<List<Answer>> = MediatorLiveData()
    var mCurrentCount: MutableLiveData<Int> = MutableLiveData(0)
    var mScore = 0

    init {
        DatabaseRepository.initialize(application.applicationContext)
        repository = DatabaseRepository.get()

        questionsLiveData = repository.getQuestions("medium")

        mCurrentQuestion = Transformations.map(questionsLiveData){
            it.get(mCurrentCount.value!!)
        } as MediatorLiveData<Question>



        mCurrentAnswerChoices = Transformations.switchMap(mCurrentQuestion){
            it?.id?.let { it1 -> repository.getAnswers(it1)
            }
        } as MediatorLiveData<List<Answer>>

       // populateCurrentQuestionAndAnswer()

       // nextQuestion()
    }

    fun validateAnswer(userSelection : ArrayList<String>) : Boolean{
        var mCorrectAnswer = mCurrentAnswerChoices.value!!.filter {
            it.isCorrect == 1
        }
        return if (mCorrectAnswer.size != userSelection.size){
            false
        } else {
            for (selection in userSelection){
                for (answer in mCorrectAnswer){
                    if (selection == answer.text){
                        continue
                    } else {
                        false
                    }
                }
            }
            true

        }
    }

    fun nextQuestion(){
        mCurrentCount.postValue(mCurrentCount.value?.plus(1))

    }

    fun increaseScore() : Int{
        mScore += 10
        return mScore
    }

    fun populateCurrentQuestionAndAnswer(){
        mCurrentQuestion.addSource(questionsLiveData){
            mCurrentCount.value = 0
        }
        mCurrentQuestion.addSource(mCurrentCount) {
            mCurrentQuestion.value = mCurrentCount.value?.let { it1 ->
                questionsLiveData.value?.get(
                    it1
                )
            }
        }

        mCurrentAnswerChoices.addSource(mCurrentQuestion){
            mCurrentAnswerChoices.value = mCurrentQuestion.value?.id?.let { it1 ->
                repository.getAnswers(
                    it1
                ).value
            }
        }

    }


//    fun nextQuestion(){
//
//        mCurrentCount.value = mCurrentCount.value?.plus(1)
//        val count = mCurrentCount?.value
//
//        mCurrentQuestion.value = questions[count!!]
//        val id = mCurrentQuestion?.value?.id
//
//        mCurrentAnswerChoices = id?.let { repository.getAnswers(it) }!!
//    }
}