package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityCheckInBinding
/**
 * Provides user check-in functionality.
 *
 * This class can be used to let the user confirm their safety or status. It is
 * optional but supports future expansion of the SafeStep app.
 */
class CheckInActivity : AppCompatActivity() {

    private lateinit var b: ActivityCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(b.root)

        val prefs = getSharedPreferences("user_settings", MODE_PRIVATE)
        val savedHour = prefs.getInt("checkin_hour", 12)
        val savedMinute = prefs.getInt("checkin_minute", 0)

        // Load saved time
        b.timePicker.hour = savedHour
        b.timePicker.minute = savedMinute

        b.btnSave.setOnClickListener {
            val hour = b.timePicker.hour
            val minute = b.timePicker.minute

            prefs.edit()
                .putInt("checkin_hour", hour)
                .putInt("checkin_minute", minute)
                .apply()

            Toast.makeText(this, "Check-in time saved!", Toast.LENGTH_SHORT).show()
            finish()
        }

        b.btnBack.setOnClickListener {
            finish()
        }

        // Bottom Navigation
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
        b.bottomNav.navProfile.setOnClickListener {
            finish()
        }
    }
}
