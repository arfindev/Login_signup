package com.example.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup_page.*

class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)
        firebaseAuth = FirebaseAuth.getInstance()

        btnSignup.setOnClickListener {
            signupusers()
        }
        btnSign.setOnClickListener {
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun signupusers(){
        val email = tvEmailSG.text.toString()
        val password = tvPassSG.text.toString()
        val confirmPassword = tvPassSGC.text.toString()

        if (password != confirmPassword){
            Toast.makeText(this,"Password Missmatch",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                Toast.makeText(this,"Signup Successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
            }
    }
}