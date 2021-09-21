package com.example.kotlinlearning.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMain2Binding>(this,
            R.layout.activity_main2
        )

        val navController = this.findNavController(R.id.nav_host_2)


    }
}