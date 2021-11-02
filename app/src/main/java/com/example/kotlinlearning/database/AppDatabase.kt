package com.example.kotlinlearning.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.argomenti.ArgumentDao
import com.example.kotlinlearning.database.domande.DomandeInserimento
import com.example.kotlinlearning.database.domande.DomandeInserimentoDao
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.database.domande.DomandeMultipleDao
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.database.teoria.TeoriaDao

@Database(entities =[Argument::class, Teoria::class, DomandeInserimento::class, DomandeMultiple::class], version = 1,exportSchema = true)
abstract class AppDatabase:RoomDatabase() {

    abstract fun argumentDao():ArgumentDao
    abstract fun teoriaDao():TeoriaDao
    abstract fun domandemultipleDao():DomandeMultipleDao
    abstract fun domandeinserimentoDao():DomandeInserimentoDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {  return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(  context.applicationContext,  AppDatabase::class.java, "kotlin_learning_database"
            )
                .fallbackToDestructiveMigration()
                //.allowMainThreadQueries()
                .createFromAsset("database/argument.db")

                .build()
                .also { INSTANCE = it }

        }
        }

    }


}