package com.example.monee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val name = binding.editName.text
    private val pw = binding.editPw.text
    private val btnLogin = binding.btnLogin
    //private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            fun onClick(){
                validate(name.toString(), pw.toString())
            } }
    }

    private fun validate(userName: String, userPassword: String) {
        if ((userName == "Admin") && (userPassword == "1234")) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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