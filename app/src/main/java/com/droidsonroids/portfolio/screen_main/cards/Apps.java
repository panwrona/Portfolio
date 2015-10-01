package com.droidsonroids.portfolio.screen_main.cards;

import com.droidsonroids.portfolio.R;

import java.util.ArrayList;
import java.util.List;

public class Apps {

	public static List<AppItem> getApps() {
		List<AppItem> apps = new ArrayList<>();

		AppItem aiHire = new AppItem("AiHire", "The app is intended for corporations and job " +
				"seekers. It delivers custom, research-based cognitive assessments to prospective " +
				"candidates who apply for positions.", new int[]{R.drawable.aihire_main});

		AppItem shopKonnect = new AppItem("ShopKonnect", "ShopKonnect delivers measurable results " +
				"for Bricks & Mortar and Multi Channel Retailers throughout the in-store and online " +
				"customer lifecycle.", new int[]{R.drawable.shopkonnect_main});

		AppItem seoSerp = new AppItem("SEO Serp App", "Seo Serp App allows you to track your" +
				" websites ranking for any keywords in Google.", new int[]{R.drawable.seoserp_main});

		AppItem sknerus = new AppItem("Sknerus", "Sknerus is application made for internet auctions." +
				"It allows you to easily bid and buy with your phone in just few seconds.",
				new int[]{R.drawable.sknerus_main});

		apps.add(aiHire);
		apps.add(shopKonnect);
		apps.add(seoSerp);
		apps.add(sknerus);
		return apps;
	}

}
