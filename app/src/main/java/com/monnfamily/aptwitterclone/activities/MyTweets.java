package com.monnfamily.aptwitterclone.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monnfamily.aptwitterclone.R;
import com.monnfamily.aptwitterclone.Utilities.Utilities;
import com.monnfamily.aptwitterclone.adapter.TweetAdapter;
import com.monnfamily.aptwitterclone.model.TweetModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MyTweets extends AppCompatActivity implements View.OnClickListener {
    EditText et_yourTweet;
    Button btn_sendTweet, btn_ViewTweet;
    RecyclerView mRecyc_Tweet;
    ArrayList<TweetModel> mTweetData;
    TweetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tweets);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_yourTweet = findViewById(R.id.et_yourTweet);
        btn_sendTweet = findViewById(R.id.btn_sendTweet);
        btn_ViewTweet = findViewById(R.id.btn_ViewTweet);
        mRecyc_Tweet = findViewById(R.id.recyc_AllTweet);
        btn_sendTweet.setOnClickListener(this);
        btn_ViewTweet.setOnClickListener(this);

        mTweetData = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new TweetAdapter(MyTweets.this, mTweetData);
        mRecyc_Tweet.setLayoutManager(layoutManager);
        mRecyc_Tweet.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendTweet:
                String tweet = et_yourTweet.getText().toString().trim();
                if (tweet.equals("")) {
                    Toast.makeText(this, "Empty tweet", Toast.LENGTH_SHORT).show();
                    return;
                }
                Utilities.get().sendTweet(tweet, MyTweets.this);
                et_yourTweet.setText("");
                break;
            case R.id.btn_ViewTweet:

                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage("Loading Tweets");
                dialog.show();
                try {
                    ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("MyTweet");
                    mTweetData.clear();
                    parseQuery.whereContainedIn("user", ParseUser.getCurrentUser().getList("fanOf"));
                    parseQuery.orderByDescending("updatedAt");
                    parseQuery.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            dialog.dismiss();
                            if (e != null || objects.size() <= 0) return;
                            for (ParseObject tweetObjects : objects) {
                                String userName = tweetObjects.get("user") != null ? tweetObjects.get("user").toString() : "Null";
                                String usertweet = tweetObjects.get("tweet") != null ? tweetObjects.get("tweet").toString() : "Null";
                                mTweetData.add(new TweetModel(userName, usertweet));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
