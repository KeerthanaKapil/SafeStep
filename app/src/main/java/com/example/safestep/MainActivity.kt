package com.example.safestep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityMainBinding

/**
 * Main home screen of the application.
 * Provides the central navigation hub for the user after logging in.
 * It includes buttons to access key features like the map, contacts, and safety tips.
 *
 * @author  Keerthana Baskaran, Itzayana Aguilar, Jovanni Maya
 * @version 1.0
 */
class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Navigate to the user's profile
        b.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Display a confirmation toast that user is safe
        b.btnSafe.setOnClickListener {
            Toast.makeText(this, "Glad you’re safe!", Toast.LENGTH_SHORT).show()
        }

        // Open the SMS app with a pre-filled help message
        b.btnHelp.setOnClickListener {
            val body = "🚨 I need help. This is SafeStep."
            val uri = Uri.parse("smsto:")
            startActivity(Intent(Intent.ACTION_SENDTO, uri).putExtra("sms_body", body))
        }

        // Navigate to the emergency contacts screen
        b.btnContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        // Navigate to the map screen
        b.btnMap.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }

        // Navigate to the safety tips screen
        b.btnTips.setOnClickListener {
            startActivity(Intent(this, TipListActivity::class.java))
        }
    }
}
