package com.gijuno.paymeback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Goto_Addperson.setOnClickListener {
            val firstIntent = Intent(this, Add_personActivity::class.java)
            startActivity(firstIntent)
        }

        Goto_Editperson.setOnClickListener {
            val secondIntent = Intent(this, Edit_personActivity::class.java)
            startActivity(secondIntent)
        }
    }
}