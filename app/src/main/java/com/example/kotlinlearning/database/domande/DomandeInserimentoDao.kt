package com.example.kotlinlearning.database.domande

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.kotlinlearning.database.argomenti.Argument

@Dao
interface DomandeInserimentoDao {

    @Query("SELECT* FROM domande_inserimento WHERE cod_argomento = :argomento")
    fun getQuestionfromArgument(argomento: String): LiveData<List<DomandeInserimento>>
}