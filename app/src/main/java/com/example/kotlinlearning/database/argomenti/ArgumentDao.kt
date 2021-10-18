package com.example.kotlinlearning.database.argomenti

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArgumentDao {

    @Update
    suspend fun update(argomento:Argument)

    @Query("SELECT * FROM argument ORDER BY indice ASC")
      fun getAllArgument(): LiveData<List<Argument>>

    @Query("SELECT * FROM argument ORDER BY indice ASC")
     fun getArgument(): List<Argument>
}