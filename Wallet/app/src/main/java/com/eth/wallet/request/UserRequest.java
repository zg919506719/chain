package com.eth.wallet.request;


import android.content.Context;
import android.provider.ContactsContract;

import com.eth.base.data_response.DataResult;
import com.eth.base.data_response.ResponseStatus;
import com.eth.base.data_response.ResultSource;
import com.eth.wallet.database.AppDatabase;
import com.eth.wallet.database.user.User;
import com.eth.wallet.request.bean.UserItem;
import com.kunminx.architecture.domain.message.MutableResult;
import com.kunminx.linkage.bean.DefaultGroupedItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserRequest {
    private Context mContext;
    private AppDatabase appDatabase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    //创建结果
    public MutableResult<DataResult<User>> userCreateResult = new MutableResult<>();
    //列表数据
    public MutableResult<DataResult<List<UserItem>>> recycleData=new MutableResult<>();


    public UserRequest(Context mContext) {
        this.mContext = mContext;
        appDatabase = AppDatabase.getInstance(mContext);
    }


    public void createUser(String userName, String password) {
        final Disposable disposable = Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        String handledName = userName;

                        User user = appDatabase.getUserDao().getUser(userName);
                        if (user != null) {
                            String sqlName = userName + "_";
                            int nameExistCount = appDatabase.getUserDao().userExistCount(sqlName);
                            if (nameExistCount > 9) {
                                emitter.onError(new Exception("相同名称不能超过十个"));
                                return;
                            }
                            handledName = userName + nameExistCount;
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
                        userCreateResult.setValue(new DataResult<>(user));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        userCreateResult.setValue(new DataResult<>(null, new ResponseStatus(t.getMessage(), false, ResultSource.DATABASE)));
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getAllUsers() {
        compositeDisposable.add(Observable.create(new ObservableOnSubscribe<List<User>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<User>> emitter) throws Exception {
                        emitter.onNext(appDatabase.getUserDao().getAll());
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        final ArrayList<UserItem> defaultGroupedItems = new ArrayList<>();
                        defaultGroupedItems.add(new UserItem(true, "创建用户"));
//        String title, String group, String content
                        DefaultGroupedItem.ItemInfo itemInfo = new DefaultGroupedItem.ItemInfo("起一个名字和密码吧", "创建用户");
                        defaultGroupedItems.add(new UserItem(itemInfo));

                        for (User user : users) {
                            final UserItem userItem = new UserItem(true, user.getUserName());
                            userItem.setUser(user);
                            defaultGroupedItems.add(userItem);
                            //        String title, String group, String content
                            defaultGroupedItems.add(new UserItem(new DefaultGroupedItem.ItemInfo("创建/导入eth", user.getUserName())));
                        }

                        recycleData.setValue(new DataResult<>(defaultGroupedItems));
                    }
                }));
    }

    public void clearAll() {
        compositeDisposable.clear();
    }
}
