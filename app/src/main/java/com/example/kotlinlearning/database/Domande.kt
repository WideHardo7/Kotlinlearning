package com.example.kotlinlearning.database

import androidx.room.*

@Entity(tableName = "domande")
data class Domande(
    @PrimaryKey(autoGenerate = true)
    val id_domanda: Int,
    val testo_domanda: String,
    val tipo:Int,
    val argomento: String,
    val risposta_1 :String,
    val risposta_2 :String,
    val risposta_3 :String,
    val risposta_giusta: String,




    )


