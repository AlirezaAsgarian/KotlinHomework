package org.example

data class RepoResponse(
    val id: Long,
    val name: String,
    val full_name: String,
    val html_url: String,
    val description: String?
)
