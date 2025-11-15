package com.example.safestep.tips

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.ContactsActivity
import com.example.safestep.MainActivity
import com.example.safestep.ProfileActivity
import com.example.safestep.databinding.ActivityTipListBinding

/**
 * Tips List: shows safety tip categories + bottom navigation.
 */
class TipListActivity : AppCompatActivity() {

    private lateinit var b: ActivityTipListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityTipListBinding.inflate(layoutInflater)
        setContentView(b.root)

        setupBottomNav()
        setupTipButtons()
    }

    private fun setupBottomNav() {

        // These come from include_bottom_nav.xml
        val navHome = b.bottomNav.navHome
        val navProfile = b.bottomNav.navProfile
        val navContacts = b.bottomNav.navContacts

        navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
    }

    private fun setupTipButtons() {
        // Later you can open detail screens here
        b.btnWork.setOnClickListener {
            // TODO: open Work safety tips detail
        }

        b.btnHome.setOnClickListener {
            // TODO: open Home safety tips detail
        }

        b.btnUni.setOnClickListener {
            // TODO: open University safety tips detail
        }

        b.btnOnline.setOnClickListener {
            // TODO: open Online safety tips detail
        }

        b.btnStreet.setOnClickListener {
            // TODO: open Street safety tips detail
        }
    }
}
