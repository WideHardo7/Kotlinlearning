package com.example.kotlinlearning.database.teoria

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "teoria")
data class Teoria(
    @PrimaryKey
    val argomento:String,
    val testo_teoria:String,
)