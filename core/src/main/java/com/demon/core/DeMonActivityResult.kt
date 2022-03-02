package com.demon.core

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
class DeMonActivityResult(caller: ActivityResultCaller, contract: ActivityResultContract<Intent, ActivityResult>) {

    private var launcher: ActivityResultLauncher<Intent>? = caller.registerForActivityResult(contract) {
        callback?.onActivityResult(it)
    }

    private var callback: ActivityResultCallback<ActivityResult>? = null

    fun launch(input: Intent, callback: ActivityResultCallback<ActivityResult>?) {
        this.callback = callback
        launcher?.launch(input)
    }


}