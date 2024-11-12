import kotlin.properties.Delegates

class Data {
    // it will initialize the abc when we acces it
    // 4-5 thread will access same value, consistancy maintained
    val abc : Int by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { 5 } // by default synchronized
    /* Description: This mode ensures thread-safe access by using a synchronized lock. When multiple threads try to
    access the property simultaneously, only one thread will compute the value, and others will wait
    until it’s available. This prevents multiple threads from re-computing the value concurrently.
Use Case: Suitable when you want to safely access the property in a multithreaded environment and ensure
the value is only computed once.*/
    val abc1 : Int by lazy(LazyThreadSafetyMode.PUBLICATION) { 10 } //
    /*Description: In this mode, multiple threads can initialize the property simultaneously.
    If two threads access the property at the same time and it hasn’t been initialized, both may compute the value, but only one result will be retained.
Use Case: This is useful when you don’t mind if the property is initialized multiple times under
rare conditions. It’s faster than SYNCHRONIZED but can result in minor inefficiencies with multiple
initializations.
Performance Impact: Slightly faster than SYNCHRONIZED as it avoids locking but allows redundant initialization.
    * */
    val abc2 : Int by lazy(LazyThreadSafetyMode.NONE) { 15 }

    /* Description: In this mode, no synchronization is performed, so the property is not thread-safe. If accessed from multiple threads simultaneously, it can lead to inconsistent or unexpected results. The value is computed the first time it is accessed, but if accessed by multiple threads simultaneously, there is no guarantee of safety.
Use Case: Best used when thread safety is not a concern (e.g., when you know the property will be accessed only from a single thread).
Performance Impact: Fastest option since it avoids synchronization completely, but only safe in single-threaded contexts.*/


    // you can see old, new value
    var l : Int by Delegates.observable(0){property, oldValue, newValue ->
        println("Old value of l : ${oldValue}")
        println("Old value of l : ${newValue}")
        println("Old value of l : ${property}")
    }
    // here you can set value accoridng to condition
   // must write condition here to initialize
    var m : Int by Delegates.vetoable(26){property, oldValue, newValue ->
        println("Old value of m : ${oldValue}")
        println("Old value of m : ${newValue}")
        println("Old value of m : ${property}")
        newValue>24
    }



}

fun main (){

    val p = Data()
//    p.l = 10
    p.m = 12
    println(p.m)
}