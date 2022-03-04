package com.demon.ara

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demon.ara.databinding.ActivityMainBinding
import com.demon.ara.java.JavaActivity
import com.demon.corektx.forActivityResult
import com.demon.corektx.pairIntent
import com.demon.corektx.toActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {
            btn.setOnClickListener {
                forActivityResult(
                    pairIntent<TestJumpActivity>(
                        "tag" to TAG,
                        "timestamp" to System.currentTimeMillis()
                    ),
                    isCanBack = false
                ) {
                    val str = it?.getStringExtra("tag") ?: ""
                    text.text = "跳转页面返回值：$str"
                }
            }

            btn0.setOnClickListener {
                AraFragment().showAllowingState(supportFragmentManager, TAG)
            }


            btn1.setOnClickListener {
                val intent = Intent(this@MainActivity,JavaActivity::class.java)
                forActivityResult(intent) {
                    val str = it?.getStringExtra("tag") ?: ""
                    text.text = "跳转页面返回值：$str"
                }
            }

            btn2.setOnClickListener {
                forActivityResult(pairIntent<ActResultActivity>()) {
                    val str = it?.getStringExtra("tag") ?: ""
                    text.text = "跳转页面返回值：$str"
                }
            }


            btn3.setOnClickListener {
                toActivity<BenchmarkActivity>()
            }
        }
    }
}