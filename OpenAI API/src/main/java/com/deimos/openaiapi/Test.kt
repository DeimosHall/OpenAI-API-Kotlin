package com.deimos.openaiapi

import android.content.Context
import android.widget.Toast

class Test(private val context: Context) {
    fun showMessage() { Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show() }
}