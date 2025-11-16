/**
 * MainActivity
 * ------------
 * The main entry point of the SafeStep application. Displays the primary
 * emergency and navigation actions for the user. This Activity utilizes Android
 * Intents for screen-to-screen navigation and the system SMS application for
 * emergency messages.
 */

package com.example.safestep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityMainBinding
import com.example.safestep.tips.TipListActivity


/**
 * The main home screen of the SafeStep application.
 *
 * This class displays the primary actions of the app such as Help Me, I’m Safe,
 * Contacts, and Profile. It handles user interaction on the home screen and
 * routes the user to the correct Activity using Intents. The class also launches
 * the SMS app to send an emergency message.
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
            startActivity(Intent(this, TipListActivity::class.java))
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
    }
}
