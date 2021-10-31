package com.example.kotlinlearning.database.domande

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "domande_multiple")
data class DomandeMultiple (

    @PrimaryKey(autoGenerate = true)
    val id_domanda: Int,
    val testo_domanda: String,
    val cod_argomento: String,
    val risposta_1: String,
    val risposta_2: String,
    val risposta_3: String,
    val risposta_giusta: String,


    ):Parcelable


