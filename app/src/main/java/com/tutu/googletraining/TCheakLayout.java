package com.tutu.googletraining;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tutu on 16/4/27.
 */
public class TCheakLayout extends RelativeLayout implements Checkable {
	private CheckBox checkBox;
	private TextView name;
	private TextView id;
	private TextView edit;
	private RelativeLayout rlMain;

	private boolean isCheak = false;
	private String nameString;
	private String idString;

	private TCheakGroup.OnChildCheakChange changeListener;
	private OnEditClick onEditClick;

	public TCheakLayout(Context context) {
		super(context);
		init();
	}

	public TCheakLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TCheakLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}


	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.passgener_layout, this);
		checkBox = (CheckBox) view.findViewById(R.id.cb);
		name = (TextView) view.findViewById(R.id.tv_name);
		id = (TextView) view.findViewById(R.id.tv_id);
		edit = (TextView) view.findViewById(R.id.tv_edit);
		rlMain = (RelativeLayout) view.findViewById(R.id.rl_main);


		rlMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isCheak) {
					return;
				}
				setChecked(true);
				Log.e("xxxx", "setOnClickListener=" + v.getId());
			}
		});


		edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onEditClick.onEditClick(TCheakLayout.this.getId());
			}
		});
	}

	public interface OnEditClick {
		public void onEditClick(int viewID);
	}

	public void setOnEditClick(OnEditClick onEditClick) {
		this.onEditClick = onEditClick;
	}

	public void setCheakChangeListener(TCheakGroup.OnChildCheakChange listener) {
		changeListener = listener;
	}

	public void setName(String name) {
		if (TextUtils.isEmpty(name)) {
			return;
		}
		nameString = name;
		this.name.setText(name);
	}

	public void setId(String id) {
		if (TextUtils.isEmpty(id)) {
			return;
		}
		idString = id;
		this.id.setText(id);
	}

	public String getName() {
		return nameString;
	}

	public String getIdString() {
		return idString;
	}

	@Override
	public void setChecked(boolean checked) {
		isCheak = checked;
		checkBox.setChecked(checked);
		changeListener.OnCheakChange(this, getId(), checked);
	}

	@Override
	public boolean isChecked() {
		return isCheak;
	}

	@Override
	public void toggle() {
		setChecked(!isCheak);
		//changeListener.OnCheakChange(this,getId(), isCheak);
	}
}
