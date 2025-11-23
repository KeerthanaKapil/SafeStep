package com.example.safestep

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Displays the detailed content of a single safety tip.
 * Allows the user to navigate between tips using previous and next buttons.
 */
class TipDetailActivity : AppCompatActivity() {

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_detail)

        // Retrieve the selected tip index from the intent
        index = intent.getIntExtra("index", 0)

        updateScreen()

        val btnPrev = findViewById<Button>(R.id.btnPrev)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnClose = findViewById<Button>(R.id.btnClose)

        // Navigate to the previous tip
        btnPrev.setOnClickListener {
            if (index > 0) {
                index--
                updateScreen()
            }
        }

        // Navigate to the next tip
        btnNext.setOnClickListener {
            if (index < TipsData.titles.size - 1) {
                index++
                updateScreen()
            }
        }

        // Close the activity and return to the list
        btnClose.setOnClickListener {
            finish()
        }
    }

    /**
     * Updates the on-screen TextViews and ImageView with the content
     * for the current tip index.
     */
    private fun updateScreen() {
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        val imgTip = findViewById<ImageView>(R.id.imgTip)

        tvTitle.text = TipsData.titles[index]
        tvContent.text = TipsData.contents[index]
        imgTip.setImageResource(R.drawable.ic_work_safety)
    }
}
