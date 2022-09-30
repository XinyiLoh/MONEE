package com.example.monee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.databinding.ActivityRegisterBinding
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val name = binding.editName.text
    private val email = binding.regEditEmail.text
    private val phone = binding.regEditPhone.text
    private val password = binding.editPw.text
    private val confirmpw = binding.confirmPw.text
    private val btnRegister = binding.btnRegister
    private val toLogin = binding.textLogin

    open fun databaseReference: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {

            if (validate()) {
                //save data into DB

            }
        }

        toLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun validate(): Boolean {
        var result = false
        val name: String = name.toString()
        val pw: String = password.toString()
        val confirmpw: String = confirmpw.toString()

        //if user did not enter any details
        if (name.isEmpty() && pw.isEmpty() && confirmpw.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else if (!pw.equals(confirmpw)) {
            Toast.makeText(this, "Passwords are not matching", Toast.LENGTH_SHORT).show()
        } else {
            result = true
        }
        return result
    }
}