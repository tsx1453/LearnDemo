package com.example.learndemo.virtual_display;

import android.app.Presentation;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learndemo.R;
import com.example.learndemo.base.BaseFragment;

import static android.content.Context.DISPLAY_SERVICE;

/**
 * Description:
 * Author: TianShuxin
 * Date: 2020/10/20
 */
public class VirtualDisplayDemo extends BaseFragment {
    @Override
    protected int layoutId() {
        return R.layout.fragment_virtual_display;
    }

    private SurfaceView surfaceView;
    private VirtualDisplay virtualDisplay;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        surfaceView = view.findViewById(R.id.surface);
        view.findViewById(R.id.btn_presentation).setOnClickListener(this::onPresentationClick);
        view.findViewById(R.id.btn_virtual_display).setOnClickListener(this::onVirtualDisplayClick);
        view.findViewById(R.id.btn_test).setOnClickListener(this::onTestClick);
    }

    public void onPresentationClick(View view) {
        DisplayManager displayManager = (DisplayManager) requireActivity().getSystemService(DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        if (displays.length > 1) {
            Presentation presentation = new Presentation(requireContext(), displays[1]);
            TextView textView = new TextView(requireContext());
            textView.setText("SSSS");
            presentation.setContentView(textView);
            presentation.show();
        } else {
            Toast.makeText(requireContext(), "还没有创建额外的Display", Toast.LENGTH_SHORT).show();
        }
    }

    public void onVirtualDisplayClick(View view) {
        if (virtualDisplay == null) {
            final DisplayManager displayManager = (DisplayManager) requireActivity().getSystemService(DISPLAY_SERVICE);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            int h = getResources().getDisplayMetrics().heightPixels;
            int w = getResources().getDisplayMetrics().widthPixels;
            virtualDisplay = displayManager.createVirtualDisplay("test-vd", w, h, getResources().getDisplayMetrics().densityDpi, surfaceHolder.getSurface(), 0);
            Presentation presentation = new Presentation(requireContext(), virtualDisplay.getDisplay());
            final EditText editText = new EditText(requireContext());
            editText.setLayoutParams(new ViewGroup.LayoutParams(400, 100));
            editText.setText("test");
            editText.setEnabled(true);
            editText.setTextColor(Color.BLUE);
            presentation.setContentView(editText);
            viewInVD = editText;
            presentation.show();
        }
    }

    private View viewInVD;

    public void onTestClick(View view) {
        if (viewInVD != null) {
            viewInVD.requestFocus();
        }
    }

    @Override
    public void onDestroy() {
        virtualDisplay.release();
        super.onDestroy();
    }
}
