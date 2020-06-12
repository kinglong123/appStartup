package com.nd.hy.ele.idpmodule.client;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lanjl on 2020/5/27.
 */
public  class Retry implements Interceptor {
    public int maxRetry;//最大重试次数
    private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
    public Retry(int maxRetry) {
        this.maxRetry = maxRetry;
    }
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.i("Retry","num:"+retryNum);
        while ((response.code() == 401)&& retryNum < maxRetry){
            retryNum++;
            Log.i("Retry","num:"+retryNum);

//            HttpUrl httpUrl = request.url().newBuilder()
//                    .addQueryParameter("token", "tokenValue")
//                    .build();


//            request = request.newBuilder().header("access_token", TicketUtil.getAccess()).build();
//            request.newBuilder().
//            request.newBuilder().
//            if (request.method().equals("POST")) {
//                MediaType mediaType = request.body().contentType();
//
//                mediaType.subtype();
//
//                request.body().
//
//            }
            //更新token
            //替换

            response = chain.proceed(request);
        }
        retryNum =0;
//        while (!response.isSuccessful() && retryNum < maxRetry) {
//            retryNum++;
//            Log.i("Retry","num:"+retryNum);
//            response = chain.proceed(request);
//        }
        return response;
    }
}