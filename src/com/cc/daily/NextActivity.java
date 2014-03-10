package com.cc.daily;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class NextActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 删除欢迎界面
		MainActivity.wel.finish();
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		
		//顶部的图片是一张正方形的图片，在原始状态下只显示中间一部分。
		//下拉的过程中，顶部的图片会随着列表的移动改变高度，露出上下部分。
		//由于移动速度的不同，标题和背景图之间会有一种层次感。
		//http://www.zhihu.com/question/21522143
		
		setContentView(R.layout.main);
		
		SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN );
        menu.setShadowWidthRes(R.dimen.shadow_width);        
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset );
        menu.setFadeDegree(0.35f);
        menu.attachToActivity( this, SlidingMenu.SLIDING_CONTENT );
        menu.setMenu(R.layout.activity_menu); // 4）
        /*** 初始化侧滑菜单 End ***/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
