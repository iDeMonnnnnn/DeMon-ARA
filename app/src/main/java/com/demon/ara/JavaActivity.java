package com.demon.ara;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demon.ara.databinding.ActivityJavaBinding;
import com.demon.core.DeMonActivityResult;
import com.demon.core.DeMonAraHelper;

public class JavaActivity extends AppCompatActivity {
    private static final String TAG = "JavaActivity";
    private ActivityJavaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, TestJumpActivity.class);
            DeMonActivityResult result = DeMonAraHelper.getBaseActivityResult(JavaActivity.this);
            if (result != null) {
                result.launch(intent, data -> {
                    String str = data.getData().getStringExtra("tag");
                    binding.text.setText("跳转页面返回值：" + str);
                });
            }
        });


        binding.btnFinish.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("tag", TAG));
            finish();
        });

    }
}