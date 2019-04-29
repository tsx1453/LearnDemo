package com.example.learndemo.zhihuAd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class AdShowView extends CardView {

    private float ratio = 0f;
    private int adRes1 = -1;
    private int adRes2 = -1;
    private float radius = 0f;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path clipPath = new Path();

    public AdShowView(@NonNull Context context) {
        super(context);
    }

    public AdShowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdShowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        if (adRes1 != -1 && (bitmap1 == null || bitmap1.isRecycled())) {
            bitmap1 = loadBitMap(getContext(), adRes1);
        }
        if (adRes2 != -1 && (bitmap2 == null || bitmap2.isRecycled())) {
            bitmap2 = loadBitMap(getContext(), adRes2);
        }
    }

    private Bitmap loadBitMap(Context context, int res) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), res, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        Bitmap ori = BitmapFactory.decodeResource(context.getResources(), res, options);
        return Bitmap.createScaledBitmap(ori,getWidth(),getHeight(),true);
    }


    private boolean isAd() {
        return adRes1 != -1 && adRes2 != -1;
    }

    private boolean bitmapCanUse() {
        return bitmap1 != null && bitmap2 != null && !bitmap1.isRecycled() && !bitmap2.isRecycled();
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
        invalidate();
    }

    public int getAdRes1() {
        return adRes1;
    }

    public void setAdRes1(int adRes1) {
        this.adRes1 = adRes1;
    }

    public int getAdRes2() {
        return adRes2;
    }

    public void setAdRes2(int adRes2) {
        this.adRes2 = adRes2;
    }

    public void setRes(int r1, int r2) {
        this.adRes1 = r1;
        this.adRes2 = r2;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = (float) (Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2)));
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (isAd() && bitmapCanUse()) {
            clipPath.reset();
            clipPath.addCircle(getWidth(), getHeight(), radius * ratio, Path.Direction.CW);
            canvas.drawBitmap(bitmap1, getPaddingLeft(), getPaddingTop(), mPaint);
            canvas.save();
            canvas.clipPath(clipPath);
            canvas.drawBitmap(bitmap2, getPaddingLeft(), getPaddingTop(), mPaint);
            canvas.restore();
        }
        super.onDraw(canvas);
    }
}
