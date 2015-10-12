package com.example.revealdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private GallaryHorizontalScrollView hsv;
	private int[] unSelectedIds = { R.drawable.avft, R.drawable.box_stack, R.drawable.bubble, R.drawable.rectangle };
	private int[] selectedIds = { R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_active,
			R.drawable.rectangle_active };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Log.e("gsgafafsgs", "sgsgs");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hsv = (GallaryHorizontalScrollView) findViewById(R.id.hsv);
		RevealDrawable[] drawable = new RevealDrawable[unSelectedIds.length];
		for (int i = 0; i < drawable.length; i++) {
			drawable[i] = new RevealDrawable(getResources().getDrawable(unSelectedIds[i]),
					getResources().getDrawable(selectedIds[i]), RevealDrawable.HORIZONTAL);
		}
		hsv.addImageView(drawable);
	}

}
