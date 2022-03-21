package com.example.findmyrestaurant.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.findmyrestaurant.R


class ProfileFragment : Fragment() {

    lateinit var profilename:TextView
    lateinit var profilenumber:TextView
    lateinit var profileaddress:TextView
    lateinit var profileemail:TextView
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_profile, container, false)
        profilename=view.findViewById(R.id.profilename)
        profilenumber=view.findViewById(R.id.profilenumber)
        profileaddress=view.findViewById(R.id.profileaddress)
        profileemail=view.findViewById(R.id.profileemail)



        sharedPreferences=requireActivity().getSharedPreferences(getString(R.string.login_prefrence_file), AppCompatActivity.MODE_PRIVATE)

        profilename.text=sharedPreferences.getString("Name","--------")
        profilenumber.text=sharedPreferences.getString("MobileNo","--------")
        profileaddress.text=sharedPreferences.getString("Address","--------")
        profileemail.text=sharedPreferences.getString("Email","--------")

        return view
    }

}