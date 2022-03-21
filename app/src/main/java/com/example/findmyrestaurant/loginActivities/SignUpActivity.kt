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

class SignUpActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var signUpToolbar: Toolbar

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etMobileNo: EditText
    lateinit var etAddress: EditText
    lateinit var etPassword: EditText
    lateinit var etCPassword: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        signUpToolbar=findViewById(R.id.signUpToolbar)
        setSupportActionBar(signUpToolbar)
        supportActionBar?.title="Sign Up "

        sharedPreferences=getSharedPreferences(getString(R.string.login_prefrence_file), MODE_PRIVATE)

        etName=findViewById(R.id.etname)
        etEmail=findViewById(R.id.etemail)
        etMobileNo=findViewById(R.id.etmobileNo)
        etAddress=findViewById(R.id.etaddress)
        etPassword=findViewById(R.id.etpassword)
        etCPassword=findViewById(R.id.etcpassword)

        button=findViewById(R.id.btregister)

        button.setOnClickListener {
            val setName=etName.text.toString()
            val setEmail=etEmail.text.toString()
            val setMobileNo=etMobileNo.text.toString()
            val setAddress=etAddress.text.toString()
            val setPassword=etPassword.text.toString()
            val setCPassword=etCPassword.text.toString()

            if (setPassword ==setCPassword){
                sharedPreferences.edit().putString("Name",setName).apply()
                sharedPreferences.edit().putString("MobileNo",setMobileNo).apply()
                sharedPreferences.edit().putString("Address",setAddress).apply()
                sharedPreferences.edit().putString("Email",setEmail).apply()
                sharedPreferences.edit().putString("Password",setPassword).apply()

                val intent= Intent(this@SignUpActivity, HomePageActivity::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this@SignUpActivity,"Confirm password not match", Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}