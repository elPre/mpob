package com.mpob.base.api;

import android.content.Context;

import com.mpob.base.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HOLV on 15,March,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class Service {

    public <T> T createService(Context context, final Class<T> cls, final String endPoint) {
        return createRetrofitService(context, cls, endPoint, true, -1);
    }

    private <T> T createRetrofitService(Context context, final Class<T> cls, final String endPoint, boolean useHttps, int timoutSeconds) {

        Cache cache = new Cache(new File(context.getCacheDir(), "http"), Constants.SIZE_OF_CACHE);
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder()
                .cache(cache)
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();
                        HttpUrl url = request.url()
                                .newBuilder()
                                .addQueryParameter("format", "json")
                                .build();

                        request = request.newBuilder()
                                .url(url)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new Interceptor() {

                    // Override Cache-Control so that Okhttp will cache it on our terms. This is a NetworkInterceptor to ensure it runs before caching.
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response originalResponse = chain.proceed(request);
                        return originalResponse;
                    }
                });

        if (timoutSeconds >= 0) {
            okHttpBuilder
                    .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS);
        }

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(constructBaseUrl(useHttps, endPoint))
                .client(okHttpBuilder.build())
                .build()
                .create(cls);
    }

    private String constructBaseUrl(boolean useHttps, String endpoint) {
        return (useHttps ? "https://" : "http://") + endpoint;
    }

}
