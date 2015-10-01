package com.droidsonroids.portfolio.screen_main.cards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidsonroids.portfolio.R;

import java.util.List;

public class AppsRecyclerAdapter extends RecyclerView.Adapter<AppsViewHolder> {

	private final List<AppItem> appList;
	private final AppsOnClickListener listener;

	public AppsRecyclerAdapter(final List<AppItem> appList, final AppsOnClickListener listener) {
		this.appList = appList;
		this.listener = listener;
	}

	@Override
	public AppsViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
		View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_card, viewGroup, false);
		return new AppsViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(final AppsViewHolder appsViewHolder, final int position) {
		appsViewHolder.bindHolder(appList.get(position));
	}

	@Override
	public int getItemCount() {
		return appList.size();
	}
}
