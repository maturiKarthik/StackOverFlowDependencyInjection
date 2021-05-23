package com.example.androiddependencyinjection

import com.example.androiddependencyinjection.model.StackOverFlowData
import com.example.androiddependencyinjection.retrofit.RetroFitHelper
import retrofit2.Call

object Repository {
    private val retroFitHelper = RetroFitHelper.invoke()

    fun getAllQuestion(): Call<StackOverFlowData>? {
        return retroFitHelper.getAllQuestions()
    }

    fun getAllAnswersForQuestion(questionId: String): Call<StackOverFlowData>? {
        return retroFitHelper.getAllAnswers(questionId)
    }
}