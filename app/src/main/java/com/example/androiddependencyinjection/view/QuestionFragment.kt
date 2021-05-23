package com.example.androiddependencyinjection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddependencyinjection.R
import com.example.androiddependencyinjection.adapter.QuestionItemAdapter
import com.example.androiddependencyinjection.viewModel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    private val questionItemAdapter = QuestionItemAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        viewModel.onLoad()

        recycler_question.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = questionItemAdapter
            addItemDecoration(DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL))
        }

        // Swipe To refresh
        swipeToRefresh.setOnRefreshListener {
            viewModel.onLoad()
            swipeToRefresh.isRefreshing = false
        }

        observe(viewModel)
    }

    private fun observe(viewModel: QuestionViewModel) {
        viewModel.listOfQuestions.observe(viewLifecycleOwner, Observer {
            it?.let { listOfQuestions ->
                questionItemAdapter.onUpdateList(listOfQuestions)
            }
            errorMsg.visibility = View.GONE
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            it?.let {
                errorMsg.text = it
            }
            recycler_question.visibility = View.GONE
        })

    }
}