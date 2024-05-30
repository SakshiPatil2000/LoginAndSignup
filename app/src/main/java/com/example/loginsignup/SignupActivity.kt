package com.example.loginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignup.databinding.ActivityLoginBinding
import com.example.loginsignup.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper= DatabaseHelper(this)

        binding.signupButton.setOnClickListener {

            val signupUserName=binding.signupusername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            signupDatabase(signupUserName,signupPassword)
        }

       binding.loginRedirect.setOnClickListener {
           val intent = Intent(this, LoginActivity::class.java)
           startActivity(intent)
           finish()
       }
    }

    private fun signupDatabase(username : String, password : String){
        val insertRowId=databaseHelper.insertUser(username,password)
        if(insertRowId != -1L){
            Toast.makeText(this,"Signup Success",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this,"Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }

}