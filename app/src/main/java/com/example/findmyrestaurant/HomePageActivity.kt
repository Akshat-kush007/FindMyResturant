package com.example.findmyrestaurant

import android.R.*
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.findmyrestaurant.fragments.FaqFragment
import com.example.findmyrestaurant.fragments.FavouriteFragment
import com.example.findmyrestaurant.fragments.HomeFragment
import com.example.findmyrestaurant.fragments.ProfileFragment
import com.example.findmyrestaurant.loginActivities.LoginActivity
import com.google.android.material.navigation.NavigationView

class HomePageActivity : AppCompatActivity() {

    lateinit var navigator:NavigationView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout:DrawerLayout
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        toolbar=findViewById(R.id.homeToolbar)
        navigator=findViewById(R.id.homeNevigator)
        drawerLayout=findViewById(R.id.homeDrawer)

//  setting up sharedPrefrence
        sharedPreferences=getSharedPreferences(getString(R.string.login_prefrence_file), MODE_PRIVATE)

//Setting support Action Bar
        setUpToolBar()

//adding Responsiveness to toolbar with hamburgerIcon
        val actionBarDrawerToggle=
            ActionBarDrawerToggle(this@HomePageActivity,drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
            )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

//      Setting up HomeDrawer
        setUpHomeFragment()

//adding Responsiveness to NavigatorView
        var previousMenuItem:MenuItem?=null
        navigator.setNavigationItemSelectedListener {
            if (previousMenuItem != null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when(it.itemId){
                R.id.menuHome->{
                    setUpHomeFragment()
                }
                R.id.menuFavourite->{
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,FavouriteFragment()).commit()
                    supportActionBar?.title="Favourite"
                    drawerLayout.closeDrawers()

                }
                R.id.menuProfile->{
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,ProfileFragment()).commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()

                }
                R.id.menuFaq->{
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,FaqFragment()).commit()
                    supportActionBar?.title="Frequently Asked Questions"
                    drawerLayout.closeDrawers()

                }
                R.id.menuLogout->{
                    val intent=Intent(this@HomePageActivity,LoginActivity::class.java)
                    sharedPreferences.edit().putBoolean("isLogin",false).apply()
                    startActivity(intent)
                    drawerLayout.closeDrawers()
                    finish()
                }
            }

            return@setNavigationItemSelectedListener true
        }





    }
    fun setUpToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Home"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if (id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun setUpHomeFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
            .commit()
        drawerLayout.closeDrawers()
        navigator.setCheckedItem(R.id.menuHome)
        supportActionBar?.title="DashBoard"
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frameLayout)
        if (frag is HomeFragment){
            super.onBackPressed()
        }else{
            setUpHomeFragment()
        }
    }
}