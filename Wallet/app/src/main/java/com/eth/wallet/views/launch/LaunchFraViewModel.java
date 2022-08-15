package com.eth.wallet.views.launch;

import com.eth.base.BaseViewModel;
import com.eth.wallet.request.bean.UserItem;
import com.kunminx.architecture.ui.page.State;

import java.util.ArrayList;
import java.util.List;

public class LaunchFraViewModel extends BaseViewModel {
    public State<String> userName=new State<>("这个是测试得launch");

    public State<List<UserItem>> linkData=new State<>(new ArrayList<>());
}
