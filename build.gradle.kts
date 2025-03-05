plugins {
    kotlin("jvm") version "1.8.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation(kotlin("stdlib"))

    // Retrofit for network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson Converter for Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Kotlin Coroutines for asynchronous operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Optional: OkHttp Logging Interceptor for debugging network calls
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
}

