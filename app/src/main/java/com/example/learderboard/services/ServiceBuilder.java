package com.example.learderboard.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static  final String URL="https://docs.google.com/forms/d/e/";

    //logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logger)
            .build();

    private static Retrofit.Builder sBuilder=new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client);
    private static Retrofit sRetrofit=sBuilder.build();
    public static <s> s buildService(Class<s> servicetype)
    {
        return sRetrofit.create(servicetype);
    }
}
