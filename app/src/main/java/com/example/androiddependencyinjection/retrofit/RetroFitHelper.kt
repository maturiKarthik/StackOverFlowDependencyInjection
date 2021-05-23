package com.example.androiddependencyinjection.retrofit

import com.example.androiddependencyinjection.model.StackOverFlowData
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitHelper {

    companion object {
        private var instance: RetroFitHelper? = null
        private val LOCK = Job()
        private const val BASE_URL = "https://api.stackexchange.com/"
        private var stackOverFlowService: StackOverFlowService? = null

        operator fun invoke() = instance ?: synchronized(LOCK) {
            instance ?: build().also {
                instance = it
            }
        }

        private fun build(): RetroFitHelper {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            stackOverFlowService = retrofit.create(StackOverFlowService::class.java)
            return RetroFitHelper()
        }
    }

    fun getAllQuestions(): Call<StackOverFlowData>? {
        return stackOverFlowService?.getAllQuestions()
    }

    fun getAllAnswers(questionId: String): Call<StackOverFlowData>? {
        return stackOverFlowService?.getAnswersForQuestion(questionId)
    }
}