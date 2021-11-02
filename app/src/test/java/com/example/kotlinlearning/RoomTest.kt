package com.example.kotlinlearning

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.argomenti.ArgumentDao
import com.example.kotlinlearning.viewmodel.CompletamentoQuizViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import java.io.InvalidObjectException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var argumentDao: ArgumentDao
    private lateinit var db: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db= Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).setTransactionExecutor(
            Executors.newSingleThreadExecutor()).build()
        argumentDao=db.argumentDao()
    }

    @Test
    @Throws(Exception::class)
    fun insertArgument() {
        val argument= Argument("Test",10 ,1,1 )
        runBlocking {
            argumentDao.insertArgument(argument)
        }
        val allArguments = argumentDao.getAllArgument()
        val result= allArguments.getValueBlocking() ?: throw InvalidObjectException("null returned as arguments")
        assertEquals(result.get(10).cod_argomento, "Test")
    }

    fun <T> LiveData<T>.getValueBlocking():T?{
        var value:T?=null
        val latch= CountDownLatch(1)
        val observer= Observer<T>{
            t ->
            value=t
            latch.countDown()
        }
        observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}

