package com.monnfamily.aptwitterclone.model;

public class TweetModel {
    private String mUserName;
    private String mUserTweet;

    public TweetModel(String mUserName, String mUserTweet) {
        this.mUserName = mUserName;
        this.mUserTweet = mUserTweet;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmUserTweet() {
        return mUserTweet;
    }
}
