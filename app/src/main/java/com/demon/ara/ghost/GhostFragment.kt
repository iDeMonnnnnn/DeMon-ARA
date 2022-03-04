package com.demon.ara.ghost

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * @author DeMon
 * Created on 2022/3/4.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
class GhostFragment : Fragment() {

    private var requestCode = -1
    private var intent: Intent? = null
    private var callback: ((result: Intent?) -> Unit)? = null

    fun init(requestCode: Int, intent: Intent, callback: ((result: Intent?) -> Unit)) {
        this.requestCode = requestCode
        this.intent = intent
        this.callback = callback
    }

    private var activityStarted = false

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (!activityStarted) {
            activityStarted = true
            intent?.let { startActivityForResult(it, requestCode) }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!activityStarted) {
            activityStarted = true
            intent?.let { startActivityForResult(it, requestCode) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == this.requestCode) {
            callback?.let { it1 -> it1(data) }
        }
    }

    override fun onDetach() {
        super.onDetach()
        intent = null
        callback = null
    }
}