package com.cc.daily;

import java.util.Timer;
import java.util.TimerTask;


import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {
	
	public static  MainActivity wel ;
	private ImageView imageView; 
	private TextView textView;
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		wel = this;
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		
		setContentView(R.layout.activity_main);
		
		imageView=(ImageView)findViewById(R.id.imageview);  
		textView =(TextView)findViewById(R.id.textview); 
		textView.setBackgroundColor(android.R.color.transparent);

		// 缓慢变大
		Scale(); 
	
	    Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, NextActivity.class);
			    startActivity(intent);
			}
		}, 2*1000);
		
	}
	
	private void Scale() {
		AnimationSet animationSet=new AnimationSet(true);  
        ScaleAnimation scaleAnimation=new ScaleAnimation(  
                1, 1.2f, 1, 1.2f,   
                Animation.RELATIVE_TO_SELF, 0.5f,   
                Animation.RELATIVE_TO_SELF, 0.5f);  
        scaleAnimation.setDuration(3000);  
        animationSet.addAnimation(scaleAnimation);  
        imageView.startAnimation(scaleAnimation);  
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


