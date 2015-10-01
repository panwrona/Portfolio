package com.droidsonroids.portfolio.screen_main.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droidsonroids.portfolio.R;
import com.droidsonroids.portfolio.mvp.MvpActivity;
import com.droidsonroids.portfolio.screen_details.AppDetailsActivity;
import com.droidsonroids.portfolio.screen_main.cards.AppItem;
import com.droidsonroids.portfolio.screen_main.cards.Apps;
import com.droidsonroids.portfolio.screen_main.cards.AppsRecyclerAdapter;

import butterknife.Bind;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter> implements MainActivityView, AppBarLayout.OnOffsetChangedListener {
	private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
	private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
	private static final int ALPHA_ANIMATIONS_DURATION = 200;

	private boolean mIsTheTitleVisible = false;
	private boolean mIsTheTitleContainerVisible = true;


	@Bind(R.id.toolbar) Toolbar mToolbar;
	@Bind(R.id.activity_main_ll_title_container) LinearLayout mLlTitleContainer;
	@Bind(R.id.activity_main_ll_action_items_container) LinearLayout mLlActionItemsContainer;
	@Bind(R.id.activity_main_tv_toolbar_title) TextView mTvToolbarTitle;
	@Bind(R.id.activity_main_app_bar_layout) AppBarLayout mAppBarLayout;
	@Bind(R.id.activity_main_rl_placeholder) RelativeLayout mRlPlaceholder;
	@Bind(R.id.activity_main_fl_title) FrameLayout mFlTitleContainer;
	@Bind(R.id.toolbar_rl) RelativeLayout mRlToolbarMainLayout;
	@Bind(R.id.activity_main_cl) CoordinatorLayout mClCoordinatorLayout;
	@Bind(R.id.cards_recyclerView) RecyclerView mCardsRecyclerView;

	@Override
	protected MainActivityPresenter createPresenter() {
		return new MainActivityPresenterImpl();
	}

	@Override
	protected int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		initRecyclerView();
		mAppBarLayout.addOnOffsetChangedListener(this);
		mClCoordinatorLayout.setOnTouchListener((v, event) -> true);
		initParallaxValues();
	}

	private void initRecyclerView() {
		AppsRecyclerAdapter adapter = new AppsRecyclerAdapter(Apps.getApps(), this::showNewActivity);
		mCardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mCardsRecyclerView.setHasFixedSize(true);
		mCardsRecyclerView.setAdapter(adapter);
	}

	private void showNewActivity(final AppItem item) {
		AppDetailsActivity.newInstance(this, item);
	}


	private void initParallaxValues() {
		CollapsingToolbarLayout.LayoutParams petDetailsLp =
			(CollapsingToolbarLayout.LayoutParams) mRlPlaceholder.getLayoutParams();
		CollapsingToolbarLayout.LayoutParams petBackgroundLp =
			(CollapsingToolbarLayout.LayoutParams) mFlTitleContainer.getLayoutParams();
		petDetailsLp.setParallaxMultiplier(0.9f);
		petBackgroundLp.setParallaxMultiplier(0.3f);
		mRlPlaceholder.setLayoutParams(petDetailsLp);
		mFlTitleContainer.setLayoutParams(petBackgroundLp);
	}

}
