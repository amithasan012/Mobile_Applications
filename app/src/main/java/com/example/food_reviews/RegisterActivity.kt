package com.example.food_reviews

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    lateinit var user: EditText
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var confirm: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        user = findViewById<EditText>(R.id.username)
        email = findViewById<EditText>(R.id.email)
        pass = findViewById<EditText>(R.id.password)
        confirm = findViewById<EditText>(R.id.confirmpassword)

    }

    fun startFeed(view: View) {}
    fun loginUser(view: View) {
        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
    }
}