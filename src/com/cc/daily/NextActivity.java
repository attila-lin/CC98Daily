package com.cc.daily;

import java.util.LinkedList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class NextActivity extends ListActivity implements OnRefreshListener<ViewPager>{
	
//	private PullToRefreshViewPager mPullToRefreshViewPager;
	

//	static final int MENU_MANUAL_REFRESH = 0;
//	static final int MENU_DISABLE_SCROLL = 1;
//	static final int MENU_SET_MODE = 2;
//	static final int MENU_DEMO = 3;

	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private MyAdapter mAdapter;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 删除欢迎界面
		MainActivity.wel.finish();
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		
		// layout
		setContentView(R.layout.main);
		
		//顶部的图片是一张正方形的图片，在原始状态下只显示中间一部分。
		//下拉的过程中，顶部的图片会随着列表的移动改变高度，露出上下部分。
		//由于移动速度的不同，标题和背景图之间会有一种层次感。
		//http://www.zhihu.com/question/21522143
//		mPullToRefreshViewPager = (PullToRefreshViewPager) findViewById(R.id.pull_refresh_viewpager);
//		mPullToRefreshViewPager.setOnRefreshListener(this);
//		ViewPager vp = mPullToRefreshViewPager.getRefreshableView();
//		vp.setAdapter(new SamplePagerAdapter());
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		// Add an end-of-list listener
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				// 拿新数据
//				Toast.makeText(NextActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);

//		mListItems = new LinkedList<String>();
//		mListItems.addAll(Arrays.asList(mStrings));

		
		MyAdapter adapter = new MyAdapter(this);
//		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

		/**
		 * Add Sound Event Listener
		 */
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		mPullRefreshListView.setOnPullEventListener(soundListener);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(adapter);
		
		
		// 右滑activtity
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
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent = new Intent();
		intent.setClass(NextActivity.this, ViewPage.class);
	    startActivity(intent);
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
	}
	
//	static class SamplePagerAdapter extends PagerAdapter {
//
//		private static int[] sDrawables = { R.drawable.no1, R.drawable.no2, R.drawable.no3,
//				R.drawable.no4, R.drawable.no5 };
//
//		@Override
//		public int getCount() {
//			return sDrawables.length;
//		}
//
//		@Override
//		public View instantiateItem(ViewGroup container, int position) {
//			ImageView imageView = new ImageView(container.getContext());
//			imageView.setImageResource(sDrawables[position]);
//
//			// Now just add ImageView to ViewPager and return it
//			container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//
//			return imageView;
//		}
//
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView((View) object);
//		}
//
//		@Override
//		public boolean isViewFromObject(View view, Object object) {
//			return view == object;
//		}
//
//		@Override
//		public void destroyItem(View arg0, int arg1, Object arg2) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void finishUpdate(View arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public Object instantiateItem(View arg0, int arg1) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public void restoreState(Parcelable arg0, ClassLoader arg1) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public Parcelable saveState() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public void startUpdate(View arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
	
	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh...");
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
	
	public class MyAdapter extends BaseAdapter {

		 private Context c = null;  
		 private LayoutInflater myInflater = null;  
		 private int[] s = { R.drawable.no1, R.drawable.no2, R.drawable.no3, R.drawable.no4, R.drawable.no5 };
		 private int[] t = { R.string.text1, R.string.text2, R.string.text3, R.string.text4, R.string.text5 };
		 
		 public MyAdapter(Context myContext)  
		 {  
		        c = myContext;  
		 }  
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return s.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			
		    
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();  
	        if (convertView == null)  
	        {  
	            /** 
	             * 在程序中动态加载以上布局: 
	             * LayoutInflater flater = LayoutInflater.from(this); 
	             * View view = flater.inflate(R.layout.example, null); 
	             */  
	        	
	            myInflater = LayoutInflater.from(c);  
	            convertView = myInflater.inflate(R.layout.item_list, null);  
	  
	            holder.icon = (ImageView) convertView.findViewById(R.id.myimageview);  
	            holder.text = (TextView) convertView.findViewById(R.id.mytext);  
	  
	            convertView.setTag(holder);  
	        }  
	        else  
	        {  
	            holder = (ViewHolder) convertView.getTag();  
	        }  
	        
	  
	        holder.icon.setImageResource(s[position]);  
            holder.text.setText(t[position]);  
            if(position == 0){
            	holder.icon.setImageResource(R.drawable.date2); 
            	holder.text.setText("");
	        }
	        
	        return convertView;  
	        
	        
	        
//	        ImageView imageView = new ImageView(container.getContext());
//			imageView.setImageResource(sDrawables[position]);
//
//			// Now just add ImageView to ViewPager and return it
//			container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//
//			return imageView;
	        
		} 
		 
	 }
	 private class ViewHolder  
	 {  
        TextView text;  
        ImageView icon;  
	 }

//	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
//			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
//			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
//			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
//			"Allgauer Emmentaler" };
}
