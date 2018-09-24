package se.evinja.rxandroid.simpleexamples

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

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

    fun basicObservable() {
        //The observable
        val observable = Observable.create<String> { observer ->
            //The lambda will be called for every subscriber by default
            println("🍄 ~~Observable logic being triggered ~~")

            //Do work on a background thread
            launch {
                delay(1000)
                observer.onNext("some value 23")
                observer.onComplete()
            }
        }

        observable.subscribe{someString ->
            println("🍄 New value: $someString")
        }.addTo(bag)

        val observer = observable.subscribe{ someString ->
            println("🍄 Another subscriber: $someString")
        }.addTo(bag)
    }

    fun creatingObservables() {
        val observable = Observable.just("23") //Gives us an observalbe of just one event
        val observable2 = Observable.interval(300, TimeUnit.MILLISECONDS).timeInterval(AndroidSchedulers.mainThread())
        val userIds = arrayOf(1,2,3,4,5,5,6)
        val observable3 = userIds.toObservable()

    }

}