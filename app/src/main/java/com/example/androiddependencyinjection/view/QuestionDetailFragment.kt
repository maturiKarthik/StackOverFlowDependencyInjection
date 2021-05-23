package com.example.androiddependencyinjection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androiddependencyinjection.R
import com.example.androiddependencyinjection.viewModel.QuestionDetailViewModel
import kotlinx.android.synthetic.main.fragment_question_detail.*


class QuestionDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionId = arguments?.getLong("questionId")

        val qDetailViewModel = ViewModelProviders.of(this).get(QuestionDetailViewModel::class.java)
        qDetailViewModel.retrieveAnswers(questionId.toString())

        observer(qDetailViewModel)
    }

    private fun observer(qDetailViewModel: QuestionDetailViewModel) {
        qDetailViewModel.listOfAnswers.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                textView.text = "No answers Found"
            } else {
                textView.text = it[0].body
            }
        })
    }
}