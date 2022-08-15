package com.eth.wallet.binding;

import android.view.View;

import com.eth.base.utils.ToastUtils;
import com.eth.wallet.R;
import com.eth.wallet.databinding.FragmentLaunchBinding;
import com.eth.wallet.request.bean.UserItem;
import com.google.android.material.snackbar.Snackbar;
import com.kunminx.linkage.LinkageRecyclerView;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.kunminx.linkage.bean.DefaultGroupedItem;
import com.kunminx.linkage.defaults.DefaultLinkagePrimaryAdapterConfig;
import com.kunminx.linkage.defaults.DefaultLinkageSecondaryAdapterConfig;

import java.util.List;

import androidx.databinding.BindingAdapter;

public class LinkRecycleAdapter {
    @BindingAdapter("recycle_data")
    public static void setAdapter(LinkageRecyclerView linkageRecyclerView, List<UserItem> defaultGroupedItems){
        linkageRecyclerView.init(defaultGroupedItems);
        linkageRecyclerView.setDefaultOnItemBindListener(new DefaultLinkagePrimaryAdapterConfig.OnPrimaryItemClickListener() {
            @Override
            public void onItemClick(LinkagePrimaryViewHolder linkagePrimaryViewHolder, View view, String s) {
                ToastUtils.showShortToast(linkageRecyclerView.getContext(),s);
            }
        }, new DefaultLinkagePrimaryAdapterConfig.OnPrimaryItemBindListener() {
            @Override
            public void onBindViewHolder(LinkagePrimaryViewHolder linkagePrimaryViewHolder, String s) {

            }
        }, new DefaultLinkageSecondaryAdapterConfig.OnSecondaryItemBindListener() {
            @Override
            public void onBindViewHolder(LinkageSecondaryViewHolder secondaryHolder, BaseGroupedItem<DefaultGroupedItem.ItemInfo> item) {
                secondaryHolder.getView(R.id.level_2_item).setOnClickListener(v -> {
                    Snackbar.make(v, item.info.getTitle(), Snackbar.LENGTH_SHORT).show();
                });
            }
        }, new DefaultLinkageSecondaryAdapterConfig.OnSecondaryHeaderBindListener() {
            @Override
            public void onBindHeaderViewHolder(LinkageSecondaryHeaderViewHolder linkageSecondaryHeaderViewHolder, BaseGroupedItem<DefaultGroupedItem.ItemInfo> baseGroupedItem) {

            }
        }, new DefaultLinkageSecondaryAdapterConfig.OnSecondaryFooterBindListener() {
            @Override
            public void onBindFooterViewHolder(LinkageSecondaryFooterViewHolder linkageSecondaryFooterViewHolder, BaseGroupedItem<DefaultGroupedItem.ItemInfo> baseGroupedItem) {

            }
        });
    }
}
