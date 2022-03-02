package com.demon.ara

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demon.ara.databinding.ActivityActResultBinding

class ActResultActivity : AppCompatActivity() {
    private val TAG = "ActResultActivity"

    private lateinit var binding: ActivityActResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}