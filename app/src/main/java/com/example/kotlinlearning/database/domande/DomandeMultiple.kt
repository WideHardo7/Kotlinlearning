package com.example.kotlinlearning.database.domande

import androidx.room.*

@Entity(tableName = "domande_multiple")
data class DomandeMultiple (

    @PrimaryKey(autoGenerate = true)
    val id_domanda: Int,
    val testo_domanda: String,
    val argomento: String,
    val risposta_1: String,
    val risposta_2: String,
    val risposta_3: String,
    val risposta_giusta: String,


)


