package com.droidsonroids.portfolio.screen_main;

import com.droidsonroids.portfolio.R;
import com.droidsonroids.portfolio.mvp.MvpActivity;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter> implements MainActivityView {
	@Override
	protected MainActivityPresenter createPresenter() {
		return new MainActivityPresenterImpl();
	}

	@Override
	protected int getLayout() {
		return R.layout.activity_main;
	}
}
