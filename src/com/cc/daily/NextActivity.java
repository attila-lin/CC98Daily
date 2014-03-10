package com.cc.daily;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.cc.daily.ViewPage.SamplePagerAdapter;
import com.handmark.pulltorefresh.extras.viewpager.PullToRefreshViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class NextActivity extends Activity implements OnRefreshListener<ViewPager>{
	

	private PullToRefreshViewPager mPullToRefreshViewPager;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 删除欢迎界面
		MainActivity.wel.finish();
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		
		setContentView(R.layout.main);
		
		//顶部的图片是一张正方形的图片，在原始状态下只显示中间一部分。
		//下拉的过程中，顶部的图片会随着列表的移动改变高度，露出上下部分。
		//由于移动速度的不同，标题和背景图之间会有一种层次感。
		//http://www.zhihu.com/question/21522143
		mPullToRefreshViewPager = (PullToRefreshViewPager) findViewById(R.id.pull_refresh_viewpager);
		mPullToRefreshViewPager.setOnRefreshListener(this);
		ViewPager vp = mPullToRefreshViewPager.getRefreshableView();
		vp.setAdapter(new SamplePagerAdapter());
		
		setContentView(R.layout.main);
		
		// 右边的activtity
		SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN );
        menu.setShadowWidthRes(R.dimen.shadow_width);        
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset );
        menu.setFadeDegree(0.35f);
        menu.attachToActivity( this, SlidingMenu.SLIDING_CONTENT );
        menu.setMenu(R.layout.menu);
        /*** 初始化侧滑菜单 End ***/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onRefresh(PullToRefreshBase<ViewPager> refreshView) {
		// TODO Auto-generated method stub
		new GetDataTask().execute();
	}
	
	static class SamplePagerAdapter extends PagerAdapter {

		private static int[] sDrawables = { R.drawable.no1, R.drawable.no2, R.drawable.no3,
				R.drawable.no4, R.drawable.no5 };

		@Override
		public int getCount() {
			return sDrawables.length;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(container.getContext());
			imageView.setImageResource(sDrawables[position]);

			// Now just add ImageView to ViewPager and return it
			container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mPullToRefreshViewPager.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}
