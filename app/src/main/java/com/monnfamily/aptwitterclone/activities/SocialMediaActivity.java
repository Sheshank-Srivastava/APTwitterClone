package com.monnfamily.aptwitterclone.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monnfamily.aptwitterclone.R;
import com.monnfamily.aptwitterclone.Utilities.AppManager;
import com.monnfamily.aptwitterclone.Utilities.Utilities;
import com.monnfamily.aptwitterclone.adapter.UserListAdapter;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SocialMediaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserListAdapter adapter;
    List<String> mUserList;
    ParseQuery<ParseUser> parseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        mUserList = new ArrayList<>();
        /**
         * below Condition
         */
        AppManager.get().setmFanOf(ParseUser.getCurrentUser().<String>getList("fanOf") == null
                ? new ArrayList<String>()
                : ParseUser.getCurrentUser().<String>getList("fanOf"));

        parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e != null && objects == null) return;
                for (ParseUser object : objects) {
                    mUserList.add(object.getUsername());
                }
                AppManager.get().setmUserList(mUserList);
                loadRecycler();
                Toast.makeText(SocialMediaActivity.this, "Data is loadeded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadRecycler() {
        recyclerView = findViewById(R.id.recyclerView_UserList);
        adapter = new UserListAdapter(SocialMediaActivity.this, mUserList, new UserListAdapter.ClickListener() {
            @Override
            public void onClickItem(View v, int pos) {
                //TODO click event
                CheckBox checkBox = v.findViewById(R.id.check_follow);
                checkBox.setChecked(!checkBox.isChecked());
                String mCheckMessage = checkBox.isChecked() ?
                        Utilities.get().follow(mUserList.get(pos), SocialMediaActivity.this) :
                        Utilities.get().unFollow(mUserList.get(pos), SocialMediaActivity.this);
                Toast.makeText(SocialMediaActivity.this, mCheckMessage + mUserList.get(pos), Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loggin Out");
                progressDialog.show();
                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        progressDialog.dismiss();
                        startActivity(new Intent(SocialMediaActivity.this, LogInActivity.class));
                        finish();
                    }
                });
                return true;
            case R.id.menu_tweet:
                startActivity(new Intent(this,MyTweets.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
