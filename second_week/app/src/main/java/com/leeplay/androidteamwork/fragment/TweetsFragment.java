package com.leeplay.androidteamwork.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leeplay.androidteamwork.activity.MainActivity;
import com.leeplay.androidteamwork.R;
import com.leeplay.androidteamwork.executor.RetrieveTweetsExecutor;
import com.leeplay.androidteamwork.interfaces.TweetChangeListener;
import com.leeplay.androidteamwork.model.Tweet;
import com.leeplay.androidteamwork.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class TweetsFragment extends Fragment implements TweetChangeListener {

    private RetrieveTweetsExecutor mExecutor;
    private MyTweetRecyclerViewAdapter mAdapter;
    private List<Tweet> mTweets = new ArrayList<>();

    //private RecyclerView mRecyclerView;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TweetsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TweetsFragment newInstance(int columnCount) {
        TweetsFragment fragment = new TweetsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            mAdapter = new MyTweetRecyclerViewAdapter(mTweets);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String login = PreferenceUtils.getLogin();
        if (!TextUtils.isEmpty(login)) {
            mExecutor = new RetrieveTweetsExecutor(this);
            mExecutor.getTweets(login);
        }
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {

        for (Tweet tweet : tweets) {
            Log.i(String.valueOf(MainActivity.class.getClass()), "tweet: " + tweet);
        }
        mAdapter.addValues(tweets);
        mAdapter.notifyDataSetChanged();


    }
}