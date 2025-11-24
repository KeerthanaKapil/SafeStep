package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Displays a list of safety tip categories.
 * Each category is represented by a button, and clicking a button
 * navigates the user to the TipDetailActivity to display the specific tip.
 */
class TipListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_list)

        // Set up listeners for each tip category button
        val btnWork = findViewById<Button>(R.id.btnWork)
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnUni = findViewById<Button>(R.id.btnUni)
        val btnOnline = findViewById<Button>(R.id.btnOnline)
        val btnStreet = findViewById<Button>(R.id.btnStreet)

        btnWork.setOnClickListener { openTipDetail(0) }
        btnHome.setOnClickListener { openTipDetail(1) }
        btnUni.setOnClickListener { openTipDetail(2) }
        btnOnline.setOnClickListener { openTipDetail(3) }
        btnStreet.setOnClickListener { openTipDetail(4) }
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
