package com.example.kotlinlearning.view.activity

import android.content.Intent
import android.os.Binder.getCallingPid
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //Inizializzo il nome del share preference
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
    //funzione che controlla il valore contenuto nel share preference, se true,
    //allora è la prima volta che l'utente usa l'app e viene eseguito il test delle conoscenze, passando alla mainActivity2,
    //se è false allora non è la prima volta che l'utente usa l'app e passa subito al primo fragment contenuto nella mainActivity
    fun firstTime() {
        //se non esiste crea un file con nome quello contenuto in preferences_name, altrimenti prende il contenuto del file
        val sharedTime = getSharedPreferences(preferences_name,0)

        if (sharedTime.getBoolean("firstTime",true)){

            var intent = Intent(this, MainActivity2::class.java)
            //setta il valore del sharepreferences a false, in questo modo non viene più invocata la MainActivity2
            sharedTime.edit().putBoolean("firstTime",false).apply()

            startActivity(intent)
            finish()



        } else {



Log.i("MainActivity", "Siamo dentro il MainActivity")
            val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main
            )

            val navController = this.findNavController(R.id.fragmentContainerView)

            //setta action bar
            drawerLayout = binding.drawerLayout
            NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

            //Navigation drawer
            NavigationUI.setupWithNavController(binding.navView, navController)


        }
    }
}

