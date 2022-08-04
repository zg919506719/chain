package com.eth.wallet.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Task extends RealmObject {
    @PrimaryKey
    private String name;

    @Required
    String status=TaskStatus.Open.name();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status.displayName;
    }

    public Task(String name) {
        this.name = name;
    }

    public Task() {
    }
}

