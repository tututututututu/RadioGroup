package com.tutu.googletraining;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by tutu on 16/4/27.
 */
public class TCheakGroup extends LinearLayout {
	private PassThroughHierarchyChangeListener mPassThroughListener;
	private OnChildCheakChange mChildOnCheckedChangeListener;
	private int mCheakID = -1;
	private TCheakLayout mCheakLayout = null;

	public TCheakGroup(Context context) {
		super(context);
		init();
	}

	public TCheakGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TCheakGroup(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}


	private void init() {
		mPassThroughListener = new PassThroughHierarchyChangeListener();
		mChildOnCheckedChangeListener = new OnChildCheakChange() {
			@Override
			public void OnCheakChange(View view, int cheakid, boolean cheaked) {
				//当里面的控件cheak状态改变

				if (cheaked) {
					if (mCheakLayout != null) {
						mCheakLayout.setChecked(false);
					}
					mCheakLayout = (TCheakLayout) view;
					Log.e("xxx", "OnCheakChange cheakid=" + cheakid + " cheaked=" + cheaked);
					mCheakID = cheakid;
				}
			}
		};
		super.setOnHierarchyChangeListener(mPassThroughListener);
	}

	@Override
	public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
		// the user listener is delegated to our pass-through listener
		mPassThroughListener.mOnHierarchyChangeListener = listener;
	}

	private class PassThroughHierarchyChangeListener implements
		OnHierarchyChangeListener {
		private OnHierarchyChangeListener mOnHierarchyChangeListener;

		public void onChildViewAdded(View parent, View child) {
			if (parent == TCheakGroup.this && child instanceof TCheakLayout) {
				Log.e("xxx", "onChildViewAdded=" + child.getId());

				((TCheakLayout) child).setCheakChangeListener(
					mChildOnCheckedChangeListener);
				Log.e("xxx", "setCheakChangeListener viewid=" + child.getId());
				if (((TCheakLayout) child).isChecked()) {
					mCheakID = child.getId();
					mCheakLayout = (TCheakLayout) child;
				}
			}

			if (mOnHierarchyChangeListener != null) {
				mOnHierarchyChangeListener.onChildViewAdded(parent, child);
			}
		}


		public void onChildViewRemoved(View parent, View child) {
			if (parent == TCheakGroup.this && child instanceof TCheakLayout) {
				((RadioButton) child).setOnCheckedChangeListener(null);
			}

			if (mOnHierarchyChangeListener != null) {
				mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
			}
		}
	}

	public int getCheakPos() {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			if (getChildAt(i).getId() == mCheakID) {
				return i;
			}
		}
		return -1;
	}


	public interface OnChildCheakChange {
		public void OnCheakChange(View view, int cheakid, boolean cheaked);
	}


}
