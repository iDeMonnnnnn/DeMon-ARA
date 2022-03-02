package com.demon.ara

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demon.ara.databinding.ActivityActResultTwoBinding
import com.demon.corektx.extraAct
import com.demon.corektx.finishResult


class TestJumpActivity : AppCompatActivity() {
    private  val TAG = "TestJumpActivity"
    private val string by extraAct<String>("tag")

    private val time by extraAct("timestamp", 0L)

    private lateinit var binding: ActivityActResultTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActResultTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.text.text = "key={$string}\ntime={$time}"
        binding.btnFinish.setOnClickListener {
            finishResult("tag" to TAG)
        }
    }
}