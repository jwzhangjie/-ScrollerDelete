package com.jwzhangjie.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class ScrollListviewDelete extends ListView implements OnScrollListener{

	private float minDis = 10;
	private float mLastMotionX;// 记住上次X触摸屏的位置
	private float mLastMotionY;// 记住上次Y触摸屏的位置
	private boolean isLock = false;
	
	public interface ItemClickListener{
		void onItemClick(int position);
	}
	
	private ItemClickListener onItemClickListener;
	
	public void setOnItemClickListener(ItemClickListener onItemClickListener){
		this.onItemClickListener = onItemClickListener;
	}

	public ScrollListviewDelete(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnScrollListener(this);
	}
	
	
	/**
	 * 如果一个ViewGroup的onInterceptTouchEvent()方法返回true，说明Touch事件被截获，
	 * 子View不再接收到Touch事件，而是转向本ViewGroup的
	 * onTouchEvent()方法处理。从Down开始，之后的Move，Up都会直接在onTouchEvent()方法中处理。
	 * 先前还在处理touch event的child view将会接收到一个 ACTION_CANCEL。
	 * 如果onInterceptTouchEvent()返回false，则事件会交给child view处理。
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (!isIntercept(ev)) {
			DeleteAdapter.ItemDeleteReset();
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		boolean dte = super.dispatchTouchEvent(event);
		if (MotionEvent.ACTION_UP == event.getAction() && !dte) {//onItemClick
			int position = pointToPosition((int)event.getX(), (int)event.getY());
			if (onItemClickListener != null) {
				onItemClickListener.onItemClick(position);
			}
		}
		return dte;
	}

	/**
	 * 检测是ListView滑动还是item滑动 isLock 一旦判读是item滑动，则在up之前都是返回false
	 */
	private boolean isIntercept(MotionEvent ev) {
		float x = ev.getX();
		float y = ev.getY();
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e("test", "isIntercept  ACTION_DOWN  "+isLock);
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("test", "isIntercept  ACTION_MOVE  "+isLock);
			if (!isLock) {
				float deltaX = Math.abs(mLastMotionX - x);
				float deltay = Math.abs(mLastMotionY - y);
				mLastMotionX = x;
				mLastMotionY = y;
				if (deltaX > deltay && deltaX > minDis) {
					isLock = true;
					return false;
				}
			} else {
				return false;
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.e("test", "isIntercept  ACTION_UP  "+isLock);
			isLock = false;
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.e("test", "isIntercept  ACTION_CANCEL  "+isLock);
			isLock = false;
			break;
		}
		return true;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState != OnScrollListener.SCROLL_STATE_IDLE) {//认为是滚动，重置
			DeleteAdapter.ItemDeleteReset();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}

}
