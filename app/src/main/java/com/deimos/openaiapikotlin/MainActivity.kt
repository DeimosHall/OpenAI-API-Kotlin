package com.deimos.openaiapikotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.deimos.openaiapi.Test

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMessage: Button = findViewById(R.id.btnMessage)
        val test = Test(applicationContext)

        btnMessage.setOnClickListener { test.showMessage() }
    }
}