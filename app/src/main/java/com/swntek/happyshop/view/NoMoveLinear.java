package com.swntek.happyshop.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 事件分发处理
 *
 */

@SuppressLint("NewApi")
public class NoMoveLinear extends LinearLayout {

	public NoMoveLinear(Context context) {
		super(context);
		ViewConfiguration config = ViewConfiguration.get(context);
		mTouchSlop = config.getScaledTouchSlop();

	}

	public NoMoveLinear(Context context, AttributeSet attrs) {
		super(context, attrs);
		ViewConfiguration config = ViewConfiguration.get(context);
		mTouchSlop = config.getScaledTouchSlop();

		// TODO Auto-generated constructor stub
	}

	public NoMoveLinear(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		ViewConfiguration config = ViewConfiguration.get(context);
		mTouchSlop = config.getScaledTouchSlop();

		// TODO Auto-generated constructor stub
	}

	int dx = 0;
	int ux = 0;
	int mTouchSlop;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			dx = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			ux = (int) event.getX();
			if (Math.abs(ux - dx) > mTouchSlop) {
				return true;
			} else {
				break;
			}
		}
		return super.onInterceptTouchEvent(event);
	}

}
