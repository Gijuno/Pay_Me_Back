package com.gijuno.paymeback

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class PayMeBack : Application() {
    override fun onCreate() {
        super.onCreate()

        //App 클래스 만들고난 후에 Manifest에 꼭 등록해줘야해
        AndroidThreeTen.init(this)
    }
}
