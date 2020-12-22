package com.daniza.preloadgila;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomViewPreloadGila extends androidx.appcompat.widget.AppCompatImageView {
    private static final String TAG="DANI";
    private static int DETIK=0;
    private static int resources=0;
    private Context context;
    private Bitmap mBitmap;
    public CustomViewPreloadGila(@NonNull Context context) {
        super(context);
    }

    public CustomViewPreloadGila(@NonNull Context context, int resources){
        super(context);
        this.context=context;
        CustomViewPreloadGila.resources=resources;
        mBitmap= BitmapFactory.decodeResource(context.getResources(),resources);
    }

    public CustomViewPreloadGila(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewPreloadGila(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint mFillPain=new Paint(Paint.ANTI_ALIAS_FLAG);

        DETIK++;
        if(resources != 0 && mBitmap!= null){
            Matrix matrix=new Matrix();
            mPaint.setAntiAlias(true);

            matrix.setRotate((DETIK*10)%360,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
//            canvas.drawArc(30,30,canvas.getWidth(),canvas.getHeight(),0,360,true,mPaint);
//            canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2, mPaint);

            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            canvas.drawBitmap(mBitmap,matrix,mPaint);
        }else{
            mFillPain.setColor(Color.BLUE);
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeWidth(20);
            canvas.drawArc(40,30,canvas.getWidth()-30,canvas.getHeight()-20,(DETIK*10)%360-25,(DETIK*10)%360,true,mPaint);
            canvas.drawArc(30,30,canvas.getWidth(),canvas.getHeight(),(DETIK*10)%360-25,(DETIK*10)%360,true,mFillPain);
        }

        if(false) Log.d(TAG, "Sudah Berjalan : "+(DETIK*10)%360);
        this.invalidate();
    }
}
