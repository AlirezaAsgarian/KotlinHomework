package org.example

class GitHubUserRepository {
    // In-memory cache to avoid duplicate API calls
    private val userCache = mutableMapOf<String, GitHubUser>()

    suspend fun fetchUser(username: String): Result<GitHubUser> {
        // Return from cache if available
        userCache[username]?.let {
            return Result.success(it)
        }

        return try {
            // Fetch user and repositories from the GitHub API
            val userResponse = RetrofitClient.apiService.getUser(username)
            val reposResponse = RetrofitClient.apiService.getUserRepos(username)

            if (userResponse.isSuccessful && reposResponse.isSuccessful) {
                val userData = userResponse.body()!!
                val reposData = reposResponse.body()!!

                val repositories = reposData.map { repo ->
                    Repository(
                        name = repo.name,
                        url = repo.html_url,
                        description = repo.description
                    )
                }

                val gitHubUser = GitHubUser(
                    username = userData.login,
                    followers = userData.followers,
                    following = userData.following,
                    createdAt = userData.created_at,
                    repositories = repositories
                )

                // Cache the user data
                userCache[username] = gitHubUser
                Result.success(gitHubUser)
            } else {
                Result.failure(Exception("${userResponse.code()} User Not Found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getCachedUsers(): List<GitHubUser> = userCache.values.toList()

    fun searchUser(query: String): List<GitHubUser> =
        userCache.values.filter { it.username.contains(query, ignoreCase = true) }

    fun searchRepository(query: String): List<GitHubUser> =
        userCache.values.filter { user ->
            user.repositories.any { it.name.contains(query, ignoreCase = true) }
        }
}

