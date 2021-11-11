package com.example.kotlinlearning.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.repository.ArgumentRepository
import com.example.kotlinlearning.util.NumeroDomande

class AchievementViewModel( application: Application): AndroidViewModel(application),
    NumeroDomande {

    var listOfargument: LiveData<List<Argument>> = MutableLiveData<List<Argument>>()
    val repository:ArgumentRepository
    override val ndomandetot: Int
        get() = super.ndomandetot


    init{
        val argomentoDao= AppDatabase.getInstance(application).argumentDao()
        repository= ArgumentRepository(argomentoDao)
        listOfargument= repository.readallargument()


    }
    fun getAllArgument(): LiveData<List<Argument>> {
        return listOfargument
    }
}