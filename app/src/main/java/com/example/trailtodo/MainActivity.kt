package com.example.trailtodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var Email = findViewById<EditText>(R.id.et_email)
        var Password = findViewById<EditText>(R.id.et_pass)
        var Login = findViewById<Button>(R.id.bt_login)
        var Register = findViewById<Button>(R.id.bt_registerpage)

        firebaseAuth = FirebaseAuth.getInstance()

        Login.setOnClickListener(View.OnClickListener {
            val email = Email.text.toString()
            val password = Password.text.toString()
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(applicationContext,"Successfully Logined ", Toast.LENGTH_LONG).show()
                    var i = Intent(applicationContext,HomeActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(applicationContext,"incorrect ", Toast.LENGTH_LONG).show()

                }
            }
        })

        Register.setOnClickListener {
            var i = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(i)
        }

    }
}