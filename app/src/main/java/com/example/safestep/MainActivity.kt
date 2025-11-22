package com.example.safestep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityMainBinding

/**
 * Home: "Help Me!" opens SMS composer; "I'm Safe" shows a toast; Contacts opens contacts screen.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

       //profile button
        b.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // “I’m Safe”
        b.btnSafe.setOnClickListener {
            Toast.makeText(this, "Glad you’re safe!", Toast.LENGTH_SHORT).show()
        }

        // “Help Me!” → open SMS app (no SEND_SMS permission required)
        b.btnHelp.setOnClickListener {
            val body = "🚨 I need help. This is SafeStep."
            val uri = Uri.parse("smsto:") // user selects contact
            startActivity(Intent(Intent.ACTION_SENDTO, uri).putExtra("sms_body", body))
        }

        // Go to Contacts screen
        b.btnContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        // Go to Map screen
        b.btnMap.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
    }
}
