package com.cc.daily;


import android.app.Activity;
import android.content.Context;
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
	}

	@Override
	public void onRefresh(PullToRefreshBase<ViewPager> refreshView) {
		new GetDataTask().execute();
	}

	public class SamplePagerAdapter extends PagerAdapter {

		private int[] sDrawables = { R.drawable.no1, R.drawable.no2, R.drawable.no3, R.drawable.no4, R.drawable.no5 };

		 private LayoutInflater myInflater = null;

		 Context c = null;
		 
		 public SamplePagerAdapter(Context myContext)  
		 {  
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
			
//			ImageView imageView = new ImageView(container.getContext());
//			TextView textView = new TextView(container.getContext());
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
