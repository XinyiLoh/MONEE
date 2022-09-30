package com.example.monee.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.MainActivity
import com.example.monee.R
import com.example.monee.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val name = binding.editName.text.toString()
        val email = binding.editEmail.text.toString()
        val phone = binding.editPhone.text.toString()
        val pw = binding.editPw.text.toString()
        val btnLogin = binding.btnLogin
        val toRegister = binding.textRegister

        btnLogin.setOnClickListener {
            validate(name, phone, email, pw)
            loginUser(name, phone, email, pw)
        }

        toRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validate(
        userName: String,
        userPhone: String,
        userEmail: String,
        userPassword: String
    ) {
        if ((userName == "Admin") && (userPhone == "1234")
            && (userEmail == "admin@gmail.com") && (userPassword == "1234")
        ) {
            startActivity(Intent(this, MainActivity::class.java))
        } else if ((userName.isEmpty()) && (userPhone.isEmpty())
            && (userEmail.isEmpty()) && (userPassword.isEmpty())
        ) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser(
        userName: String,
        userPhone: String,
        userEmail: String,
        userPassword: String
    ) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Login Successfull", Toast.LENGTH_LONG).show();

                //to home page home fragment
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { exception ->
                Toast.makeText(baseContext, exception.message.toString(), Toast.LENGTH_LONG).show();
            }

    }
}