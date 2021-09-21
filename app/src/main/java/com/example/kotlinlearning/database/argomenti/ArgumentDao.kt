package com.example.kotlinlearning.database.argomenti

import androidx.room.*

@Dao
interface ArgumentDao {

    @Update
    fun update(argomento:Argument)

    @Query("SELECT * FROM argument ORDER BY `index` ASC")
    fun getAllArgument(): List<Argument>
}