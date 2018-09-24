package se.evinja.rxandroid.simpleexamples

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable

object SimpleRx {

    var bag = CompositeDisposable() // Container which we will get disposables from, so we can clean up all subscriptions in this bag.


    fun simpleValues() {
        println("~~~~~~simpleValues~~~~~~")
        val someInfo = BehaviorRelay.createDefault("1")
        println("🙈 someinfo.value ${someInfo.value}")

        val plainString = someInfo.value
        println("🙈 plainString: $plainString")

        someInfo.accept("2")
        println("🙈 someinfo.value ${someInfo.value}")
    }

}