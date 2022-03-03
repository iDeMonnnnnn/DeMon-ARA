package com.demon.core

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract

/**
 * @author DeMon
 * Created on 2022/3/1.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
class DeMonActivityResult<I, O>(caller: ActivityResultCaller, contract: ActivityResultContract<I, O>) {

    /**
     * 直接点击返回键或者直接finish是否会触发返回回调
     * 用于处理一些特殊情况：如返回刷新等
     * 注意此时回调返回的值或者{ActivityResult#getData()}应该为空，需要做好判空处理
     */
    private var isNeedBack = false

    private var launcher: ActivityResultLauncher<I>? = caller.registerForActivityResult(contract) {
        if (isNeedBack) {
            callback?.onActivityResult(it)
        } else {
            if (it != null && it is ActivityResult && it.resultCode == Activity.RESULT_OK) {
                callback?.onActivityResult(it)
            } else {
                callback?.onActivityResult(it)
            }
        }
    }

    private var callback: ActivityResultCallback<O>? = null


    @JvmOverloads
    fun launch(input: I, isNeedBack: Boolean = false, callback: ActivityResultCallback<O>?) {
        this.callback = callback
        this.isNeedBack = isNeedBack
        launcher?.launch(input)
    }


}