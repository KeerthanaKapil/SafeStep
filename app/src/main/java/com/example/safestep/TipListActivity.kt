package com.example.safestep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Displays a list of safety tip categories.
 * Each category button navigates to the [TipDetailActivity] to show the relevant tip.
 */
class TipListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_list)

        // Connect each button from XML and set click
        val btnWork = findViewById<Button>(R.id.btnWork)
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnUni = findViewById<Button>(R.id.btnUni)
        val btnOnline = findViewById<Button>(R.id.btnOnline)
        val btnStreet = findViewById<Button>(R.id.btnStreet)

        btnWork.setOnClickListener { openTip(0) }
        btnHome.setOnClickListener { openTip(1) }
        btnUni.setOnClickListener { openTip(2) }
        btnOnline.setOnClickListener { openTip(3) }
        btnStreet.setOnClickListener { openTip(4) }
    }

    /**
     * Opens the [TipDetailActivity] for the selected tip index.
     * @param index The index of the tip to display, corresponding to the arrays in [TipsData].
     */
    private fun openTip(index: Int) {
        val i = Intent(this, TipDetailActivity::class.java)
        i.putExtra("index", index)
        startActivity(i)
    }
}
