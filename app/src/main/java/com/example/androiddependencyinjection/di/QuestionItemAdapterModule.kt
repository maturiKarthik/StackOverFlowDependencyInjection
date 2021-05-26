package com.example.androiddependencyinjection.di

import com.example.androiddependencyinjection.adapter.QuestionItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class QuestionItemAdapterModule {

    @Provides
    fun provideQuestionItemAdapter(): QuestionItemAdapter{
        return QuestionItemAdapter(listOf())
    }
}