package com.kemizhibo.kemizhibo.yhr.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;

class TransparentDialog extends Dialog {

	public TransparentDialog(Context context, int theme) {
		super(context, theme);
	}
	
	public static TransparentDialog createDialog(Context context) {
		TransparentDialog dialog = new TransparentDialog(context, R.style.Transparent);
		dialog.setContentView(R.layout.transparent);
		dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return dialog;
	}

	public void setMessage(String message) {
		TextView msgView = (TextView)findViewById(R.id.transparent_message);
		msgView.setText(message);
	}

}
