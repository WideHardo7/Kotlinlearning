package com.example.kotlinlearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import android.content.SharedPreferences
import android.util.Log
import com.example.kotlinlearning.databinding.ActivityMainBinding
import com.example.kotlinlearning.databinding.TestConoscenzeBinding


class MainActivity : AppCompatActivity() {

    val preferences_name = "isFirstTime"
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        firstTime()

    }

    //app bar bottone up
    override fun onSupportNavigateUp(): Boolean {

        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(drawerLayout)

    }

    fun firstTime() {

        val sharedTime = getSharedPreferences(preferences_name,0)

        if (sharedTime.getBoolean("firstTime",true)){

            val binding = DataBindingUtil.setContentView<TestConoscenzeBinding>(this, R.layout.test_conoscenze)

            sharedTime.edit().putBoolean("firstTime",false).apply()

        } else {

            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

            val navController = this.findNavController(R.id.fragmentContainerView)

            //supa action bar
            drawerLayout = binding.drawerLayout
            NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

            //Navigation drawer
            NavigationUI.setupWithNavController(binding.navView, navController)

        }
    }
}

