package com.leeplay.androidteamwork.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.leeplay.androidteamwork.model.Tweet;
import com.leeplay.androidteamwork.model.TwitterUser;
import com.leeplay.androidteamwork.utils.Constants;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MastodonHelper {

	// Proxy stuff
	private static final boolean USE_PROXY = false;
	private static final String PROXY_HOST = "prx-dev02.priv.atos.fr";
	private static final int PROXY_PORT = 3128;
	private static final boolean USE_PROXY_AUTHENTICATION = false;
	private static final String PROXY_USERNAME = "training10";
	private static final String PROXY_PASSWORD = "Student10/";

	public static List<Tweet> getTweetsOfUser(String userName){
		try {

			return getTweets(userName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getFakeTweets();
	}

	public static Bitmap getTwitterUserImage(String imageUrl) throws Exception{
		final HttpURLConnection connection = getHTTPUrlConnection(imageUrl);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		final int responseCode = connection.getResponseCode();

		// If success
		if (responseCode == 200){
			final Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
			return bitmap;
		}
		return null;
	}

	private static List<Tweet> getTweets(String userName) throws Exception{
		// Create the HTTP Get request to Twitter servers
		final HttpURLConnection connection = getHTTPUrlConnection(Constants.Twitter.URL_STREAM_MASTODONTE );
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		connection.setRequestProperty("Content-Type", "application/json");

		String responseMessage = connection.getResponseMessage();
		final int responseCode = connection.getResponseCode();

		// If success
		if (responseCode == 200){
			// Build our Tweet list
			final Type type = new TypeToken<ArrayList<Tweet>>() {}.getType();
			return new Gson().fromJson(new JsonReader(new InputStreamReader(connection.getInputStream(), "UTF-8")), type);
		}

		return getFakeTweets();
	}



	private static HttpURLConnection getHTTPUrlConnection(String url) throws Exception {
		if (USE_PROXY){
			final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));

			if (USE_PROXY_AUTHENTICATION){
				Authenticator authenticator = new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return (new PasswordAuthentication(PROXY_USERNAME, PROXY_PASSWORD.toCharArray()));
					}
				};
				Authenticator.setDefault(authenticator);
			}

			return (HttpURLConnection) new URL(url).openConnection(proxy);
		} else {
			return (HttpURLConnection) new URL(url).openConnection();
		}
	}

	/**
	 * Create a fake Tweet list
	 * @return
	 */
	public static List<Tweet> getFakeTweets() {
		final ArrayList<Tweet> tweets = new ArrayList<Tweet>();

		// Build a TwitterUser
		TwitterUser user = new TwitterUser();
		user.name = "R&D Worldline";
		user.screenName = "rd_aw";
		user.profileImageUrl = "";

		// Build 20 Tweets
		for(int i=0; i<20; i++) {
			final Tweet tweet = new Tweet();
			tweet.text = "Tweet #" + i;
			tweet.user = user;
			tweets.add(tweet);
		}
		return tweets;
	}


}
