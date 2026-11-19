package com.example.food_reviews

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseError

class CreatePostActivity : AppCompatActivity() {

    lateinit var iId : String
    lateinit var iUsername : String
    lateinit var iEmail : String


    lateinit var photo: ImageView
    lateinit var caption: EditText
    lateinit var count : TextView

    private val cloudName ="cick5qhf"
    private val uploadPreset ="LocalFoodReviews"

    var imageUri : Uri? = null




    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()){
            uri: Uri? ->
            if (uri != null){
                imageUri = uri
                photo = findViewById<ImageView>(R.id.photo)
                photo.setImageURI(uri)
            }

        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

    }


    fun selectPhoto(view: View) {
        galleryLauncher.launch("images/*")
    }
    fun createPost(view: View) {
        iId = intent.getStringExtra("Id").toString()
        iUsername = intent.getStringExtra("Username").toString()
        iEmail = intent.getStringExtra("Email").toString()

        if(imageUri != null){

            Toast.makeText(this@CreatePostActivity,
                "Please select an image",
                Toast.LENGTH_SHORT).show()

            return
        }
        if(caption.text.toString().length > 100){

            Toast.makeText(this@CreatePostActivity,
                "Caption is too long, must be in 100 word",
                Toast.LENGTH_SHORT).show()

            return
        }
        uploadImage()
    }

    private fun uploadImage() {
        TODO("Not yet implemented")
    }
}