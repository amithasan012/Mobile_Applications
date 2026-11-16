package com.example.food_reviews

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feed)

    }
    fun loginUser(view: View) {
        startActivity(Intent(this@FeedActivity, LoginActivity::class.java))
    }
    fun createPost(view: View) {
        startActivity(Intent(this@FeedActivity, CreatePostActivity::class.java))
    }
    fun myPosts(view: View) {
        startActivity(Intent(this@FeedActivity, MyPosts::class.java))
    }
    fun resetPassword(view: View) {
        startActivity(Intent(this@FeedActivity, ResetPasswordActivity::class.java))
    }

}
