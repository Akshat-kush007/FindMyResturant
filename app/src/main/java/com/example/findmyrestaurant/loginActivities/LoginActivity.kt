package com.example.findmyrestaurant.loginActivities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.findmyrestaurant.HomePageActivity
import com.example.findmyrestaurant.R

class LoginActivity : AppCompatActivity() {

    lateinit var splashToolbar: Toolbar
    lateinit var imgicon:ImageView
    lateinit var etemail:EditText
    lateinit var etpassword: EditText
    lateinit var loginButton:Button
    lateinit var forgot:TextView
    lateinit var register:TextView

    lateinit var sharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        splashToolbar=findViewById(R.id.splashToolbar)
        imgicon=findViewById(R.id.imgicon)
        etemail=findViewById(R.id.etemail3)
        etpassword=findViewById(R.id.etPassword)
        loginButton=findViewById(R.id.btLogin)
        forgot=findViewById(R.id.txtforgot)
        register=findViewById(R.id.txtRegister)

        setSupportActionBar(splashToolbar)
        supportActionBar?.title="Log In"

        sharedPreferences=getSharedPreferences(getString(R.string.login_prefrence_file), MODE_PRIVATE)

        val islogin=sharedPreferences.getBoolean("isLogin",false)
        if(islogin){
            loginDone()
        }

        val getEmail=sharedPreferences.getString("Email","0000")
        val password=sharedPreferences.getString("Password","1234")

        loginButton.setOnClickListener {
            val email=etemail.text.toString()
            val pass=etpassword.text.toString()

            if(email==getEmail){
                if (pass == password){
                   loginDone()
                }else{
                    Toast.makeText(this@LoginActivity,"Wrong Password", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@LoginActivity,"Wrong Email", Toast.LENGTH_SHORT).show()
            }
        }

        forgot.setOnClickListener {
            val fogintent= Intent(this@LoginActivity, ForgotActivity::class.java)
            startActivity(fogintent)
        }

        register.setOnClickListener {
            val signIntent= Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(signIntent)
        }

    }
    fun loginDone(){
        val intent= Intent(this@LoginActivity, HomePageActivity::class.java)
        sharedPreferences.edit().putBoolean("isLogin",true).apply()
        startActivity(intent)
        finish()
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}