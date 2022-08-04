package com.eth.wallet.utils

import timber.log.Timber

/**
 * 生产的log处理
 */
class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        return
    }
}