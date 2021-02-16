package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav:BottomNavigationView = findViewById(R.id.bottom_navigation)
        var navListener =
            BottomNavigationView.OnNavigationItemSelectedListener{item: MenuItem ->
                Log.d("onNavigationItemSelect","passed")
                var selectedFragment: Fragment? = null
                when(item.itemId){
                    R.id.bottom_navigation_home->selectedFragment=home_fragment()
                    R.id.bottom_navigation_appointment->selectedFragment=AppointmentFragment()
                    R.id.bottom_navigation_donor_card->selectedFragment=DonorCardFragment()
                    R.id.bottom_navigation_about_donation->selectedFragment=AboutDonationFragment()

                }
                if (selectedFragment != null) {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,selectedFragment).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,home_fragment()).commit()
    }

}