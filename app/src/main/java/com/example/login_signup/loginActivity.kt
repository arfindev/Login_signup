package com.example.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_page.*

class loginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        firebaseAuth = FirebaseAuth.getInstance()
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            Toast.makeText(this, "User is already logged in!", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        button2.setOnClickListener {
            loginuser()
        }

        btnSignin.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun loginuser() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show()
            return
        }


        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credentials Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun redirect(name: String) {
        val intent = when (name) {
            "LOGIN" -> Intent(this, MainActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}