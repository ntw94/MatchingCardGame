package com.kr.ntw.matchingcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        findViewById<TextView>(R.id.startBtn).setOnClickListener {
            startActivity(
                Intent(this,GameScreenActivity::class.java)
            )
        }
    }
}