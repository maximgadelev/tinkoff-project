package ru.itis.tinkoff.project.network

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.tinkoff.project.data.repository.TokenRepository
import javax.net.ssl.HttpsURLConnection

private const val AUTHORIZATION = "Authorization"

class AuthInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(AUTHORIZATION, "Bearer ${"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsInBhc3N3b3JkIjoiJDJhJDEwJHIzcFZWMVdCN3lqdlc5NlpCOHVnZ2ViS2dZR3NLa3lIM3pnbVg5RkpEQkRZdUZock5BYjZDIiwicm9sZSI6IlJPTEVfQ0xJRU5UIiwic3RhdGUiOiJDT05GSVJNRUQiLCJ0eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjUxOTE4NTQxLCJpYXQiOjE2NTE5MTQ5NDEsImVtYWlsIjoiYWRtaW5AbWFpbC5jb20ifQ.uDHdHwL3OBDAKmQXxtDEBZdM3vpKgKVUy5ec9LPDgDO1Bj2qiD0gsJnoNCFZazr6vnUrCowTRCTqvdgMj8M8lA"}")
        var response = chain.proceed(requestBuilder.build())
//        if (response.code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
//            response.close()
//            runBlocking {
//                try {
//                    tokenRepository.refreshToken()
//                } catch (ex: Exception) {
//                    tokenRepository.loginAndGetToken(
//                        "admin@mail.com",
//                        "qwerty"
//                    ) // т.к рефреш токен может устареть,то опять требует залогинится
//                }
//            }
//            requestBuilder.removeHeader(AUTHORIZATION)
//                .addHeader(AUTHORIZATION, "Bearer ${tokenRepository.getToken()}")
//            response = chain.proceed(requestBuilder.build())
//        }
        return response
    }
}
