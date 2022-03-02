package com.demon.ara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.demon.ara.databinding.FragmentAraBinding
import com.demon.corektx.forActivityResult
import com.demon.corektx.pairIntent

/**
 * @author DeMon
 * Created on 2022/3/2.
 * E-mail idemon_liu@qq.com
 * Desc: 测试Fragment
 */
class AraFragment : DialogFragment() {
    private val TAG = "AraFragment"

    private var _binding: FragmentAraBinding? = null
    private val binding: FragmentAraBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAraBinding.inflate(inflater, container, false)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn.setOnClickListener {
            forActivityResult(
                pairIntent<TestJumpActivity>(
                    "tag" to TAG,
                    "timestamp" to System.currentTimeMillis()
                ),
                isCanBack = false
            ) {
                val str = it?.getStringExtra("tag") ?: ""
                binding.text.text = "跳转页面返回值：$str"
            }
        }

    }

    fun showAllowingState(manager: FragmentManager, tagStr: String? = null) {
        manager.beginTransaction().add(this, tagStr ?: tag).commitAllowingStateLoss()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}