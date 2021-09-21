package com.example.kotlinlearning.database.domande

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinlearning.database.argomenti.Argument

@Dao
interface DomandeMultipleDao {
    @Query("SELECT* FROM domande_multiple WHERE argomento = :argomento"  )
    fun getQuestionfromArgument(argomento:String):LiveData<List<DomandeMultiple>>
}