package com.daniza.preloadgila;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PreloadGila{
    private Context mContext;
    private ViewGroup mParent;
    private CustomViewPreloadGila ivLoadingBar;
    private static int INDEX=0,posisiX,posisiY,batasX,batasY,velocityX=100,velocityY=100;

    PreloadGila(Context context, ViewGroup parent){
        this.mContext=context;
        this.mParent=parent;
    }

    public PreloadGila(Context context, ViewGroup parent,int drawable){
        this(context,parent);

        final float scale = mContext.getResources().getDisplayMetrics().density;

        if(drawable<=0){
            ivLoadingBar=new CustomViewPreloadGila(mContext);
        }else{
            ivLoadingBar=new CustomViewPreloadGila(mContext,drawable);
        }
        ivLoadingBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ivLoadingBar.getLayoutParams().width= (int) mContext.getResources().getDimension(R.dimen.imageWidth);
        ivLoadingBar.getLayoutParams().height= (int) mContext.getResources().getDimension(R.dimen.imageHeight);

        mParent.addView(ivLoadingBar);

        batasX=mContext.getResources().getDisplayMetrics().widthPixels-ivLoadingBar.getLayoutParams().width;
        batasY=mContext.getResources().getDisplayMetrics().heightPixels-ivLoadingBar.getLayoutParams().height;
        posisiX=0;
        posisiY=0;

        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                posisiX= posisiX+velocityX;
                posisiY= posisiY+velocityY;

                if(posisiX<0 || posisiX>batasX){
                    velocityX= -velocityX;
                }
                if(posisiY>batasY || posisiY<0){
                    velocityY= -velocityY;
                }

                ivLoadingBar.setX(posisiX);
                ivLoadingBar.setY(posisiY);

                handler.postDelayed(this,100);
            }
        };

        handler.post(runnable);
    }

}
