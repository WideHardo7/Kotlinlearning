package com.example.kotlinlearning.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.repository.ArgumentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompletamentoQuizViewModel(application: Application): AndroidViewModel(application), NumeroDomande {
    val repository:ArgumentRepository

    init {
        val argumentdao= AppDatabase.getInstance(application).argumentDao()
        repository= ArgumentRepository(argumentdao)
    }
    fun insertArgument(argomento:Argument){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArgument(argomento)
        }
    }

    fun getArgumentbyNameArgument(nomeargomento:String):LiveData<Argument>{
        return repository.getArgument(nomeargomento)
    }
}