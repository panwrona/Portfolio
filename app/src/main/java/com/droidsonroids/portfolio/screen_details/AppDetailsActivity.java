package com.droidsonroids.portfolio.screen_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.droidsonroids.portfolio.R;
import com.droidsonroids.portfolio.mvp.MvpActivity;
import com.droidsonroids.portfolio.screen_main.cards.AppItem;

import butterknife.Bind;

public class AppDetailsActivity extends MvpActivity<AppDetailsView, AppDetailsPresenter> {

	private static final String BUNDLE_APPITEM = "app_item";
	@Bind(R.id.app_name_textView) TextView appNameTextView;

	public static void newInstance(Context context, AppItem appItem) {
		final Intent intent = new Intent(context, AppDetailsActivity.class);
		intent.putExtra(BUNDLE_APPITEM, appItem);
		context.startActivity(intent);
	}

	@Override
	protected AppDetailsPresenter createPresenter() {
		return new AppDetailsPresenterImpl();
	}

	@Override
	protected int getLayout() {
		return R.layout.activity_details;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		AppItem item = (AppItem) getIntent().getSerializableExtra(BUNDLE_APPITEM);
		appNameTextView.setText(item.getAppName());
	}
}
