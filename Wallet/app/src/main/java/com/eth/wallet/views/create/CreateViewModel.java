package com.eth.wallet.views.create;

import com.eth.base.BaseViewModel;
import com.kunminx.architecture.ui.page.State;


//TODO tip 4：每个页面都需单独准备一个 state-ViewModel，托管 DataBinding 绑定的 State，
// 此外，state-ViewModel 职责仅限于状态托管和保存恢复，不建议在此处理 UI 逻辑，
// UI 逻辑只适合在 Activity/Fragment 等视图控制器中完成，是 “数据驱动” 一部分，将来升级到 Jetpack Compose 更是如此。
public class CreateViewModel extends BaseViewModel {
    //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，

    //如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350
    public final State<Boolean> isLoading=new State<>(false);

    public final State<String> name=new State<>("");
}
