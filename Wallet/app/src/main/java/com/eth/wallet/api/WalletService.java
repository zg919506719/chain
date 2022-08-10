package com.eth.wallet.api;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface WalletService {
    @POST("xxx/login")
    @FormUrlEncoded
    Call<String> createChain(@Field("reqData") String req);

//    @GET
//    Flowable<ResBaseModel> get(@Url String url, @Header("X-ZZ-Timestamp") String timestamp);
//
//
//    @POST
//    Flowable<ResBaseModel> post(@Url String url, @Body RequestBody body, @Header("X-ZZ-Timestamp") String timestamp);
//
//    @GET
//    Observable<ResBaseModel> getObservable(@Url String url, @Header("X-ZZ-Timestamp") String timestamp);
//
//
//    @POST
//    Observable<ResBaseModel> postObservable(@Url String url, @Body RequestBody body, @Header("X-ZZ-Timestamp") String timestamp);
//
//    @POST
//    Flowable<ResBaseModel> postLog(@Url String url, @Body RequestBody body, @Header("X-ZZ-Timestamp") String timestamp,@Header("ZZ-POS-LOG") String ZZ_POS_LOG);

}
