package com.leeplay.androidteamwork.fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leeplay.androidteamwork.R;
import com.leeplay.androidteamwork.model.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private static List<Tweet> mValues;

    public MyTweetRecyclerViewAdapter(List<Tweet> values) {
        mValues = values;
    }

    public void addValues(List<Tweet> tweets) {
        mValues = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tweets, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tweet tweet = mValues.get(position);
        holder.mItem = tweet;
        holder.name.setText(tweet.user.screenName);
        holder.alias.setText(tweet.user.name);
        holder.retweetButton.setTag(position);
        holder.text.loadData(tweet.text, "text/html; charset=utf-8", "UTF-8");

        //holder.web.loadData(tweet.text, "text/html; charset=utf-8", "UTF-8");

        if(null != tweet.user.profileImageUrl) {
            Picasso.get().load(tweet.user.profileImageUrl).fit().into(holder.image);
        }
        // holder.mIdView.setText(mValues.get(position).id);
        // holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView alias;
        public final WebView text;
        public final ImageView image;
        public final Button retweetButton;
        public Tweet mItem;

        public ViewHolder(View binding) {
            super(binding);
            image = itemView.findViewById(R.id.tweetImageView);
            name = itemView.findViewById(R.id.usernameTextView);
            alias = itemView.findViewById(R.id.aliasTextView);
            retweetButton = itemView.findViewById(R.id.retweetButton);
            text = itemView.findViewById(R.id.tweetTextView);
            // mIdView = binding.itemNumber;
            // mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " 'todo'";
        }
    }
}