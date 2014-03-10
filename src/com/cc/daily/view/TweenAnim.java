package com.cc.daily.view;


import com.cc.daily.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;


public class TweenAnim extends View {
	
	//Sacle动画 - 渐变尺寸缩放
    private Animation scaleAnimation = null;
    public TweenAnim(Context context) {
        super(context);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("Tween", "onDraw");
        //加载一个图片
//        canvas.drawBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.welcome)).getBitmap(), 0, 0, null);
        
        

        scaleAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.myanim);
        //动画集
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(scaleAnimation);
        
        this.startAnimation(set);
        //设置动画时间 (作用到每个动画)
//        set.setDuration(1000);
    }


}