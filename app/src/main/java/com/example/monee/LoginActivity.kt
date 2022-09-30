package com.example.monee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val name = binding.editName.text
    private val email = binding.editEmail.text
    private val phone = binding.editPhone.text
    private val pw = binding.editPw.text
    private val btnLogin = binding.btnLogin
    private val toRegister = binding.textRegister
    //private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            validate(name.toString(), phone.toString(), email.toString(), pw.toString())
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
        /*else {
            counter--

            info.setText("No. of attempts remaining: " + counter)

            if (counter == 0) {
                btnLogin.isEnabled = false
            }
        }*/
    }
}