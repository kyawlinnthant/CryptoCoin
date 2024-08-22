package com.kyawlinnthant.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder =
            chain.request().newBuilder().addHeader(
                "x-access-token",
                BuildConfig.API_KEY,
            )
        return chain.proceed(builder.build())
    }
}
