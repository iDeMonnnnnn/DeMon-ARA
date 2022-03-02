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


    @JvmStatic
    fun init(@NonNull application: Application) {
        application.registerActivityLifecycleCallbacks(DeMonActivityCallbacks)
    }


    @JvmStatic
    fun getBaseActivityResult(@NonNull activity: FragmentActivity): DeMonActivityResult? {
        activity.run {
            val mapKey = intent.getStringExtra(DeMonActivityCallbacks.DEMON_ACTIVITY_KEY)
            return if (!mapKey.isNullOrEmpty()) {
                DeMonActivityCallbacks.resultMap[mapKey]
            } else {
                null
            }
        }
    }

    @JvmStatic
    fun getBaseActivityResult(@NonNull fragment: Fragment): DeMonActivityResult? {
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