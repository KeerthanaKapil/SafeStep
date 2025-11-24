package com.example.safestep

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.databinding.ActivityTipListBinding

/**
 * Displays a list of safety tip categories.
 * Each category is represented by a button, and clicking a button
 * navigates the user to the [TipDetailActivity] to display the specific tip.
 *
 * @author  Keerthana Baskaran
 * @version 1.0
 */
class TipListActivity : AppCompatActivity() {

    private lateinit var b: ActivityTipListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityTipListBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Set up listeners for each tip category button
        b.btnWork.setOnClickListener { openTipDetail(0) }
        b.btnHome.setOnClickListener { openTipDetail(1) }
        b.btnUni.setOnClickListener { openTipDetail(2) }
        b.btnOnline.setOnClickListener { openTipDetail(3) }
        b.btnStreet.setOnClickListener { openTipDetail(4) }

        // --- Bottom Navigation Listeners ---
        b.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        b.bottomNav.navContacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
        b.bottomNav.navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    /**
     * Opens the [TipDetailActivity] for the selected tip index.
     *
     * @param index The index of the tip to display, corresponding to the arrays in [TipsData].
     */
    private fun openTipDetail(index: Int) {
        val intent = Intent(this, TipDetailActivity::class.java).apply {
            putExtra("tip_index", index)
        }
        startActivity(intent)
    }
}
