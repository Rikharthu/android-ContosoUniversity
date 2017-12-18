package com.example.rikharthu.contosouniversity.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.rikharthu.contosouniversity.App

class ContosoViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseDetailsViewModel::class.java))
            return CourseDetailsViewModel(App.get(context).getContosoDatabase()) as T
        throw IllegalArgumentException("Unknown class name")
    }
}