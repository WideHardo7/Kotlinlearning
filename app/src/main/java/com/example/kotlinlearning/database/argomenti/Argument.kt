package com.example.kotlinlearning.database.argomenti

import androidx.room.*

@Entity (tableName = "argument")
data class Argument(


    @PrimaryKey()
    val cod_argomento:String,
    val index: Int,
    var score : Int=0,
    var unlocked: Boolean=false
)
