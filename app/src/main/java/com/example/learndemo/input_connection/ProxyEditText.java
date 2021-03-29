package com.example.learndemo.input_connection;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/**
 * Description:
 * Author: TianShuxin
 * Date: 2020/10/20
 */
public class ProxyEditText extends androidx.appcompat.widget.AppCompatEditText {
    public ProxyEditText(Context context) {
        super(context);
    }

    public ProxyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProxyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        if(inputConnectionProxy!=null){
            return inputConnectionProxy.onCreateInputConnection(outAttrs);
        }
        return super.onCreateInputConnection(outAttrs);
    }

    private InputConnectionProxy inputConnectionProxy;

    public void setInputConnectionProxy(InputConnectionProxy inputConnectionProxy) {
        this.inputConnectionProxy = inputConnectionProxy;
    }

    public interface InputConnectionProxy{
        InputConnection onCreateInputConnection(EditorInfo outAttrs);
    }
}
