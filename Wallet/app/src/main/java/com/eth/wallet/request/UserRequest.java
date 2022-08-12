package com.eth.wallet.request;


import android.content.Context;

import com.eth.base.data_response.DataResult;
import com.eth.base.data_response.ResponseStatus;
import com.eth.base.data_response.ResultSource;
import com.eth.wallet.database.AppDatabase;
import com.eth.wallet.database.user.User;
import com.kunminx.architecture.domain.message.MutableResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserRequest {
    private Context mContext;
    private AppDatabase appDatabase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableResult<DataResult<User>> userResult=new MutableResult<>();

    public MutableResult<DataResult<User>> getUserResult() {
        return userResult;
    }

    public UserRequest(Context mContext) {
        this.mContext = mContext;
        appDatabase = AppDatabase.getInstance(mContext);
    }


    public void createUser(String userName, String password) {
        final Disposable disposable = Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        String handledName=userName;

                        User user = appDatabase.getUserDao().getUser(userName);
                        if (user!=null){
                            String sqlName=userName+"_";
                            int nameExistCount = appDatabase.getUserDao().userExistCount(sqlName);
                            if (nameExistCount>9){
                                emitter.onError(new Exception("相同名称不能超过十个"));
                                return;
                            }
                            handledName=userName+nameExistCount;
                        }

                        user = new User();
                        user.setUserName(handledName);
                        user.setPassword(password);
                        appDatabase.getUserDao().insertAll(user);
                        emitter.onNext(user);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        userResult.setValue(new DataResult<>(user));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        userResult.setValue(new DataResult<>(null,new ResponseStatus(t.getMessage(),false, ResultSource.DATABASE)));
                    }
                });
        compositeDisposable.add(disposable);
    }
}
