package com.demon.ara.ghost

import android.content.Intent
import androidx.fragment.app.FragmentActivity

/**
 * @author DeMon
 * Created on 2022/3/4.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
object Ghost {
    var requestCode = 0
        set(value) {
            field = if (value >= Integer.MAX_VALUE) 1 else value
        }

    inline fun launchActivityForResult(
        starter: FragmentActivity?,
        intent: Intent,
        crossinline callback: ((result: Intent?) -> Unit)
    ) {
        starter ?: return
        val fm = starter.supportFragmentManager
        val fragment = GhostFragment()
        fragment.init(++requestCode, intent) { result ->
            callback(result)
            fm.beginTransaction().remove(fragment).commitAllowingStateLoss()
        }
        fm.beginTransaction().add(fragment, GhostFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }

}


