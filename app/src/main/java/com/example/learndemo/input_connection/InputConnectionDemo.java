package com.example.learndemo.input_connection;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learndemo.R;
import com.example.learndemo.base.BaseFragment;

/**
 * Description:
 * Author: TianShuxin
 * Date: 2020/10/20
 */
public class InputConnectionDemo extends BaseFragment {
    private CEditText editText;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.et_main);
        EditText editText = view.findViewById(R.id.et_display);
        ProxyEditText proxyEditText = view.findViewById(R.id.et_proxy);
        proxyEditText.setInputConnectionProxy(editText::onCreateInputConnection);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_input_connection;
    }
}
