package com.example.monee.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.monee.MainActivity
import com.example.monee.R
import com.example.monee.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    //private lateinit var actionBar: ActionBar
    private lateinit var auth: FirebaseAuth
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionBar = supportActionBar!!
        //actionBar.title = "Sign Up"
        //actionBar.setDisplayHomeAsUpEnabled(true)
        //actionBar.setDisplayShowHomeEnabled(true)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            validate()
        }
        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun validate() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPw.text.toString().trim()
        val confirmpw = binding.confirmPw.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editEmail.setError("Invalid email format")
        } else if (password.length < 6) {
            binding.editPw.setError("Password must at least 6 characters")
        } else if (!(password == confirmpw)) {
            binding.confirmPw.setError("Password confirmation does not match")
        } else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPw.text.toString().trim()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = auth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RegisterDetailsActivity::class.java)
                intent.putExtra("username",binding.editName.text.toString())
                intent.putExtra("email",email)
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Sign up failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    /*private fun registerNewUser(email: String, password: String) {
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

    }*/
}