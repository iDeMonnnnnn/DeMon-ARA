package com.demon.ara

import android.app.Application
import com.demon.core.DeMonAraHelper

/**
 * @author DeMon
 * Created on 2022/3/2.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DeMonAraHelper.init(this)
    }
}