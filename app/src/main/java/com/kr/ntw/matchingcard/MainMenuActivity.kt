package com.kr.ntw.matchingcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        findViewById<TextView>(R.id.startBtn).setOnClickListener {
            val dialog = CustomDialogSelectCardSize(this)
            dialog.showDialog()
            dialog.setOnClickListener(object: CustomDialogSelectCardSize.OnDialogClickListener {
                override fun onClicked(name: String) {
                    Toast.makeText(this@MainMenuActivity,name+"",Toast.LENGTH_SHORT).show()
                }
            })
        //            startActivity(
        //                Intent(this,GameScreenActivity::class.java)
        //            )
        }
    }
}