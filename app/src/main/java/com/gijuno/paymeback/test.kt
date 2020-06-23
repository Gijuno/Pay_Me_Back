package com.gijuno.paymeback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.activity_test.*

class test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btn_1.setOnClickListener {
            btn_2.performClick()
        }

        btn_2.setOnClickListener {
            Toast.makeText(this@test, "앙 성공 기모링", Toast.LENGTH_SHORT).show()
        }

    }
}