package org.example

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    // Example endpoint for user details
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<UserResponse>

    // Endpoint to get the public repositories for a user
    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): Response<List<RepoResponse>>
}
