package se.evinja.rxandroid.simpleexamples

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

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

        val changeSubscription = someInfo.subscribe() { newValue ->
            println("🦄 value has changed: $newValue")
        }

        someInfo.accept("3")
        //NOTE Relays will never receive onError/onComplete

    }

    fun subjects() {
        val behaviourSubject = BehaviorSubject.createDefault("24")
        val disposable = behaviourSubject.subscribe({ newValue -> //onNext
            println("🕺 behaviorSubject subscription: $newValue")
        }, { error -> // onError
            println("🕺 error: ${error.localizedMessage}")
        }, { //onCompleted
            println("🕺 Completed")
        }, { disposable -> //onSubscribed
            println("🕺 Subscribed")
        })

        behaviourSubject.onNext("34")
        behaviourSubject.onNext("48")
        behaviourSubject.onNext("48") //Duplicates shown as new events by default

        //1 onError
        //val someException = IllegalArgumentException("Fake error")
        //behaviourSubject.onError(someException) //Push the error to te subject
        //behaviourSubject.onNext("109") //This will never show

        //2 onComplete
        behaviourSubject.onComplete()
        behaviourSubject.onNext("1111") //This will never show
    }

}