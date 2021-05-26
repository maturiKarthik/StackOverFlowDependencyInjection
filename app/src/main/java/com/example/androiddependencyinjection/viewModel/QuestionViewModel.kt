package com.example.androiddependencyinjection.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddependencyinjection.Repository
import com.example.androiddependencyinjection.model.Questions
import com.example.androiddependencyinjection.model.StackOverFlowData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class QuestionViewModel @Inject constructor (application: Application) : BaseViewModel(application) {

    var listOfQuestions = MutableLiveData<List<Questions>>()
    var errorMsg = MutableLiveData<String>()
    private val disposable = CompositeDisposable() // Rx Java

    fun onLoad() {
        Repository.getAllQuestion()?.let {
            disposable.add(
                it.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<StackOverFlowData>() {
                        override fun onSuccess(t: StackOverFlowData) {
                            listOfQuestions.value = t.items
                        }

                        override fun onError(e: Throwable) {
                            errorMsg.value = e.toString()
                        }
                    })
            )
        }


    }

}