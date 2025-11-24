package com.example.safestep

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Displays a detailed safety tip.
 * Receives an index from TipListActivity and uses it to look up the coressponding tip from  TipsData.
 */
class TipDetailActivity : AppCompatActivity() {

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_detail)

        // Get the selected tip index
        currentIndex = intent.getIntExtra("tip_index", 0)

        updateScreenContent()

        val btnPrev = findViewById<Button>(R.id.btnPrev)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnClose = findViewById<Button>(R.id.btnClose)

        // Navigate to the previous tip
        btnPrev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateScreenContent()
            }
        }

        // Navigate to the next tip
        btnNext.setOnClickListener {
            if (currentIndex < TipsData.titles.size - 1) {
                currentIndex++
                updateScreenContent()
            }
        }

        //Close the activity and return to the list
        btnClose.setOnClickListener {
            finish()
        }
    }

    /**
     * Updates the TextViews and ImageView with content
     * for the current tip index, pulling data from TipsData
     */
    private fun updateScreenContent() {
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        val imgTip = findViewById<ImageView>(R.id.imgTip)

        tvTitle.text = TipsData.titles[currentIndex]
        tvContent.text = TipsData.contents[currentIndex]
        // Note: The image is currently hardcoded for demonstration purposes.
        imgTip.setImageResource(R.drawable.ic_work_safety)
    }
}
