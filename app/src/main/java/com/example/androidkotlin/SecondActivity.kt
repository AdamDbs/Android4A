package com.example.androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.title = "Second activity"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }
}
