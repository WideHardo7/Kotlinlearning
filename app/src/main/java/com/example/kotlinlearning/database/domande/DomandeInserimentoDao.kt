package com.example.kotlinlearning.database.domande

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.kotlinlearning.database.argomenti.Argument

@Dao
interface DomandeInserimentoDao {

    @Query("SELECT* FROM domande_inserimento")
     suspend fun getQuestion(): List<DomandeInserimento>
}