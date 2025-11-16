/**
 * TipDetailActivity
 * ------------------
 * Displays detailed text-based safety guidance for one of the categories
 * defined in {@link TipsData}. The chosen category is passed as an Intent
 * Extra named <code>TIP_KEY</code>.
 */
package com.example.safestep.tips

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.R

/**
 * Shows the detailed text for a selected safety category.
 *
 * TipDetailActivity receives a TIP_KEY through an Intent, retrieves the
 * correct Tip object from TipsData, and displays its title and content in a
 * scrollable layout. This screen focuses on readability and long-form content
 * delivery.
 *
 * The Activity also includes the app's bottom navigation bar, allowing users
 * to return to main sections without using the system back button.
 */
class TipDetailActivity : AppCompatActivity() {

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_detail)

        index = intent.getIntExtra("index", 0)

        updateScreen()

        val btnPrev = findViewById<Button>(R.id.btnPrev)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnClose = findViewById<Button>(R.id.btnClose)

        btnPrev.setOnClickListener {
            if (index > 0) {
                index--
                updateScreen()
            }
        }

        btnNext.setOnClickListener {
            if (index < TipsData.titles.size - 1) {
                index++
                updateScreen()
            }
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun updateScreen() {
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        val imgTip = findViewById<ImageView>(R.id.imgTip)

        tvTitle.text = TipsData.titles[index]
        tvContent.text = TipsData.contents[index]
        imgTip.setImageResource(R.drawable.ic_work_safety)
    }
}
