package com.cc.daily;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.extras.viewpager.PullToRefreshViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class ViewPage extends Activity implements OnRefreshListener<ViewPager> {

	private PullToRefreshViewPager mPullToRefreshViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		
		// 设置layout
		setContentView(R.layout.activity_viewpager);

		mPullToRefreshViewPager = (PullToRefreshViewPager) findViewById(R.id.pull_refresh_viewpager);
		mPullToRefreshViewPager.setOnRefreshListener(this);

		ViewPager vp = mPullToRefreshViewPager.getRefreshableView();
		vp.setAdapter(new SamplePagerAdapter(this));
		
		Intent intent = getIntent();
        String value = intent.getStringExtra("position");
        // 设置默认页
		vp.setCurrentItem(Integer.parseInt( value ));
		
		
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativite2);

		// 设置底
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.date2);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT, 
        		ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin = 0;
        params.bottomMargin = 100;
        rl.addView(iv, params);	
        
        // 设置收藏
        ImageView iv1 = new ImageView(this);
        iv.setImageResource(R.drawable.shou);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT, 
        		ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.leftMargin = 0;
        params1.bottomMargin = 100;
        rl.addView(iv1, params1);	
        
        // 设置分享
        ImageView iv2 = new ImageView(this);
        iv.setImageResource(R.drawable.shou);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT, 
        		ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.leftMargin = 0;
        params2.bottomMargin = 100;
        rl.addView(iv2, params2);	
        
        // 设置点评
        ImageView iv3 = new ImageView(this);
        iv.setImageResource(R.drawable.shou);
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT, 
        		ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.leftMargin = 0;
        params3.bottomMargin = 100;
        rl.addView(iv3, params3);	
	}


	@Override
	public void onRefresh(PullToRefreshBase<ViewPager> refreshView) {
		new GetDataTask().execute();
	}

	public class SamplePagerAdapter extends PagerAdapter {

		private int[] sDrawables = { R.drawable.no1, R.drawable.no2, R.drawable.no3, R.drawable.no4, R.drawable.no5 };

		private LayoutInflater myInflater = null;

		Context c = null;
		 
		public SamplePagerAdapter(Context myContext) {  
		    c = myContext;  
		   
		} 
		 
		@Override
		public int getCount() {
			return sDrawables.length;
		}
	
		@Override
		public View instantiateItem(ViewGroup container, int position) {
			
			ViewHolder holder = new ViewHolder();  
			
			View convertView = new View(container.getContext());
			myInflater = LayoutInflater.from(container.getContext());  
			convertView = myInflater.inflate(R.layout.activity_page, null);
			
			holder.icon = (ImageView) convertView.findViewById(R.id.myimageview);  
	        holder.text = (TextView) convertView.findViewById(R.id.mytext);
			
	        holder.icon.setImageResource(sDrawables[position]);
	        holder.text.setText("这里是正文啊啊啊啊");
			
			// Now just add ImageView to ViewPager and return it
			container.addView(convertView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			
			return convertView;
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
	 private class ViewHolder  
	 {  
        TextView text;  
        ImageView icon;  
	 }

}
