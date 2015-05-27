/**
 * ¹¦ÄÜ£º
 */
package com.example.revealdemo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * @author HanKun
 *
 */
public class RevealDrawable extends Drawable {
	
	private Drawable mUnSelectedDrawable;
	private Drawable mSelectedDrawable;
	private int mOrientation;
	private Rect mUnSelectedRect = new Rect();
	private Rect mSelectedRect = new Rect();
	public final static int HORIZONTAL = 1;
	public final static int VERTICAL = 2;

	public RevealDrawable(Drawable unselected,Drawable selected,int orientation){
		mUnSelectedDrawable = unselected;
		mSelectedDrawable = selected;
		mOrientation = orientation;
	}

	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#draw(android.graphics.Canvas)
	 */
	@Override
	public void draw(Canvas canvas) {
		int level = getLevel();
		if(level == 0 || level == 10000){
			mUnSelectedDrawable.draw(canvas);
		}else if(level == 5000){
			mSelectedDrawable.draw(canvas);
		}else{
			Rect bounds = getBounds();
			int unSelectedWidth = 0;
			int unSelectedHeight = 0;
			int selectedWidth = 0;
			int selectedHeight = 0;
			if(mOrientation == HORIZONTAL){
				float ratio = (level/5000f) - 1f;
				unSelectedWidth = (int) (bounds.width()*Math.abs(ratio));
				unSelectedHeight = bounds.height();
				Gravity.apply(ratio>0?Gravity.RIGHT:Gravity.LEFT, unSelectedWidth, unSelectedHeight, bounds, mUnSelectedRect);
				selectedWidth = bounds.width() - unSelectedWidth;
				selectedHeight = bounds.height();
				Gravity.apply(ratio<0?Gravity.RIGHT:Gravity.LEFT, selectedWidth, selectedHeight, bounds, mSelectedRect);
			}else if(mOrientation == VERTICAL){
				float ratio = (level/5000f) - 1f;
				unSelectedWidth = bounds.width();
				unSelectedHeight = (int) (bounds.height()*Math.abs(ratio));
				Gravity.apply(ratio>0?Gravity.BOTTOM:Gravity.TOP, unSelectedWidth, unSelectedHeight, bounds, mUnSelectedRect);
				selectedWidth = bounds.width();
				selectedHeight = bounds.height() - unSelectedHeight;
				Gravity.apply(ratio<0?Gravity.BOTTOM:Gravity.TOP, selectedWidth, selectedHeight, bounds, mSelectedRect);
			}
			canvas.save();
			canvas.clipRect(mUnSelectedRect);
			mUnSelectedDrawable.draw(canvas);
			canvas.restore();
			canvas.save();
			canvas.clipRect(mSelectedRect);
			mSelectedDrawable.draw(canvas);
			canvas.restore();
		}
	}
	
	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#onLevelChange(int)
	 */
	@Override
	protected boolean onLevelChange(int level) {
		invalidateSelf();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#onBoundsChange(android.graphics.Rect)
	 */
	@Override
	protected void onBoundsChange(Rect bounds) {
		mUnSelectedDrawable.setBounds(bounds);
		mSelectedDrawable.setBounds(bounds);
	}
	
	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#getIntrinsicHeight()
	 */
	@Override
	public int getIntrinsicHeight() {
		return Math.max(mUnSelectedDrawable.getIntrinsicHeight(), mSelectedDrawable.getIntrinsicHeight());
	}
	
	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#getIntrinsicWidth()
	 */
	@Override
	public int getIntrinsicWidth() {
		return Math.max(mUnSelectedDrawable.getIntrinsicWidth(), mSelectedDrawable.getIntrinsicWidth());
	}

	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#setAlpha(int)
	 */
	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#setColorFilter(android.graphics.ColorFilter)
	 */
	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#getOpacity()
	 */
	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
