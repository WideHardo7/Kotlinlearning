package com.example.kotlinlearning.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.database.teoria.TeoriaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeoriaRepository(private val teoriaDao:TeoriaDao) {




    suspend fun getTheory():List<Teoria> {
        val teoria:List<Teoria>

        withContext(Dispatchers.IO) {
            teoria = teoriaDao.getTheory()
        }
        Log.i("TeoriaRepository","Dentro getTheory in repository")


            return teoria

    }


    suspend fun getTheoryfromArgument(argomento:String):Teoria{
        val teoria:Teoria
        withContext(Dispatchers.IO){
            teoria= teoriaDao.getTheoryfromArgument(argomento)
        }

         return teoria
    }




}