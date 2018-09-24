package se.evinja.rxandroid

import android.app.Application
import se.evinja.rxandroid.simpleexamples.SimpleRx

class RxAndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        SimpleRx.simpleValues()
    }
}