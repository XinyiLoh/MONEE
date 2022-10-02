package com.example.monee.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.MainActivity
import com.example.monee.R
import com.example.monee.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    //private lateinit var actionBar: ActionBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionBar = supportActionBar!!
        //actionBar.title = "Login"

        auth = FirebaseAuth.getInstance()
        checkUser()

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            validate()
        }
    }

    private fun validate(){
        val email = binding.editEmail.text.toString().trim()
        val pw = binding.editPw.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.editEmail.setError("Invalid email format")
        }else if(TextUtils.isEmpty(pw)){
            binding.editPw.setError("Please enter password")
        }else if(pw.length<6){
            binding.editPw.setError("Password must at least 6 characters")
        }else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin(){
        val email = binding.editEmail.text.toString().trim()
        val pw = binding.editPw.text.toString().trim()

        auth.signInWithEmailAndPassword(email,pw)
            .addOnSuccessListener {
                //get user info
                val firebaseUser = auth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Logged in as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
            }
            .addOnFailureListener{ e ->
                Toast.makeText(this,"Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser(){
        val firebaseUser=auth.currentUser
        if(firebaseUser!=null){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    /*private fun loginUser(
        userName: String,
        userPhone: String,
        userEmail: String,
        userPassword: String
    ) {
        if ((userName.isEmpty()) && (userPhone.isEmpty())
            && (userEmail.isEmpty()) && (userPassword.isEmpty())
        ) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Login Successfull", Toast.LENGTH_LONG).show();

                //to home page home fragment
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { exception ->
                Toast.makeText(baseContext, exception.message.toString(), Toast.LENGTH_LONG).show();
            }

    }*/
}