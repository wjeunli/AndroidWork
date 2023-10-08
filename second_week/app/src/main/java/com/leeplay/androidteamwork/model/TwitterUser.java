package com.leeplay.androidteamwork.model;

import com.google.gson.annotations.SerializedName;

public class TwitterUser {

	@SerializedName("display_name")
	public String screenName;

	@SerializedName("acct")
	public String name;

	@SerializedName("avatar")
	public String profileImageUrl;

}
