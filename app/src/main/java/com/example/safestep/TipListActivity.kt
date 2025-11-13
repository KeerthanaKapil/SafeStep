package com.example.safestep.tips

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.safestep.R

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

    private fun openTip(index: Int) {
        val i = Intent(this, TipDetailActivity::class.java)
        i.putExtra("index", index)
        startActivity(i)
    }
}
