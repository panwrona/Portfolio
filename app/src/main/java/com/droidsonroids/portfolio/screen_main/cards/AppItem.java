package com.droidsonroids.portfolio.screen_main.cards;

import java.io.Serializable;

public class AppItem implements Serializable {

	private int[] drawableBackground;
	private String appName;
	private String appDescription;

	public AppItem(final String appName, final String appDescription, final int[] drawableBackground) {
		this.drawableBackground = drawableBackground;
		this.appName = appName;
		this.appDescription = appDescription;
	}

	public int getDrawableBackground() {
		return drawableBackground[0];
	}

	public String getAppName() {
		return appName;
	}

	public String getAppDescription() {
		return appDescription;
	}
}
