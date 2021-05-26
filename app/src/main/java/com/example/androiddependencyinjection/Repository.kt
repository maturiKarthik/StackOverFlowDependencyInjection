package com.example.androiddependencyinjection

import com.example.androiddependencyinjection.model.StackOverFlowData
import com.example.androiddependencyinjection.network.RetroFitHelper
import io.reactivex.Single
import retrofit2.Call

object Repository {
    private val retroFitHelper = RetroFitHelper.invoke()

    fun getAllQuestion(): Single<StackOverFlowData>? {
        return retroFitHelper.getAllQuestions()
    }

    fun getAllAnswersForQuestion(questionId: String): Single<StackOverFlowData>? {
        return retroFitHelper.getAllAnswers(questionId)
    }
}