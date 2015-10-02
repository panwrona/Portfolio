package com.droidsonroids.portfolio.screen_main.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.droidsonroids.portfolio.R;
import com.droidsonroids.portfolio.mvp.MvpActivity;
import com.droidsonroids.portfolio.screen_details.AppDetailsActivity;
import com.droidsonroids.portfolio.screen_main.cards.AppItem;
import com.droidsonroids.portfolio.screen_main.cards.Apps;
import com.droidsonroids.portfolio.screen_main.cards.AppsRecyclerAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter>
	implements MainActivityView, AppBarLayout.OnOffsetChangedListener {
	private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.2f;
	private static final int COLLAPSING_ANIMATIONS_DURATION = 300;

	private boolean mIsTheTitleContainerVisible = true;

	@Bind(R.id.toolbar) Toolbar mToolbar;
	@Bind(R.id.activity_main_ll_title_container) LinearLayout mLlTitleContainer;
	@Bind(R.id.activity_main_tv_toolbar_title) TextView mTvToolbarTitle;
	@Bind(R.id.activity_main_tv_name) TextSwitcher mTsName;
	@Bind(R.id.activity_main_tv_occupation) TextSwitcher mTsOccupation;
	@Bind(R.id.activity_main_tv_working_at) TextSwitcher mTsWorkingAt;
	@Bind(R.id.activity_main_app_bar_layout) AppBarLayout mAppBarLayout;
	@Bind(R.id.activity_main_rl_placeholder) RelativeLayout mRlPlaceholder;
	@Bind(R.id.activity_main_fl_title) FrameLayout mFlTitleContainer;
	@Bind(R.id.toolbar_rl) RelativeLayout mRlToolbarMainLayout;
	@Bind(R.id.activity_main_civ_mariusz) CircleImageView mCivMariusz;
	@Bind(R.id.activity_main_civ_paulina) CircleImageView mCivPaulina;
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
		int maxScroll = appBarLayout.getTotalScrollRange();
		float percentage = (float) Math.abs(i) / (float) maxScroll;
		handleViewCollapsing(percentage);
	}

	private void handleViewCollapsing(float percentage) {
		if(percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
			if(mIsTheTitleContainerVisible) {
				startScaleAnimationDown(mCivMariusz, mCivPaulina);
				mIsTheTitleContainerVisible = false;
			}
		} else {
			if(!mIsTheTitleContainerVisible) {
				startScaleAnimationUp(mCivMariusz, mCivPaulina);
				mIsTheTitleContainerVisible = true;
			}
		}
	}

	private void startScaleAnimationDown(CircleImageView... civs) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(COLLAPSING_ANIMATIONS_DURATION);
		for(CircleImageView civ : civs) {
			civ.startAnimation(scaleAnimation);
			civ.setVisibility(View.INVISIBLE);
		}
	}

	private void startScaleAnimationUp(CircleImageView... civs) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(COLLAPSING_ANIMATIONS_DURATION);
		for(CircleImageView civ : civs) {
			civ.setVisibility(View.VISIBLE);
			civ.startAnimation(scaleAnimation);
		}
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
		Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");
		initTextSwitchers(typeface);
	}

	private void initTextSwitchers(Typeface typeface) {
		mTsName.setInAnimation(this, android.R.anim.fade_in);
		mTsName.setOutAnimation(this, android.R.anim.fade_out);
		mTsName.setFactory(() -> {
			TextView textView = new TextView(MainActivity.this);
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				getResources().getDimension(R.dimen.name_text_size));
			textView.setTypeface(typeface);
			textView.setTextColor(getResources().getColor(R.color.text_icon_color));
			return textView;
		});

		mTsName.setText("Droids on Roids");

		mTsOccupation.setInAnimation(this, android.R.anim.fade_in);
		mTsOccupation.setOutAnimation(this, android.R.anim.fade_out);
		mTsOccupation.setFactory(() -> {
			TextView textView = new TextView(MainActivity.this);
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				getResources().getDimension(R.dimen.occupation_text_size));
			textView.setTypeface(typeface);
			textView.setTextColor(getResources().getColor(R.color.text_icon_color));
			return textView;
		});

		mTsOccupation.setText("app development company");

		mTsWorkingAt.setInAnimation(this, android.R.anim.fade_in);
		mTsWorkingAt.setOutAnimation(this, android.R.anim.fade_out);
		mTsWorkingAt.setFactory(() -> {
			TextView textView = new TextView(MainActivity.this);
			textView.setGravity(Gravity.CENTER);
			textView.setTypeface(typeface);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				getResources().getDimension(R.dimen.working_at_text_size));
			textView.setTextColor(getResources().getColor(R.color.light_primary_color));
			return textView;
		});

		mTsWorkingAt.setText("visit us @ thedroidsonroids.com");
	}

	private void initRecyclerView() {
		AppsRecyclerAdapter adapter =
			new AppsRecyclerAdapter(Apps.getApps(), this::showNewActivity);
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

	@OnClick(R.id.activity_main_civ_mariusz)
	public void onMariuszClicked() {
		mTsName.setText("Mariusz Brona");
		mTsOccupation.setText("android developer");
		mTsWorkingAt.setText("mariusz.brona@droidsonroids.pl");
	}

	@OnClick(R.id.activity_main_civ_paulina)
	public void onPaulinaClicked() {
		mTsName.setText("Paulina Szklarska");
		mTsOccupation.setText("android developer");
		mTsWorkingAt.setText("paulina.szklarska@droidsonroids.pl");
	}

	@OnClick(R.id.activity_main_rl_placeholder)
	public void onLogoClicked() {
		mTsName.setText("Droids on Roids");
		mTsOccupation.setText("app development company");
		mTsWorkingAt.setText("visit us @ thedroidsonroids.com");
	}
}
