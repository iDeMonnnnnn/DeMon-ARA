package com.demon.ara

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.demon.ara.databinding.ActivityBenchmarkBinding
import com.demon.ara.ghost.Ghost
import com.demon.corektx.forActivityResult
import com.google.android.material.button.MaterialButton

class BenchmarkActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityBenchmarkBinding

    private var TIMES = 100

    private var times = 0
    private var startTime = 0L

    private lateinit var testIntent: Intent

    val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        replay()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenchmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testIntent = Intent(this, TestJumpActivity::class.java).putExtra("benchmark", true)
        binding.run {
            btn.setOnClickListener(this@BenchmarkActivity)
            btn0.setOnClickListener(this@BenchmarkActivity)
            btn1.setOnClickListener(this@BenchmarkActivity)
            btn2.setOnClickListener(this@BenchmarkActivity)
        }
    }

    private var btnId = 0
    override fun onClick(p0: View) {
        btnId = p0.id
        times = TIMES
        startTime = System.currentTimeMillis()
        replay()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 0x001) {
            replay()
        }
    }

    private fun replay() {
        if (times > 0) {
            times--
            when (btnId) {
                R.id.btn -> {
                    startActivityForResult(testIntent, 0x001)
                }
                R.id.btn0 -> {
                    Ghost.launchActivityForResult(this, testIntent) {
                        replay()
                    }
                }
                R.id.btn1 -> {
                    result.launch(testIntent)
                }
                R.id.btn2 -> {
                    forActivityResult(testIntent) {
                        replay()
                    }
                }
            }
        } else {
            val endTime = System.currentTimeMillis()
            val btnText = findViewById<MaterialButton>(btnId).text.toString()
            binding.time.text = "$btnText${TIMES}次测试：\n开始时间：$startTime\n结束时间: $endTime\n耗时：${endTime - startTime}"
        }
    }
}