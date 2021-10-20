package com.example.kotlinlearning.database.argomenti

import androidx.room.*
import androidx.versionedparcelable.VersionedParcelize



@Entity (tableName = "argument")
data class Argument(


    @PrimaryKey(autoGenerate = false)
    val cod_argomento:String,
    val indice: Int,
    var score : Int=0,
    var unlocked: Int=0
)
