package com.example.kotlinlearning.database.teoria

import androidx.room.Dao
import androidx.room.Query
import com.example.kotlinlearning.database.argomenti.Argument

@Dao
interface TeoriaDao {

    @Query("SELECT testo_teoria FROM teoria WHERE argomento = :argomento")
    fun getTheoryfromArgument(argomento:String):String
}