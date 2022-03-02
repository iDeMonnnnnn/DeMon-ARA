package com.demon.ara.java;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demon.ara.R;
import com.demon.ara.TestJumpActivity;
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


        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new JavaFragment()).commitAllowingStateLoss();

        binding.btn.setOnClickListener(view -> {
            DeMonActivityResult result = DeMonAraHelper.getActivityResult(JavaActivity.this);
            if (result != null) {
                result.launch(new Intent(this, TestJumpActivity.class), true,
                        data -> {
                            if (data.getData() != null) {
                                String str = data.getData().getStringExtra("tag");
                                binding.text.setText("跳转页面返回值：" + str);
                            } else {
                                binding.text.setText("我是返回键返回的，没有返回值~");
                            }
                        });
            }
        });


        binding.btnFinish.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("tag", TAG));
            finish();
        });

    }
}