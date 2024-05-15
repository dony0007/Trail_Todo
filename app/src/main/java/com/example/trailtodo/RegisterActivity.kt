package com.example.trailtodo

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var etName = findViewById<EditText>(R.id.et_name)
        var etEmail = findViewById<EditText>(R.id.et_email)
        var etPass = findViewById<EditText>(R.id.et_password)
        var btnRegister = findViewById<Button>(R.id.btn_register)

        firebaseAuth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener(View.OnClickListener{
            val email = etEmail.text.toString()
            val password = etPass.text.toString()
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(applicationContext,"Successfully Registered ", Toast.LENGTH_LONG).show()
                    var i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }
                else{
                    Toast.makeText(applicationContext,"authentication failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}