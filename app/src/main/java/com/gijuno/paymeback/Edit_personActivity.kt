package com.gijuno.paymeback

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.activity_edit_person.*

class Edit_personActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_person)

        edit_layout_scrollview.scrollBarSize=0


        edit_age_layout.setOnClickListener() {
            edit_age_edittext.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edit_age_edittext, InputMethodManager.SHOW_IMPLICIT)
        }

        edit_phone_layout.setOnClickListener() {
            edit_phone_edittext.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edit_phone_edittext, InputMethodManager.SHOW_IMPLICIT)
        }

        edit_phone_edittext.addTextChangedListener(PhoneNumberFormattingTextWatcher());

        edit_sexbtn_1.setOnClickListener() {
            edit_sexbtn_1.isSelected = true;
            edit_sexbtn_2.isSelected = false;
        }

        edit_sexbtn_2.setOnClickListener() {
            edit_sexbtn_1.isSelected = false;
            edit_sexbtn_2.isSelected = true;
        }

    }
}