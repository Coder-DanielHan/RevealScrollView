/**
 * 功能：
 */
package com.example.revealdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author DanielHan
 * 
 */
public class GallaryHorizontalScrollView extends HorizontalScrollView{

	private LinearLayout container;
	private int centerX;
	private int image_width;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public GallaryHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public GallaryHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * @param context
	 */
	public GallaryHorizontalScrollView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		container = new LinearLayout(getContext());
		container.setLayoutParams(params);
//		setOnTouchListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.HorizontalScrollView#onLayout(boolean, int, int, int,
	 * int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// 得到图片的宽度
		View view = container.getChildAt(0);
		image_width = view.getWidth();
		// 得到hsv的中间x坐标
		centerX = getWidth() / 2;
		// 处理下，中心坐标改为中心图片的左边界
		centerX = centerX - image_width / 2;
		container.setPadding(centerX, 0, centerX, 0);
	}

	public void addImageView(Drawable[] revealDrawable) {
		for (int i = 0; i < revealDrawable.length; i++) {
			ImageView image = new ImageView(getContext());
			image.setImageDrawable(revealDrawable[i]);
			container.addView(image);
			if (i == 0) {
				image.setImageLevel(5000);
			}
		}
		addView(container);
	}
	/* (non-Javadoc)
	 * @see android.view.View#onScrollChanged(int, int, int, int)
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		Log.e("onScrollChanged", l+"");
		reveal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//		if (event.getAction() == MotionEvent.ACTION_MOVE
//				|| event.getAction() == MotionEvent.ACTION_UP) {
//			reveal();
//		}
//		return false;
//	}

	public void reveal() {
		// 获取中间的两个ImageView
		int scrollX = getScrollX();
		int index_left = scrollX / image_width;
		int index_right = index_left + 1;
		// 变化
		float ratio = 5000f / image_width;
		ImageView image_left = (ImageView) container.getChildAt(index_left);
		image_left.setImageLevel((int) (5000 - scrollX % image_width * ratio));
		if (index_right < container.getChildCount()) {
			ImageView image_right = (ImageView) container
					.getChildAt(index_right);
			image_right.setImageLevel((int) (10000 - scrollX % image_width
					* ratio));
		}
		for (int i = 0; i < container.getChildCount(); i++) {
			if (i != index_left && i != index_right) {
				// 灰色
				ImageView image = (ImageView) container.getChildAt(i);
				image.setImageLevel(0);
			}
		}
	}
}
