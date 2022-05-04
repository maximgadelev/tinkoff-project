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
        requestBuilder.addHeader(AUTHORIZATION, "Bearer ${tokenRepository.getToken()}")
        var response = chain.proceed(requestBuilder.build())
        if (response.code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
            response.close()
            runBlocking {
                try {
                    tokenRepository.refreshToken()
                } catch (ex: Exception) {
                    tokenRepository.loginAndGetToken(
                        "admin@mail.com",
                        "qwerty"
                    ) // т.к рефреш токен может устареть,то опять требует залогинится
                }
            }
            requestBuilder.removeHeader(AUTHORIZATION)
                .addHeader(AUTHORIZATION, "Bearer ${tokenRepository.getToken()}")
            response = chain.proceed(requestBuilder.build())
        }
        return response
    }
}
