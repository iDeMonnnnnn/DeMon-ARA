package com.demon.core

import android.app.Application
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.demon.core.lifecycle.DeMonActivityCallbacks

/**
 * @author DeMon
 * Created on 2022/3/2.
 * E-mail idemon_liu@qq.com
 * Desc: Activity Results API
 */
object DeMonAraHelper {


    /**
     * 初始化，注册ActivityLifecycleCallbacks
     */
    @JvmStatic
    fun init(@NonNull application: Application) {
        application.registerActivityLifecycleCallbacks(DeMonActivityCallbacks)
    }

    /**
     * 获取在Activity生命周期自动注册的ActivityResult
     * Activity中请使用此方法
     */
    @JvmStatic
    fun getActivityResult(@NonNull activity: FragmentActivity): DeMonActivityResult? {
        activity.run {
            val mapKey = intent.getStringExtra(DeMonActivityCallbacks.DEMON_ACTIVITY_KEY)
            return if (!mapKey.isNullOrEmpty()) {
                DeMonActivityCallbacks.resultMap[mapKey]
            } else {
                null
            }
        }
    }

    /**
     * 获取在Fragment生命周期自动注册的ActivityResult
     * Fragment中请使用此方法
     */
    @JvmStatic
    fun getActivityResult(@NonNull fragment: Fragment): DeMonActivityResult? {
        fragment.requireActivity().run {
            val mapKey = intent.getStringExtra(DeMonActivityCallbacks.DEMON_FRAGMENT_KEY)
            return if (!mapKey.isNullOrEmpty()) {
                DeMonActivityCallbacks.resultMap[mapKey]
            } else {
                null
            }
        }
    }


}