package com.demon.core.lifecycle

import android.content.Context
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.demon.core.DeMonActivityResult
import com.demon.core.lifecycle.DeMonActivityCallbacks.DEMON_FRAGMENT_KEY

/**
 * @author DeMon
 * Created on 2022/3/1.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
class DeMonFragmentCallbacks : FragmentManager.FragmentLifecycleCallbacks() {
    private val TAG = "DeMonFragmentCallbacks"
    override fun onFragmentAttached(fm: FragmentManager, fragment: Fragment, context: Context) {
        super.onFragmentAttached(fm, fragment, context)
        val mapKey: String = fragment.javaClass.simpleName + System.currentTimeMillis()
        Log.i(TAG, "onFragmentAttached: mapKey=$mapKey")
        val result = DeMonActivityResult(fragment, ActivityResultContracts.StartActivityForResult())
        fragment.requireActivity().intent.putExtra(DEMON_FRAGMENT_KEY, mapKey)
        DeMonActivityCallbacks.resultMap[mapKey] = result
    }


    override fun onFragmentDetached(fm: FragmentManager, fragment: Fragment) {
        super.onFragmentDetached(fm, fragment)
        val mapKey = fragment.requireActivity().intent.getStringExtra(DEMON_FRAGMENT_KEY)
        Log.i(TAG, "onFragmentDetached: mapKey=$mapKey")
        if (!mapKey.isNullOrEmpty()) {
            DeMonActivityCallbacks.resultMap.remove(mapKey)
        }
    }
}