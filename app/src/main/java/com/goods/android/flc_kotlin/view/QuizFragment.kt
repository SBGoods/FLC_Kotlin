package com.goods.android.flc_kotlin.view

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goods.android.flc_kotlin.R
import com.goods.android.flc_kotlin.databinding.FragmentQuizBinding
import com.goods.android.flc_kotlin.model.Answer
import com.goods.android.flc_kotlin.model.Question
import com.goods.android.flc_kotlin.viewmodel.QuizViewModel

class QuizFragment : Fragment(){
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : QuizViewModel

    private lateinit var mQuestion : Question
    private lateinit var mAnswers : List<Answer>
    private var mAnswered = false


    private var mButtons : ArrayList<CompoundButton> = ArrayList()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        setObservers()

       binding.buttonSubmitMcAnswer.setOnClickListener {
           if (mAnswered){
               nextQuestion()
           } else {
               submitAnswer()
           }
       }

        return binding.root
    }


    fun populateUI(){
        val bmp = BitmapFactory.decodeByteArray(mQuestion.imageBlob, 0, mQuestion.imageBlob!!.size)

        binding.textViewMCQuestion.text = mQuestion.text
        binding.MCQImage.setImageBitmap(bmp)
        mButtons.clear()
        binding.buttonSubmitMcAnswer.setText(R.string.submit_answer)

        if (mQuestion.numOfCorrectAnswers == 1){ //single answer
            for (answer in mAnswers){
                var mRadioButton = RadioButton(context)
                mRadioButton.text = answer.text
                mButtons.add(mRadioButton)
                binding.radioGroup.addView(mRadioButton)
            }
        } else {
            for (answer in mAnswers){
                var mCheckBox = CheckBox(context)
                mCheckBox.text = answer.text
                mButtons.add(mCheckBox)
                binding.radioGroup.addView(mCheckBox)
            }
        }

    }

    fun nextQuestion(){
        mAnswered = false
        if (viewModel.mCurrentCount.value!! < 10){
            viewModel.nextQuestion()

        } else {
            //navigate to result screen.
        }
    }

    fun getSelectedAnswers() : ArrayList<String>{
        var selectedAnswers = ArrayList<String>()
        for (button in mButtons){
            if (button.isChecked) {
                selectedAnswers.add(button.text.toString())
            }
        }
        return selectedAnswers
    }

    fun populateResultScreen(isCorrect : Boolean){
        if (isCorrect){
            binding.description.background = context?.getDrawable(R.drawable.bg_text_green)
            binding.description.text = resources.getText(R.string.incorrectDescription).toString() + " " + mQuestion.description
        } else {
            binding.description.background = context?.getDrawable(R.drawable.bg_text_red)
            binding.description.text = resources.getText(R.string.correctDescription).toString() + " " + mQuestion.description
        }

        binding.buttonSubmitMcAnswer.setText(R.string.next)
    }

    fun submitAnswer(){
        val isCorrect = viewModel.validateAnswer(getSelectedAnswers())
        populateResultScreen(isCorrect)
        mAnswered = true
    }

    fun setObservers(){
        val questionObserver : Observer<Question> = Observer {
            Log.d("Hello", it.toString())
            mQuestion = it
        }
        val answerObserver : Observer<List<Answer>> = Observer {
            Log.d("Hello", it.toString())
            mAnswers = it
            populateUI()
        }

        viewModel.mCurrentQuestion.observe(viewLifecycleOwner, questionObserver)
        viewModel.mCurrentAnswerChoices.observe(viewLifecycleOwner, answerObserver)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}