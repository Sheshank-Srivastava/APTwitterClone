package com.monnfamily.aptwitterclone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListItem> {
    Context context;
    ArrayList<String> userList;
    ClickListener clickListener;

    public UserListAdapter(Context context, ArrayList<String> userList, ClickListener clickListener) {
        this.context = context;
        this.userList = userList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public UserListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListItem holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UserListItem extends RecyclerView.ViewHolder{
        public UserListItem(@NonNull View itemView) {
            super(itemView);
        }
    }
    public interface ClickListener{
        void onClickItem(View v);
    }
}
