package ru.itis.tinkoff.project.network

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
