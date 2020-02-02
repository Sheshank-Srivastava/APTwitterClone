package com.monnfamily.aptwitterclone.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.monnfamily.aptwitterclone.R;
import com.parse.ParseInstallation;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ParseInstallation.getCurrentInstallation().saveInBackground();
       String id =ParseInstallation.getCurrentInstallation().get("installationId")+"";
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
    }
}
