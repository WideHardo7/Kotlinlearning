package com.example.kotlinlearning.database.teoria

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "teoria")
data class Teoria(
    @PrimaryKey(autoGenerate = false)
    val cod_argomento:String,
    val testo_teoria:String,
):Parcelable