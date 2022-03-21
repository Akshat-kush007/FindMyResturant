package com.example.findmyrestaurant.loginActivities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.findmyrestaurant.R
import com.example.findmyrestaurant.HomePageActivity

class ForgotActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var etEmail:EditText
    lateinit var etMObile:EditText
    lateinit var btButton:Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        toolbar=findViewById(R.id.forgotToolbar)
        etEmail=findViewById(R.id.etemail2)
        etMObile=findViewById(R.id.etmobileNum)
        btButton=findViewById(R.id.btlogin2)

        setSupportActionBar(toolbar)
        supportActionBar?.title="Forgot Password"

        sharedPreferences=getSharedPreferences(getString(R.string.login_prefrence_file), MODE_PRIVATE)
        val getMobile=sharedPreferences.getString("MobileNo","0000")
        val getEmail=sharedPreferences.getString("Email","abcd")

        val mobile=etMObile.text.toString()
        val email=etEmail.text.toString()

        btButton.setOnClickListener {
            if(mobile==getMobile){
                if (email==getEmail){
                    val intent=Intent(this@ForgotActivity, HomePageActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Toast.makeText(this@ForgotActivity,"Wrong mobile Number",Toast.LENGTH_SHORT).show()
            }
        }

    }
}