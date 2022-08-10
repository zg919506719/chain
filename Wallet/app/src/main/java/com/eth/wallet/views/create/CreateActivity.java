package com.eth.wallet.views.create;

import com.eth.base.BaseActivity;
import com.eth.wallet.BR;
import com.eth.wallet.R;
import com.eth.wallet.message.PageMessenger;
import com.eth.wallet.message.event.Messages;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import androidx.lifecycle.Observer;

import static com.eth.wallet.message.event.Messages.EVENT_CLOSE_ACTIVITY_IF_ALLOWED;

public class CreateActivity extends BaseActivity {
    private CreateViewModel mStates;
    private PageMessenger mMessenger;

    @Override
    protected void initViewModel() {
        mStates = getActivityScopeViewModel(CreateViewModel.class);
        mMessenger = getApplicationScopeViewModel(PageMessenger.class);

        //TODO tip 8: 此处演示使用 "唯一可信源" MVI-Dispatcher input-output 接口完成消息收发

        //如这么说无体会，详见《领域层设计》篇拆解 https://juejin.cn/post/7117498113983512589
        mMessenger.output(this, new Observer<Messages>() {
            @Override
            public void onChanged(Messages messages) {
                switch (messages.eventId){
                    case EVENT_CLOSE_ACTIVITY_IF_ALLOWED:
                        break;
                }
            }
        });
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        //TODO tip 2: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这方式，彻底解决 View 实例 Null 安全一致性问题，
        // 如此，View 实例 Null 安全性将和基于函数式编程思想的 Jetpack Compose 持平。
        // 而 DataBindingConfig 就是在这样背景下，用于为 base 页面 DataBinding 提供绑定项。

        // 如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

        return new DataBindingConfig(R.layout.activity_create, BR.vm, mStates)
                .addBindingParam(BR.click, new ClickProxy());
    }

    public class ClickProxy {
        public void login() {
            final Messages event = new Messages(EVENT_CLOSE_ACTIVITY_IF_ALLOWED);
            event.param=new Messages.Param();
            mMessenger.input(event);
        }
    }
}
