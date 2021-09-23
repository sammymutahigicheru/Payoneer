package com.sammy.bestpractice.commons;

import okhttp3.logging.HttpLoggingInterceptor;

public class LoggingInterceptor {

    public static HttpLoggingInterceptor getLogging() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }
}