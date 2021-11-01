package com.example.kotlinlearning.database.domande

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "domande_inserimento")
data class DomandeInserimento(

    @PrimaryKey(autoGenerate = true)
    val id_domanda: Int,
    val testo_domanda: String,
    val cod_argomento: String,
    val risposta_giusta:String

        ):Parcelable