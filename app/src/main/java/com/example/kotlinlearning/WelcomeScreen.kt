package com.example.kotlinlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class WelcomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },1000)
    }
}