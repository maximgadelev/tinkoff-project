package ru.itis.tinkoff.project.network

import okhttp3.Interceptor
import okhttp3.Response

const val TEST_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsInBhc3N3b3JkIjoiJDJhJDEwJHIzcFZWMVdCN3l" +
        "qdlc5NlpCOHVnZ2ViS2dZR3NLa3lIM3pnbVg5RkpEQkRZdUZock5BYjZDIiwicm9sZSI6IlJPTEVfQ" +
        "0xJRU5UIiwic3RhdGUiOiJDT05GSVJNRUQiLCJleHAiOjE2NDk5OTg5MzMsImlhdCI6MTY0OTg0ODczNywiZW1haWwiOiJ" +
        "hZG1pbkBtYWlsLmNvbSJ9.aTtYegXvzDkXfCsFRKUjQhf5Fy9ogo5KPtP5MK" +
        "uFDlypwb0TbIBJqOS18Gy7aTazueTRj267JNXfyP9-GJ53zA"

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer $TEST_TOKEN")
        return chain.proceed(requestBuilder.build())
    }
}
