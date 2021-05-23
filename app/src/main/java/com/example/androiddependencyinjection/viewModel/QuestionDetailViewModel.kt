package com.example.androiddependencyinjection.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.androiddependencyinjection.Repository
import com.example.androiddependencyinjection.model.Questions
import com.example.androiddependencyinjection.model.StackOverFlowData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionDetailViewModel(application: Application) : BaseViewModel(application) {

    var listOfAnswers = MutableLiveData<List<Questions>>()
    var errorMsg = MutableLiveData<String>()

    fun retrieveAnswers(questionId: String) {
        Repository.getAllAnswersForQuestion(questionId)?.let {
            it.enqueue(object : Callback<StackOverFlowData> {
                override fun onFailure(call: Call<StackOverFlowData>, t: Throwable) {
                    errorMsg.value = t.toString()
                }

                override fun onResponse(
                    call: Call<StackOverFlowData>,
                    response: Response<StackOverFlowData>
                ) {
                    listOfAnswers.value = response.body()?.let {
                        it.items
                    }

                }
            })

        }
    }

}