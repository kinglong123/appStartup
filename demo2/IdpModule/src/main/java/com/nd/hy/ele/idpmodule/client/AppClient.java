package com.nd.hy.ele.idpmodule.client;

import com.nd.hy.ele.idpmodule.api.TicketApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lanjl on 2020/5/27.
 */
public enum  AppClient {
    INSTANCE;
    protected static String URL = "https://uc-gateway.beta.101.com";

    private  TicketApi api;

    public  TicketApi getTicketApi() {
        if (api == null) {

            api = buildApi(URL, TicketApi.class);
        }
        return api;
    }

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Retry(5))
//                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
//                .addNetworkInterceptor(new Retry(5))
                .build();
        return client;
    }

    public  <T> T buildApi(String url, Class<T> clazz) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(
                        ObjectMapperUtils
                                .getMapperInstance()))

                .client(getOkHttpClient())
                .build();

        return mRetrofit.create(clazz);
    }

}
