package com.example.androiddependencyinjection.network

import com.example.androiddependencyinjection.model.StackOverFlowData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface StackOverFlowService {
    @GET("/2.2/questions?page=10000&order=desc&sort=activity&site=stackoverflow")
    fun getAllQuestions(): Single<StackOverFlowData>

    @GET("/2.2/questions/{id}/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun getAnswersForQuestion(@Path("id") questionId: String): Single<StackOverFlowData>
}