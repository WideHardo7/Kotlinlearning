package com.example.kotlinlearning.database.argomenti

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArgumentDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE )
    suspend fun insertArgument(argomento:Argument)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoreArgument(vararg argomenti: Argument)


    @Query("SELECT * FROM argument ORDER BY indice ASC")
      fun getAllArgument(): LiveData<List<Argument>>

    @Query("SELECT * FROM argument ORDER BY indice ASC")
     suspend fun getAllArgumentwithCouroutine(): List<Argument>

    @Query("SELECT * FROM argument WHERE cod_argomento = :argomento")
      fun getArgument(argomento:String):LiveData<Argument>
}