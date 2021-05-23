package com.example.androiddependencyinjection.retrofit

import com.example.androiddependencyinjection.model.StackOverFlowData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface StackOverFlowService {
    @GET("/2.2/questions?page=10000&order=desc&sort=activity&site=stackoverflow")
    fun getAllQuestions(): Call<StackOverFlowData>

    @GET("/2.2/questions/{id}/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun getAnswersForQuestion(@Path("id") questionId: String): Call<StackOverFlowData>
}