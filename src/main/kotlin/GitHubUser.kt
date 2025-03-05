package org.example

data class GitHubUser(
    val username: String,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val repositories: List<Repository>
)


data class Repository(
    val name: String,
    val url: String,
    val description: String?
)

