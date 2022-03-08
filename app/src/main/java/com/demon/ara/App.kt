package com.demon.ara

import android.app.Application
import com.demon.core.DeMonAraHelper
import com.demon.qfsolution.QFHelper

/**
 * @author DeMon
 * Created on 2022/3/2.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        QFHelper.init(this)
        DeMonAraHelper.init(this)
    }
}