package com.example.food_reviews

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class ResetPasswordActivity : AppCompatActivity() {


    lateinit var oldPass: EditText
    lateinit var newPass: EditText
    lateinit var confirmPass: EditText
    lateinit var updateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


        oldPass = findViewById<EditText>(R.id.oldPassword)
        newPass = findViewById<EditText>(R.id.newPassword)
        confirmPass = findViewById<EditText>(R.id.confirmPassword)
        updateBtn = findViewById<Button>(R.id.button)
    }


    fun updatePassword(view: View) {
        val currentEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
        val currentUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        val sOld = oldPass.text.toString()
        val sNew = newPass.text.toString()
        val sConfirm = confirmPass.text.toString()


        if (sOld.isEmpty() || sNew.isEmpty() || sConfirm.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (sNew != sConfirm) {
            Toast.makeText(this, "New password and Confirm password must be same", Toast.LENGTH_SHORT).show()
            return
        }

        if (sNew.length < 6) {
            Toast.makeText(this, "New password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        val user = FirebaseAuth.getInstance().currentUser


        user?.updatePassword(sNew)?.addOnSuccessListener {
            Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show()

            FirebaseDatabase.getInstance().getReference("users")
                .child(currentUid).setValue(currentEmail)

            startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
            finish()

        }?.addOnFailureListener { e ->

            Toast.makeText(this, "Update failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


}