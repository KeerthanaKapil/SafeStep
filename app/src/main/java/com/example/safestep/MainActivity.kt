/**
 * SafeStep Mobile Application – Technical Documentation
 * -----------------------------------------------------
 *
 * <p>
 * SafeStep is a personal safety Android application implemented using Kotlin,
 * XML layouts, and the Android SDK. The system provides emergency messaging,
 * safety awareness guidance, user profile management, and quick access to
 * trusted contacts. This documentation describes the architecture, modules,
 * and behaviors implemented in the project, following a JavaDoc-like structure.
 * </p>
 *
 * <p><strong>Core Features Implemented:</strong></p>
 * <ul>
 *   <li><strong>Emergency SMS</strong> – launches the device SMS app with a pre-
 *       filled help message.</li>
 *   <li><strong>Safety Tips System</strong> – category list and detailed guidance for
 *       Work, Home, University, Online, and Street safety.</li>
 *   <li><strong>User Authentication</strong> – Login screen and user data handling.</li>
 *   <li><strong>Profile Management</strong> – view, edit, and update user info.</li>
 *   <li><strong>Contacts Access</strong> – emergency contacts interface.</li>
 *   <li><strong>Bottom Navigation</strong> – reusable navigation bar shared across screens.</li>
 * </ul>
 *
 * <p>
 * The following sections describe each Activity, data class, and layout resource
 * in the SafeStep application.
 * </p>
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
