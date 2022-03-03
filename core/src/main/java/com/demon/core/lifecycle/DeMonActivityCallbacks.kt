package com.demon.core.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.demon.core.DeMonActivityResult

/**
 * @author DeMon
 * Created on 2020/7/20.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
object DeMonActivityCallbacks : Application.ActivityLifecycleCallbacks {
    private val TAG = "DeMonActivityCallbacks"

    const val DEMON_ACTIVITY_KEY = "DeMon_Activity_Key"
    val DEMON_FRAGMENT_KEY = "DeMon_Fragment_Key"

    //临时存储FragmentCallbacks
    val callbackMap = mutableMapOf<String, DeMonFragmentCallbacks>()

    //临时存储BaseActivityResult
    val resultMap = mutableMapOf<String, DeMonActivityResult<Intent, ActivityResult>>()
    override fun onActivityPaused(p0: Activity) {

    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is FragmentActivity) {
            val mapKey = activity.intent.getStringExtra(DEMON_ACTIVITY_KEY)
            Log.i(TAG, "onActivityDestroyed: mapKey=$mapKey")
            if (!mapKey.isNullOrEmpty()) {
                callbackMap[mapKey]?.let { activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(it) }
                //移除
                callbackMap.remove(mapKey)
                resultMap.remove(mapKey)
            }
        }
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        if (activity is FragmentActivity) {
            val mapKey: String = activity.javaClass.simpleName + System.currentTimeMillis()
            Log.i(TAG, "onActivityCreated: mapKey=$mapKey")
            //注册
            val fragmentCallbacks = DeMonFragmentCallbacks()
            callbackMap[mapKey] = fragmentCallbacks
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)
            val result = DeMonActivityResult(activity, ActivityResultContracts.StartActivityForResult())
            activity.intent.putExtra(DEMON_ACTIVITY_KEY, mapKey)
            resultMap[mapKey] = result
        }
    }

    override fun onActivityResumed(p0: Activity) {
    }
}