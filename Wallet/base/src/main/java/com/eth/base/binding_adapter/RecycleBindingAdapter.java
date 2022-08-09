package com.eth.base.binding_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eth.base.utils.ScreenUtils;
import com.eth.base.widgets.DividerGridItemDecoration;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleBindingAdapter {

    @BindingAdapter(value = {"itemColorStr", "itemWidth", "adapter", "listData"}, requireAll = false)
    public static void setRecycleInfo(RecyclerView recyclerView, String itemColorStr, int itemWidth, BaseDataBindingAdapter adapter, List listData) {
        try {
            if (recyclerView != null) {

                if (recyclerView.getAdapter() == null && adapter != null) {
                    recyclerView.setAdapter(adapter);
                }

                recyclerView.setHasFixedSize(true);
                if (itemWidth != 0 && recyclerView.getItemDecorationCount() == 0) {
                    Context mContext = recyclerView.getContext();
                    View itemDecoration = new View(mContext);
                    int size = (int) ScreenUtils.getIntToDip(mContext, itemWidth);
                    itemDecoration.setLayoutParams(new ViewGroup.LayoutParams(size, size));
                    if (itemColorStr == null) {
                        itemDecoration.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        itemDecoration.setBackgroundColor(Color.parseColor(itemColorStr));
                    }
                    recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, GridLayoutManager.VERTICAL, itemDecoration));
                }
                if (adapter != null && listData != null) {
                    adapter.notifyData(listData);
                }
            }
        } catch (Exception e) {
            Toast.makeText(recyclerView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
