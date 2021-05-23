package com.example.androiddependencyinjection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddependencyinjection.R
import com.example.androiddependencyinjection.databinding.QuestionItemBinding
import com.example.androiddependencyinjection.helperUtil.OnViewClick
import com.example.androiddependencyinjection.model.Questions
import com.example.androiddependencyinjection.view.QuestionFragmentDirections
import kotlinx.android.synthetic.main.question_item.view.*


class QuestionItemAdapter(private var listOfQuestions: List<Questions>) :
    RecyclerView.Adapter<QuestionItemAdapter.ViewHolder>(), OnViewClick {

    fun onUpdateList(newListOfQuestion: List<Questions>) {
        this.listOfQuestions = newListOfQuestion
        notifyDataSetChanged()
    }

    class ViewHolder(val view: QuestionItemBinding) : RecyclerView.ViewHolder(view.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val questionItemBinding = DataBindingUtil.inflate<QuestionItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.question_item,
            viewGroup,
            false
        )
        return ViewHolder(questionItemBinding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.view.question = listOfQuestions[position]
        viewHolder.view.listener = this
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listOfQuestions.size

    override fun clickOnView(view: View) {
        val questionId = view.questionId.text
        Toast.makeText(view.context, "text- $questionId", Toast.LENGTH_SHORT).show()

        val action = QuestionFragmentDirections.actionQuestionFragmentToQuestionDetailFragment()
        action.questionId = (questionId.toString()).toLong()

        Navigation.findNavController(view).navigate(action)
    }
}

