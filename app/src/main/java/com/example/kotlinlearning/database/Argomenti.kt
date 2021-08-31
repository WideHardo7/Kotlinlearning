package com.example.kotlinlearning.database

import androidx.room.*

@Entity (tableName = "argomenti")
data class Argomenti(
    @PrimaryKey(autoGenerate = true)
    val cod_argomento:String,
    val teoria_testo: String,
    var score : Int=0,
    var unlocked: Boolean
)
