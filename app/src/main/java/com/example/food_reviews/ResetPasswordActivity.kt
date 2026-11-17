
package com.example.food_reviews

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.database.FirebaseDatabase

class ResetPasswordActivity : AppCompatActivity() {

    lateinit var oldPass: EditText
    lateinit var newPass: EditText
    lateinit var confirmPass: EditText
    lateinit var updateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        oldPass = findViewById(R.id.oldPassword)
        newPass = findViewById(R.id.newPassword)
        confirmPass = findViewById(R.id.confirmPassword)
        updateBtn = findViewById(R.id.button)
    }

    fun updatePassword(view: View) {

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val sOld = oldPass.text.toString()
        val sNew = newPass.text.toString()
        val sConfirm = confirmPass.text.toString()


        if (sOld.isEmpty() || sNew.isEmpty() || sConfirm.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }


        if (user == null || user.email == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
            return
        }


        if (sNew != sConfirm) {
            Toast.makeText(
                this,
                "New password and Confirm password must be same",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        if (sNew.length < 6) {
            Toast.makeText(
                this,
                "New password must be at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        if (sOld == sNew) {
            Toast.makeText(
                this,
                "New password must be different from old password",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        val credential = EmailAuthProvider.getCredential(
            user.email!!,
            sOld
        )


        user.reauthenticate(credential)
            .addOnSuccessListener {


                user.updatePassword(sNew)
                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Password updated successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val currentUid = user.uid
                        val currentEmail = user.email!!

                        FirebaseDatabase.getInstance()
                            .getReference("users")
                            .child(currentUid)
                            .setValue(currentEmail)

                        startActivity(
                            Intent(
                                this@ResetPasswordActivity,
                                LoginActivity::class.java
                            )
                        )

                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Password update failed: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
            .addOnFailureListener { e ->


                Toast.makeText(
                    this,
                    "Incorrect old password! Please try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}
