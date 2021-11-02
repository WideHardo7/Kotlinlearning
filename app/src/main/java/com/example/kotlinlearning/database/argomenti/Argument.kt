package com.example.kotlinlearning.database.argomenti

import androidx.room.*
import androidx.versionedparcelable.VersionedParcelize



@Entity (tableName = "argument")
data class Argument(


    @PrimaryKey(autoGenerate = false)
    val cod_argomento:String,
    val indice: Int,
    var score : Int=0,
    //unlocked è un int che funge da boolean, quindi assumera solo valori 0 o 1
    var unlocked: Int=0
)
