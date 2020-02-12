package com.monnfamily.aptwitterclone.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.monnfamily.aptwitterclone.R;

public class MyTweets extends AppCompatActivity implements View.OnClickListener {
    EditText et_yourTweet;
    Button btn_sendTweet;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tweets);
    }

    @Override
    public void onClick(View v) {

    }
}
