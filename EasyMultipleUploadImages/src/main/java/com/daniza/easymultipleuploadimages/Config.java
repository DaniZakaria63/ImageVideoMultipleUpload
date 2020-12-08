package com.daniza.easymultipleuploadimages;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Config {
    public static final int READ_EXTERNAL_PERMISSION_CODE=3;
    public static final int INTERNET_PERMISSION_CODE=6;
    public static final int SELECT_MULTIPLE_IMAGE_UPLOAD_PERMISSION_CODE=9;
    public static final int SELECT_MULTIPLE_VIDEO_UPLOAD_PERMISSION_CODE=33;
    public static final int SELECT_MULTIPLE_IMAGE_VIDEO_UPLOAD_PERMISSION_CODE=66;

    public static OkHttpClient getService(){
        return new OkHttpClient().newBuilder()
                .connectTimeout(240, TimeUnit.SECONDS)
                .readTimeout(240, TimeUnit.SECONDS)
                .writeTimeout(240, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
    }
}
