package ru.itis.tinkoff.project.network

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.tinkoff.project.features.common.data.TokenRepository
import javax.net.ssl.HttpsURLConnection

class AuthInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer ${tokenRepository.getToken()}")
        var response = chain.proceed(requestBuilder.build())
        if (response.code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
            response.close()
            runBlocking {
                tokenRepository.refreshToken()
                requestBuilder.removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer ${tokenRepository.getToken()}")
                response = chain.proceed(requestBuilder.build())
            }
        }
        return response
    }
}
