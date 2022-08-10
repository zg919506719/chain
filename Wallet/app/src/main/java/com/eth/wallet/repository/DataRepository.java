package com.eth.wallet.repository;

import com.eth.base.data_response.DataResult;
import com.eth.base.data_response.ResponseStatus;
import com.eth.base.data_response.ResultSource;
import com.eth.wallet.api.WalletService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.eth.wallet.api.APIS.BASE_URL;

public class DataRepository {
    private static final DataRepository S_REQUEST_MANAGER = new DataRepository();
    private Call<String> createChainCall;

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        return S_REQUEST_MANAGER;
    }

    private final Retrofit retrofit;

    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * TODO 模拟登录的网络请求
     *
     * @param req    ui 层填写的用户信息
     * @param result 模拟网络请求返回的 token
     */
    public void createChain(String req, DataResult.Result<String> result) {
        createChainCall = retrofit.create(WalletService.class).createChain(req);
        createChainCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ResponseStatus responseStatus = new ResponseStatus(String.valueOf(response.code()), response.isSuccessful()
                        , ResultSource.NETWORK);

                result.onResult(new DataResult<>(response.body(),responseStatus));

                createChainCall=null;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                result.onResult(new DataResult<>(null,new ResponseStatus(t.getMessage(),false,ResultSource.NETWORK)));
                createChainCall=null;
            }
        });
    }

    public void cancelLogin() {
        if (createChainCall != null && !createChainCall.isCanceled()) {
            createChainCall.cancel();
            createChainCall = null;
        }
    }
}
