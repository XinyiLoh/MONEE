package com.example.monee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val name = binding.editName.text
    private val password = binding.editPw.text
    private val confirmpw = binding.confirmPw.text
    private val btnRegister = binding.btnRegister
    private val toLogin = binding.textLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        btnRegister.setOnClickListener {
            fun onClick() {
                if (validate()) {
                    //save data into DB

                }
            }
        }

        toLogin.setOnClickListener {
            fun onClick() {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }

    private fun validate(): Boolean {
        var result = false
        val name: String = name.toString()
        val pw: String = password.toString()
        val confirmpw: String = confirmpw.toString()

        //if user did not enter any details
        if (name.isEmpty() && pw.isEmpty() && confirmpw.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
        } else {
            result = true
        }
        return result
    }
}