package com.example.androiddependencyinjection.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.androiddependencyinjection.Repository
import com.example.androiddependencyinjection.model.Questions
import com.example.androiddependencyinjection.model.StackOverFlowData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class QuestionDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    var listOfAnswers = MutableLiveData<List<Questions>>()
    var errorMsg = MutableLiveData<String>()
    private val disposable = CompositeDisposable()

    fun retrieveAnswers(questionId: String) {
        Repository.getAllAnswersForQuestion(questionId)?.let {
            disposable.add(
                it.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(
                        object : DisposableSingleObserver<StackOverFlowData>() {
                            override fun onSuccess(t: StackOverFlowData) {
                                listOfAnswers.value = t.items
                            }

                            override fun onError(e: Throwable) {
                                errorMsg.value = e.toString()
                            }
                        })
            )
        }

    }

}