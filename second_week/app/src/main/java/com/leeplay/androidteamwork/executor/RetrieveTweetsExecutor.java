package com.leeplay.androidteamwork.executor;

import android.os.Handler;
import android.os.Looper;

import com.leeplay.androidteamwork.helpers.MastodonHelper;
import com.leeplay.androidteamwork.interfaces.TweetChangeListener;
import com.leeplay.androidteamwork.model.Tweet;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RetrieveTweetsExecutor {

    private ExecutorService mExecutorService;
    private Handler mHandler;
    private TweetChangeListener mListener;

    public RetrieveTweetsExecutor(TweetChangeListener listener) {
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
        mListener = listener;
    }

    public void getTweets(String user) {
        mExecutorService.execute(() -> {
            List<Tweet> tweets = MastodonHelper.getTweetsOfUser(user);
            if (null != tweets) {
                mHandler.post(() -> {
                    mListener.onTweetRetrieved(tweets);
                });
            }
        });
    }

    public List<Tweet> getTweetsRightNow(String user) {

        List<Tweet> tweets = MastodonHelper.getTweetsOfUser(user);

        return tweets;
    }
}
