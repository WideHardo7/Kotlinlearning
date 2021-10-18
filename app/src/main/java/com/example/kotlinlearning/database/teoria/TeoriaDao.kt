package com.example.kotlinlearning.database.teoria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.kotlinlearning.database.argomenti.Argument

@Dao
interface TeoriaDao {


    @Query("SELECT * FROM teoria")
    suspend fun getTheory():List<Teoria>

    @Transaction
    @Query("SELECT * FROM teoria WHERE cod_argomento= :argomento")
    suspend fun getTheoryfromArgument(argomento:String):Teoria
}