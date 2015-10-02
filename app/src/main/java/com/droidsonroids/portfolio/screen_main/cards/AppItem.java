package com.droidsonroids.portfolio.screen_main.cards;

import java.io.Serializable;

public class AppItem implements Serializable {

	private final int[] drawableBackground;
	private final int mainDrawableBackground;
	private final String appName;
	private final String appDescription;
	private final String packageId;

	public AppItem(final String appName, final String appDescription, final int[] drawableBackground, final int mainDrawableBackground, final String packageId) {
		this.drawableBackground = drawableBackground;
		this.appName = appName;
		this.appDescription = appDescription;
		this.mainDrawableBackground = mainDrawableBackground;
		this.packageId = packageId;
	}

	public int getDrawableBackground() {
		return mainDrawableBackground;
	}

	public int[] getDrawables() {
		return drawableBackground;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public String getPackageId() {
		return packageId;
	}
}
