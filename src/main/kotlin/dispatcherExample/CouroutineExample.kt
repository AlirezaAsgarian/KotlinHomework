package org.example.dispatcherExample

// suspend functions allow coroutines to be suspended and resumed without blocking a thread.

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() = runBlocking {
    // Create a fixed thread pool with exactly two threads.
    val dispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    // First coroutine: prints a message, delays 2 seconds, then prints again.
    launch(dispatcher) {
        println("First coroutine: Start")
        delay(2000) // non-blocking delay
        println("First coroutine: End after 2 seconds")
    }

    // Second coroutine: simulates heavy computation that runs for at least 10 seconds.
    launch(dispatcher) {
        println("Second coroutine: Heavy computation start")
        val startTime = System.currentTimeMillis()
        // Loop until 10 seconds have elapsed, doing dummy heavy computation.
        while (System.currentTimeMillis() - startTime < 10_000) {
            for (i in 1..1_000_000) {
                // Perform a dummy calculation.
                Math.sqrt(i.toDouble())
            }
            // yield()
        }
        println("Second coroutine: Heavy computation end")
    }

    // Third coroutine: similar heavy computation as the second coroutine.
    launch(dispatcher) {
        println("Third coroutine: Heavy computation start")
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < 10_000) {
            for (i in 1..1_000_000) {
                Math.sqrt(i.toDouble())
            }
            // yield()
        }
        println("Third coroutine: Heavy computation end")
    }

    // Wait enough time for all coroutines to complete.
    delay(12_000)
    // Shut down the dispatcher to free up resources.
    dispatcher.close()
}
