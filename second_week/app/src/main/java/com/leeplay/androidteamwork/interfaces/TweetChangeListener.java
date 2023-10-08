package com.leeplay.androidteamwork.interfaces;

import com.leeplay.androidteamwork.model.Tweet;

import java.util.List;


public interface TweetChangeListener {
    public void onTweetRetrieved(List<Tweet> tweets);
}
