package com.example.monee.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.R
import com.example.monee.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val name = binding.editName.text
        val email = binding.regEditEmail.text
        val phone = binding.regEditPhone.text
        val password = binding.editPw.text
        val confirmpw = binding.confirmPw.text
        val btnRegister = binding.btnRegister
        val toLogin = binding.textLogin

        btnRegister.setOnClickListener {

            if (validate()) {
                //save data into DB
                //registerNewUser(email, password)
            }
        }

        toLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun validate(): Boolean {
        var result = false
        val name = binding.editName.text
        val password = binding.editPw.text
        val confirmpw = binding.confirmPw.text

        //if user did not enter any details
        if (name.isEmpty() && password.isEmpty() && confirmpw.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else if (!password.equals(confirmpw)) {
            Toast.makeText(this, "Passwords are not matching", Toast.LENGTH_SHORT).show()
        } else {
            result = true
        }
        return result
    }

    private fun registerNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                Toast.makeText(
                    baseContext, "New user added",
                    Toast.LENGTH_LONG
                ).show();

                //val intent = Intent(this, SignupDetailsActivity::class.java)
                //intent.putExtra("username", binding.editTextUsername.text.toString())
                startActivity(intent)
            }
            .addOnFailureListener { ex ->

                Toast.makeText(baseContext, ex.message.toString(), Toast.LENGTH_LONG).show();
            }

    }
}