package ru.itis.tinkoff.project.network

import okhttp3.Interceptor
import okhttp3.Response

const val TEST_TOKEN =
    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsInBhc3N3b3JkIjoiJDJhJDEwJHIzcFZWMVdCN3lqdlc5" +
            "NlpCOHVnZ2ViS2dZR3NLa3lIM3pnbVg5RkpEQkRZdUZock5BYjZDIiwicm9sZSI6IlJPTEVfQ0xJRU5UIiwic3RhdGUiO" +
            "iJDT05GSVJNRUQiLCJ0eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjUwNDkyNzE1LCJpYXQiOjE2NTA0ODkxMTUsImVtYWlsIjoiYWRt" +
            "aW5AbWFpbC5jb20ifQ.0bVep29ZeqYzszYLqTo8KEH9bWB_VXD-aMmcQORL-l-Aiv_vHp7FF7PGfdxYgC7oj3t-sg50zW7k9CeN86HcNg"

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer $TEST_TOKEN")
        return chain.proceed(requestBuilder.build())
    }
}
