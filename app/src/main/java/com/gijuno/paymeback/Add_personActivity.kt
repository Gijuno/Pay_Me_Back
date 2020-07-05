package com.gijuno.paymeback

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.activity_calendar.*

class Add_personActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        add_layout_scrollview.scrollBarSize=0

        add_name_layout.setOnClickListener {
            add_name_edittext.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(add_name_edittext, InputMethodManager.SHOW_IMPLICIT)
        }

        add_age_layout.setOnClickListener() {
            add_age_edittext.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(add_age_edittext, InputMethodManager.SHOW_IMPLICIT)
        }

        add_phone_layout.setOnClickListener() {
            add_phone_edittext.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(add_phone_edittext, InputMethodManager.SHOW_IMPLICIT)
        }

        add_phone_edittext.addTextChangedListener(PhoneNumberFormattingTextWatcher());

        add_sexbtn_1.setOnClickListener() {
            add_sexbtn_1.isSelected = true;
            add_sexbtn_2.isSelected = false;
        }

        add_sexbtn_2.setOnClickListener() {
            add_sexbtn_1.isSelected = false;
            add_sexbtn_2.isSelected = true;
        }


        add_borrow_date_layout.setOnClickListener {
            val Intent = Intent(this, CalendarActivity::class.java)
            startActivity(Intent)
        }

    }
}
