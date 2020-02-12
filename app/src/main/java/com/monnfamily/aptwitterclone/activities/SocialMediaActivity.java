package com.monnfamily.aptwitterclone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monnfamily.aptwitterclone.R;
import com.monnfamily.aptwitterclone.adapter.UserListAdapter;
import com.parse.FindCallback;
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
        parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e != null && objects == null) return;
                for (ParseUser object : objects) {
                    mUserList.add(object.getUsername());

                }
                loadRecycler();
                Toast.makeText(SocialMediaActivity.this, "Data is loadeded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadRecycler() {
        recyclerView = findViewById(R.id.recyclerView_UserList);
        adapter = new UserListAdapter(SocialMediaActivity.this, mUserList, new UserListAdapter.ClickListener() {
            @Override
            public void onClickItem(View v) {
                //TODO click event
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
                ParseUser.getCurrentUser().logOut();
                startActivity(new Intent(SocialMediaActivity.this, LogInActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
