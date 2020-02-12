package com.monnfamily.aptwitterclone.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.monnfamily.aptwitterclone.R;
import com.monnfamily.aptwitterclone.Utilities.Utilities;

public class MyTweets extends AppCompatActivity implements View.OnClickListener {
    EditText et_yourTweet;
    Button btn_sendTweet,btn_ViewTweet;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tweets);

        et_yourTweet = findViewById(R.id.et_yourTweet);
        btn_sendTweet = findViewById(R.id.btn_sendTweet);
        btn_ViewTweet = findViewById(R.id.btn_ViewTweet);

        btn_sendTweet.setOnClickListener(this);
        btn_ViewTweet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendTweet:
                String tweet = et_yourTweet.getText().toString().trim();
                if (tweet.equals("")){
                    Toast.makeText(this, "Empty tweet", Toast.LENGTH_SHORT).show();
                    return;
                }
                Utilities.get().sendTweet(tweet,MyTweets.this);
                et_yourTweet.setText("");
                break;
            case R.id.btn_ViewTweet:

                break;
        }
    }
}
