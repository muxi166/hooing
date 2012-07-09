package com.ocb;

import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class PictrueChangeActivity extends Activity implements OnItemClickListener,
		ViewSwitcher.ViewFactory {
	// 定义ImageSwitcher类对象
	private ImageSwitcher mSwitcher;
    private int downX,upX;  
	// 文本资源
	private String[] titles = { "标题1", "标题2", "标题3", "标题4", "标题5", "标题6",
			"标题7"};
	// 大图资源
	private Integer[] mThumbIds = { R.drawable.w05, R.drawable.w06,
			R.drawable.w07, R.drawable.w08, R.drawable.w09,
			R.drawable.w10, R.drawable.w11};
	// 大图对应的小图资源
	private Integer[] mImageIds = { R.drawable.icon, R.drawable.w06,
			R.drawable.w07, R.drawable.w08, R.drawable.w09,
			R.drawable.w10, R.drawable.w11};
	private Gallery gallery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置窗体无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mygallery);
		mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		mSwitcher.setFactory(this);
		
		// 设置图片的滑动效果
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		
       // mSwitcher.setImageResource(mImageIds[1]);
        
        mSwitcher.setOnTouchListener(new OnTouchListener(){
        	@Override
        	public boolean onTouch(View arg0, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)   
                {   
                    downX=(int) event.getX();//取得按下时的坐标   
                    return true;   
                }   
                else if(event.getAction()==MotionEvent.ACTION_UP)   
                {   
                    upX=(int) event.getX();//取得松开时的坐标   
                    int index=0;   
                    if(upX-downX>100)//从左拖到右，即看前一张   
                    {   
                        //如果是第一，则去到尾部   
                        if(gallery.getSelectedItemPosition()==0)   
                           index=gallery.getCount()-1;   
                        else  
                            index=gallery.getSelectedItemPosition()-1;   
                    }   
                    else if(downX-upX>100)//从右拖到左，即看后一张   
                    {   
                        //如果是最后，则去到第一   
                        if(gallery.getSelectedItemPosition()==(gallery.getCount()-1))   
                            index=0;   
                        else  
                            index=gallery.getSelectedItemPosition()+1;   
                    }   
                    //改变gallery图片所选，自动触发ImageSwitcher的setOnItemSelectedListener   
                    gallery.setSelection(index, true);   
                    return true;   
                }   
                return false;
        	}});
		gallery = (Gallery) findViewById(R.id.gallery);
		// 设置Gallery的适配器
		gallery.setAdapter(new ImageAdapter(this, mThumbIds.length));
		// Gallery中每个条目的点击事件监听
		gallery.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView parent, View v, int position, long id) {
				mSwitcher.setImageResource(mThumbIds[position % mImageIds.length]);
			}

			public void onNothingSelected(AdapterView parent) {
			}
			});
		gallery.setOnItemClickListener(this);
		// 设置默认其实位置为第二张图片
		gallery.setSelection(1);
	}



	@Override
	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFF000000);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i;
	}

	// Gallery的适配器
	public class ImageAdapter extends BaseAdapter {
		private int mGalleryItemBackground;
		// 定义map存储划过的位置
		private HashMap<Integer, View> mViewMaps;
		private int mCount;
		// 定义布局加载器
		private LayoutInflater mInflater;

		public ImageAdapter(Context c, int count) {
			this.mCount = count;
			mViewMaps = new HashMap<Integer, View>(count);
			mInflater = LayoutInflater.from(PictrueChangeActivity.this);
			// 定义图片的背景样式
			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			mGalleryItemBackground = a.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 0);
			// 定义可以重复使用.可回收
			a.recycle();
		}

		public int getCount() {
			// 设置循环的次数
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			Log.v("TAG", "getView() position=" + position + " convertView="
					+ convertView);
			View viewGroup = mViewMaps.get(position % mCount);
			ImageView imageView = null;
			TextView textView = null;
			if (viewGroup == null) {
				viewGroup = mInflater.inflate(R.layout.gallery_item, null);
				imageView = (ImageView) viewGroup
						.findViewById(R.id.item_gallery_image);
				textView = (TextView) viewGroup
						.findViewById(R.id.item_gallery_text);
				imageView.setBackgroundResource(mGalleryItemBackground);
				mViewMaps.put(position % mCount, viewGroup);
				imageView.setImageResource(mImageIds[position
						% mImageIds.length]);
				textView.setText(titles[position % mImageIds.length]);
			}

			return viewGroup;
		}
	}

	private int curruntPos = -1;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (curruntPos == position) {
			return;
		} else
			curruntPos = position;
		mSwitcher.setImageResource(mThumbIds[position % mThumbIds.length]);
	}


}