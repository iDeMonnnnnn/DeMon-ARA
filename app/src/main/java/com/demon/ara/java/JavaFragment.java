package com.demon.ara.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demon.ara.TestJumpActivity;
import com.demon.ara.databinding.FragmentJavaBinding;
import com.demon.core.DeMonActivityResult;
import com.demon.core.DeMonAraHelper;

/**
 * @author DeMon
 * Created on 2022/3/2.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
public class JavaFragment extends Fragment {

    private FragmentJavaBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentJavaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btn.setOnClickListener(view1 -> {
            DeMonActivityResult<Intent, ActivityResult> result = DeMonAraHelper.getActivityResult(this);
            if (result != null) {
                result.launch(new Intent(requireContext(), TestJumpActivity.class), data -> {
                    String str = data.getData().getStringExtra("tag");
                    binding.text.setText("跳转页面返回值：" + str);
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}
