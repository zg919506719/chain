package com.eth.wallet.request.bean;

import com.eth.wallet.database.user.User;
import com.kunminx.linkage.bean.DefaultGroupedItem;

public class UserItem extends DefaultGroupedItem {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public UserItem(ItemInfo item) {
        super(item);
    }
}
