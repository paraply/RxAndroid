# RxAndroid
Reactive extenstions in Android Development

### RxJava
Is a library that gives us an easy to use observer pattern. The process will notify us when
* Data changes
* Task completes
* Error happen

RxJava provides
* Common patterns for events, data changes
* Filter, map values to new result
* Chain actions
* Async and threading APIs

### Observables and Observers

Observables are like a list of single events with those values = what you watch
An observable could for example be a new event from a user interaction, or a complete event from network tasks.
Observers or Subscribers are the ones doing the watching

```kotlin
val observable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9)

val disposable = observable.subscribe({number: Int -> //onNext
  println(number)
}, { error -> //onError
  println("Some error happened: $error")
}, { //onCompleted
  println("no more elements... ever")
}, { //onSubscribe = triggered as soon as the subscription first connects but before any values actually comes through
  println("Subscription is connected")
})

disposable.disposedBy(bag) //Remeber to clean up supscriptions
```

### Observable types
There are three types of observables
* Relays are the simplest type, it gives the ability to get and set at any time. Notifies when a variable changes.
Never error outs or completes

* Subjects can receive onError/onCompleted after which they die.
  They can be subscribers and observables - you can bind the output from one observable and make it anothers input.
  There are 3 different flavors of subjects:
  - Behavior  - Receives last event of the default one
  - Publish   - Begins with no values, will only get new events
  - Replay    - n number of previous events

* Observables are the most advanced type, they can be used for receiving data from network etc.
```kotlin
        val observable = Observable.create<String> { observer ->
            //The lambda will be called for every subscriber by default
            println("🍄 ~~ Observable logic being triggered ~~")

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
```
### Observable traits
These are one of tasks that can be wrapped in a single observable
* Single will only receive one onNext/onError
* Completable will only receive one onComplete/onError
* Maybe will either receive one onError/onComplete or possible onError
  

