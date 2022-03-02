package com.demon.corektx

import android.app.Activity
import android.content.Intent
import android.os.BaseBundle
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.demon.corektx.IntentFieldMethod.internalMap
import java.io.Serializable
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author DeMon
 * Created on 2020/7/22.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
fun Intent.putExtras(vararg extras: Pair<String, Any?>): Intent {
    if (extras.isEmpty()) return this
    extras.forEach { (key, value) ->
        value ?: return@forEach
        when (value) {
            is Bundle -> this.putExtra(key, value)
            is Boolean -> this.putExtra(key, value)
            is BooleanArray -> this.putExtra(key, value)
            is Byte -> this.putExtra(key, value)
            is ByteArray -> this.putExtra(key, value)
            is Char -> this.putExtra(key, value)
            is CharArray -> this.putExtra(key, value)
            is String -> this.putExtra(key, value)
            is CharSequence -> this.putExtra(key, value)
            is Double -> this.putExtra(key, value)
            is DoubleArray -> this.putExtra(key, value)
            is Float -> this.putExtra(key, value)
            is FloatArray -> this.putExtra(key, value)
            is Int -> this.putExtra(key, value)
            is IntArray -> this.putExtra(key, value)
            is Long -> this.putExtra(key, value)
            is LongArray -> this.putExtra(key, value)
            is Short -> this.putExtra(key, value)
            is ShortArray -> this.putExtra(key, value)
            is Parcelable -> this.putExtra(key, value)
            is Serializable -> this.putExtra(key, value)
            is Array<*> -> {
                @Suppress("UNCHECKED_CAST")
                when {
                    value.isArrayOf<String>() -> {
                        this.putStringArrayListExtra(key, value as ArrayList<String?>)
                    }
                    value.isArrayOf<CharSequence>() -> {
                        this.putCharSequenceArrayListExtra(key, value as ArrayList<CharSequence?>)
                    }
                    value.isArrayOf<Parcelable>() -> {
                        this.putParcelableArrayListExtra(key, value as ArrayList<out Parcelable?>)
                    }
                }
            }
            else -> {
                throw IllegalArgumentException("Not support $value type ${value.javaClass}..")
            }
        }
    }
    return this
}

class ActivityExtras<T>(private val extraName: String, private val defaultValue: T) : ReadWriteProperty<Activity, T> {
    /**
     * getExtras字段对应的值
     */
    private var extra: T? = null
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        // 如果extra不为空则返回extra
        // 如果extra是空的，则判断intent的参数的值，如果值不为空，则将值赋予extra，并且返回
        // 如果intent参数的值也为空，则返回defaultValue，并且将值赋予extra
        return extra ?: thisRef.intent?.get<T>(extraName)?.also { extra = it }
        ?: defaultValue.also { extra = it }
    }

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: T) {
        extra = value
    }
}

/**
 * 获取Intent参数，Fragment
 * 示例同[ActivityExtras]
 */
class FragmentExtras<T>(private val extraName: String, private val defaultValue: T) : ReadWriteProperty<Fragment, T> {

    /**
     * getExtras字段对应的值
     */
    private var extra: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        // 如果extra不为空则返回extra
        // 如果extra是空的，则判断intent的参数的值，如果值不为空，则将值赋予extra，并且返回
        // 如果intent参数的值也为空，则返回defaultValue，并且将值赋予extra
        return extra ?: thisRef.arguments?.get<T>(extraName)?.also { extra = it }
        ?: defaultValue.also { extra = it }
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        extra = value
    }
}

fun <T> extraFrag(extraName: String): FragmentExtras<T?> = FragmentExtras(extraName, null)

fun <T> extraFrag(extraName: String, defaultValue: T): FragmentExtras<T> = FragmentExtras(extraName, defaultValue)


fun <T> extraAct(extraName: String): ActivityExtras<T?> = ActivityExtras(extraName, null)

fun <T> extraAct(extraName: String, defaultValue: T): ActivityExtras<T> = ActivityExtras(extraName, defaultValue)

/**
 * [Intent]的扩展方法，此方法可无视类型直接获取到对应值
 * 如getStringExtra()、getIntExtra()、getSerializableExtra()等方法通通不用
 * 可以直接通过此方法来获取到对应的值，例如：
 * <pre>
 *     var mData: List<String>? = null
 *     mData = intent.get("Data")
 * </pre>
 * 而不用显式强制转型
 *
 * @param key 对应的Key
 * @return 对应的Value
 */
fun <O> Intent?.get(key: String, defaultValue: O? = null) =
    this?.internalMap()?.get(key) as? O ?: defaultValue

/**
 * 作用同Intent.[get]
 */
fun <O> Bundle?.get(key: String, defaultValue: O? = null) =
    this?.internalMap()?.get(key) as? O ?: defaultValue

/**
 * 不报错执行
 */
inline fun <T, R> T.runSafely(block: (T) -> R) = try {
    block(this)
} catch (e: Exception) {
    e.printStackTrace()
    null
}

internal object IntentFieldMethod {
    private val bundleClass =
        (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) BaseBundle::class else Bundle::class).java

    private val mExtras: Field? by lazy {
        Intent::class.java.getDeclaredField("mExtras").also { it.isAccessible = true }
    }

    private val mMap: Field? by lazy {
        runSafely {
            bundleClass.getDeclaredField("mMap").also {
                it.isAccessible = true
            }
        }
    }

    private val unparcel: Method? by lazy {
        runSafely {
            bundleClass.getDeclaredMethod("unparcel").also {
                it.isAccessible = true
            }
        }
    }

    internal fun Intent.internalMap() = runSafely {
        mMap?.get((mExtras?.get(this) as? Bundle).also {
            it?.run { unparcel?.invoke(this) }
        }) as? Map<String, Any?>
    }

    internal fun Bundle.internalMap() = runSafely {
        unparcel?.invoke(it)
        mMap?.get(it) as? Map<String, Any?>
    }
}




