package com.example.monee.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.MainActivity
import com.example.monee.R
import com.example.monee.databinding.ActivityRegisterDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class RegisterDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val username = intent.getStringExtra("username").toString()
            val email = intent.getStringExtra("email").toString()
            val gender: String = when (binding.rdGender.checkedRadioButtonId) {
                binding.rdMale.id -> "Male"
                binding.rdFemale.id -> "Female"
                else -> ""
            }
            val phone = binding.editPhone.text.toString()
            var newUser = User(username, gender,email, phone)
            addUser(newUser)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun addUser(user: User) {
        val database = FirebaseFirestore.getInstance()
        val uid = UUID.randomUUID()
        database.collection("users").document(uid.toString()).set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "User info added", Toast.LENGTH_LONG).show();
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show();
            }
    }
}