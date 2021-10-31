package com.example.kotlinlearning.database.domande

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "domande_inserimento")
data class DomandeInserimento(

    @PrimaryKey(autoGenerate = true)
    val id_domanda: Int,
    val testo_domanda: String,
    val cod_argomento: String,
    val risposta_giusta:String

        )