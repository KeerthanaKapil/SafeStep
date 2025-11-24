package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityCheckInBinding

/**
 * Allows the user to set and save a  check-in time.
 * Uses a TimePicker for time selection and persists the chosen time
 *
 * @author  Keerthana Baskaran
 * @version 1.0
 */
class CheckInActivity : AppCompatActivity() {

    private lateinit var b: ActivityCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Get the previous check-in time, or use a default.
        val prefs = getSharedPreferences("user_settings", MODE_PRIVATE)
        val savedHour = prefs.getInt("checkin_hour", 12)
        val savedMinute = prefs.getInt("checkin_minute", 0)

        // Set the TimePicker to loaded time
        b.timePicker.hour = savedHour
        b.timePicker.minute = savedMinute

        b.btnSave.setOnClickListener {
            val hour = b.timePicker.hour
            val minute = b.timePicker.minute

            // Save the selected time
            prefs.edit()
                .putInt("checkin_hour", hour)
                .putInt("checkin_minute", minute)
                .apply()

            Toast.makeText(this, "Check-in time saved!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Listener to go back to the previous screen
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
