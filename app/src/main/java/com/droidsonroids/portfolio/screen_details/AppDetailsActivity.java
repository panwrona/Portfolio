package com.droidsonroids.portfolio.screen_details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.droidsonroids.portfolio.R;
import com.droidsonroids.portfolio.mvp.MvpActivity;
import com.droidsonroids.portfolio.screen_details.fragment.AppDetailsFragment;
import com.droidsonroids.portfolio.screen_details.fragment.ZoomOutPageTransformer;
import com.droidsonroids.portfolio.screen_main.cards.AppItem;

import butterknife.Bind;
import butterknife.OnClick;

public class AppDetailsActivity extends MvpActivity<AppDetailsView, AppDetailsPresenter> {

	private static final String BUNDLE_APPITEM = "app_item";

	private String packageId;

	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.google_play_fab) FloatingActionButton googlePlayFAB;
	@Bind(R.id.app_name_textView) TextView appNameTextView;
	@Bind(R.id.app_description_textView) TextView appDescriptionTextView;
	@Bind(R.id.details_viewPager) ViewPager detailsViewPager;
	@Bind(R.id.firstDot) View firstDot;
	@Bind(R.id.secondDot) View secondDot;
	@Bind(R.id.thirdDot) View thirdDot;

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
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		init();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@OnClick(R.id.google_play_fab)
	public void onFabClicked() {
		if (packageId != null) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("market://details?id=" + packageId));
			startActivity(intent);
		}
	}

	private void init() {
		AppItem item = (AppItem) getIntent().getSerializableExtra(BUNDLE_APPITEM);
		appNameTextView.setText(item.getAppName());
		appDescriptionTextView.setText(item.getAppDescription());
		if (item.getPackageId() != null) {
			packageId = item.getPackageId();
			googlePlayFAB.setVisibility(View.VISIBLE);
		}
		initViewPager(item.getDrawables());
	}

	private void initViewPager(final int[] drawables) {
		PagerAdapter pagerAdapter = new AppDetailsPagerAdapter(getSupportFragmentManager(), drawables);
		detailsViewPager.setAdapter(pagerAdapter);
		detailsViewPager.setPageMargin(-20);
		detailsViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		detailsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(final int position) {
				setDots(position);
			}

			@Override
			public void onPageScrollStateChanged(final int state) {

			}
		});
		detailsViewPager.setCurrentItem(0);
	}

	private void setDots(final int position) {
		switch (position) {
			case 0:
				firstDot.setBackgroundResource(R.drawable.dots_active);
				secondDot.setBackgroundResource(R.drawable.dots_inactive);
				thirdDot.setBackgroundResource(R.drawable.dots_inactive);
				break;
			case 1:
				firstDot.setBackgroundResource(R.drawable.dots_inactive);
				secondDot.setBackgroundResource(R.drawable.dots_active);
				thirdDot.setBackgroundResource(R.drawable.dots_inactive);
				break;
			case 2:
				firstDot.setBackgroundResource(R.drawable.dots_inactive);
				secondDot.setBackgroundResource(R.drawable.dots_inactive);
				thirdDot.setBackgroundResource(R.drawable.dots_active);
				break;
		}
	}

	private class AppDetailsPagerAdapter extends FragmentStatePagerAdapter {

		private final int[] drawables;

		public AppDetailsPagerAdapter(final FragmentManager fm, final int[] drawables) {
			super(fm);
			this.drawables = drawables;
		}

		@Override
		public Fragment getItem(final int position) {
			return AppDetailsFragment.newInstance(drawables[position], position);
		}

		@Override
		public int getCount() {
			return drawables.length;
		}

	}
}
