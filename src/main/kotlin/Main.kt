package org.example

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val repository = GitHubUserRepository()

    while (true) {
        println(
            """
            |Please choose an option:
            |1️⃣  Get GitHub user info by username
            |2️⃣  Display cached users
            |3️⃣  Search cached users by username
            |4️⃣  Search cached data by repository name
            |5️⃣  Exit
            """.trimMargin()
        )

        when (readLine()?.trim()) {
            "1" -> {
                print("Enter GitHub username: ")
                val username = readLine()?.trim().orEmpty()
                if (username.isNotEmpty()) {
                    val result = repository.fetchUser(username)
                    if (result.isSuccess) {
                        println("User data: ${result.getOrNull()}")
                    } else {
                        println("Error: ${result.exceptionOrNull()?.message}")
                    }
                } else {
                    println("Username cannot be empty.")
                }
            }
            "2" -> {
                repository.getCachedUsers().forEach { println(it) }
            }
            "3" -> {
                print("Enter search term for username: ")
                val query = readLine()?.trim().orEmpty()
                val results = repository.searchUser(query)
                results.forEach { println(it) }
            }
            "4" -> {
                print("Enter search term for repository name: ")
                val query = readLine()?.trim().orEmpty()
                val results = repository.searchRepository(query)
                results.forEach { println(it) }
            }
            "5" -> {
                println("Exiting program.")
                break
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}


