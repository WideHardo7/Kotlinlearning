package com.example.kotlinlearning.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val preferences_name = "isFirstTime"
    private lateinit var drawerLayout: DrawerLayout

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

            var intent = Intent(this, MainActivity2::class.java)

            startActivity(intent)

            sharedTime.edit().putBoolean("firstTime",false).apply()

        } else {



            val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main
            )

            val navController = this.findNavController(R.id.fragmentContainerView)

            //supa action bar
            drawerLayout = binding.drawerLayout
            NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

            //Navigation drawer
            NavigationUI.setupWithNavController(binding.navView, navController)


        }
    }
}

