package com.droidsonroids.portfolio.screen_details.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.droidsonroids.portfolio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppDetailsFragment extends Fragment {

	private static final String BUNDLE_DRAWABLE = "drawable";
	private static final String BUNDLE_POSITION = "position";

	@Bind(R.id.background_imageView) ImageView backgroundImageView;

	public static Fragment newInstance(final int drawable, final int position) {
		AppDetailsFragment fragment = new AppDetailsFragment();
		Bundle arguments = new Bundle();
		arguments.putInt(BUNDLE_DRAWABLE, drawable);
		arguments.putInt(BUNDLE_POSITION, position);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_details, container, false);
		ButterKnife.bind(this, view);
		if (getArguments() != null) {

			setDrawable(getArguments().getInt(BUNDLE_DRAWABLE));
			view.setTag(getArguments().getInt(BUNDLE_POSITION));
		}
		return view;
	}

	private void setDrawable(final int drawable) {
		backgroundImageView.setImageResource(drawable);
	}

}
