/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eth.wallet.config;

import android.os.Environment;

import com.eth.base.utils.Utils;
import com.eth.wallet.chain.ChainETH;
import com.kunminx.architecture.data.config.keyvalue.KeyValueBoolean;
import com.kunminx.architecture.data.config.keyvalue.KeyValueInteger;
import com.kunminx.architecture.data.config.keyvalue.KeyValueSerializable;
import com.kunminx.architecture.data.config.keyvalue.KeyValueString;

/**
 * Create by KunMinX at 18/9/28
 */
public class Configs {
    public static final String COVER_PATH = Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    public static final String TOKEN = "token";

    //TODO tip 1：消除 Android 项目 KeyValue 样板代码，让 key、value、get、put、init 缩减为一，不再 KV 爆炸。
    //如这么说无体会，详见 https://juejin.cn/post/7121955840319291428

    public static final KeyValueString accountId = new KeyValueString("accountId");
    public static final KeyValueBoolean isLogin = new KeyValueBoolean("isLogin");
    public static final KeyValueInteger alive = new KeyValueInteger("alive");
    public static final KeyValueSerializable<ChainETH> chainEth = new KeyValueSerializable<>("ChainETH");
}
