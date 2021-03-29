package com.example.learndemo.input_connection;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description:
 * Author: TianShuxin
 * Date: 2020/10/20
 */
public class CEditText extends androidx.appcompat.widget.AppCompatEditText {
    public CEditText(@NonNull Context context) {
        super(context);
    }

    public CEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new MyInputConnection(this, getHandler());
    }
}
