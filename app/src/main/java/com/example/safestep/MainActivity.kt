/**
 * MainActivity
 * ------------
 *
 * <p>
 * The main entry point of the SafeStep application. Displays the primary
 * emergency and navigation actions for the user. This Activity utilizes Android
 * Intents for screen-to-screen navigation and the system SMS application for
 * emergency messages.
 * </p>
 *
 * <p><strong>Features:</strong></p>
 * <ul>
 *   <li><strong>Help Me:</strong> Opens SMS composer with a pre-defined alert message.</li>
 *   <li><strong>I’m Safe:</strong> Redirects the user to {@link TipListActivity} to view safety tips.</li>
 *   <li><strong>Contacts:</strong> Opens {@link ContactsActivity} to access trusted contacts.</li>
 *   <li><strong>Profile:</strong> Opens {@link ProfileActivity} for personal account management.</li>
 * </ul>
 *
 * <p>
 * This Activity uses ViewBinding to access UI components in a type-safe and
 * reliable manner. The layout is defined in <code>activity_main.xml</code>.
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
