package com.example.monee.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.MainActivity
import com.example.monee.databinding.ActivityRegisterDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegisterDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterDetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val username = intent.getStringExtra("username").toString()
        val gender:String = when(binding.rdGender.checkedRadioButtonId){
            binding.rdMale.id -> "Male"
            binding.rdFemale.id -> "Female"
            else -> ""
        }
        val phone = binding.editPhone.text.toString()

        var newUser = User(username, gender, phone)
        addUser(newUser)
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun addUser(user: User) {
        val user = auth.currentUser
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid).child("User")
        }

        Toast.makeText(this, "adding new", Toast.LENGTH_LONG).show();

        database.setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "User info added", Toast.LENGTH_LONG).show();
            }
            .addOnFailureListener { ex ->
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show();
            }


    }

}