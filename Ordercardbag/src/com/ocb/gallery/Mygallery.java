package com.ocb.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class Mygallery extends Gallery{

	public Mygallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 重写onFling方法，将滑动的速率降低 
	 */
	 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { 
		 return super.onFling(e1, e2, velocityX/3, velocityY); 
	 } 

}
