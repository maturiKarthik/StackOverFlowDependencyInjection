package com.example.androiddependencyinjection.network

import com.example.androiddependencyinjection.model.StackOverFlowData
import io.reactivex.Single
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
            stackOverFlowService = retrofit.create(StackOverFlowService::class.java)
            return RetroFitHelper()
        }
    }

    fun getAllQuestions(): Single<StackOverFlowData>? {
        return stackOverFlowService?.getAllQuestions()
    }

    fun getAllAnswers(questionId: String): Single<StackOverFlowData>? {
        return stackOverFlowService?.getAnswersForQuestion(questionId)
    }
}