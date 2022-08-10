package com.eth.wallet.message.event;

import com.kunminx.architecture.domain.event.Event;

/**
 * Create by KunMinX at 2022/7/4
 */
public class Messages extends Event<Messages.Param, Messages.Result> {
    public final static int EVENT_CLOSE_ACTIVITY_IF_ALLOWED = 1;

    public Messages(int eventId) {
        this.eventId = eventId;
        this.param = new Param();
        this.result = new Result();
    }

    public static class Param {
    }

    public static class Result {
    }
}
