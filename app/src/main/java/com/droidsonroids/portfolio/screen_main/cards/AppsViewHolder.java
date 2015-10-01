package com.droidsonroids.portfolio.screen_main.cards;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidsonroids.portfolio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppsViewHolder extends RecyclerView.ViewHolder {

	private final AppsOnClickListener listener;

	@Bind(R.id.main_cardView) CardView mainView;
	@Bind(R.id.app_name_textView) TextView appName;
	@Bind(R.id.app_photo_imageView) ImageView appPhoto;
	@Bind(R.id.app_description_textView) TextView appDescription;

	public AppsViewHolder(final View itemView, final AppsOnClickListener listener) {
		super(itemView);
		this.listener = listener;
		ButterKnife.bind(this, itemView);
	}

	public void bindHolder(AppItem appItem) {
		appName.setText(appItem.getAppName());
		appPhoto.setBackgroundResource(appItem.getDrawableBackground());
		appDescription.setText(appItem.getAppDescription());
		mainView.setOnClickListener(view -> listener.onClick(appItem));
	}
}
