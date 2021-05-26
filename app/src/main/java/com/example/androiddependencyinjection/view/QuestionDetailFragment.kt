package com.example.androiddependencyinjection.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.androiddependencyinjection.R
import com.example.androiddependencyinjection.viewModel.QuestionDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_question_detail.*

@AndroidEntryPoint
class QuestionDetailFragment : Fragment() {

    private val qDetailViewModel: QuestionDetailViewModel by viewModels()

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

        qDetailViewModel.retrieveAnswers(questionId.toString())
        observer(qDetailViewModel)
    }

    private fun observer(qDetailViewModel: QuestionDetailViewModel) {
        qDetailViewModel.listOfAnswers.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                textView.text = "No answers Found"
            } else {

                textView.text = Html.fromHtml(it[0].body).toString()
            }
        })
    }
}