package com.monnfamily.aptwitterclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monnfamily.aptwitterclone.R;
import com.monnfamily.aptwitterclone.Utilities.AppManager;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListItem> {
    private Context context;
    private List<String> userList;
    private ClickListener clickListener;
    private List<String> mFanOf;
    public UserListAdapter(Context context, List<String> userList, ClickListener clickListener) {
        this.context = context;
        this.userList = userList;
        this.clickListener = clickListener;
        mFanOf = AppManager.get().getmFanOf();
    }

    @NonNull
    @Override
    public UserListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_item,parent,false);
        UserListItem userListItem = new UserListItem(view);
        return userListItem;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListItem holder, int position) {
        boolean isFan =mFanOf.contains(userList.get(position));

        holder.txt_UserName.setText(userList.get(position));
        holder.checkBox.setChecked(isFan);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickItem(v,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserListItem extends RecyclerView.ViewHolder{
        TextView txt_UserName;
        CheckBox checkBox;
        public UserListItem(@NonNull View itemView) {
            super(itemView);
            txt_UserName = itemView.findViewById(R.id.txt_UserName);
            checkBox = itemView.findViewById(R.id.check_follow);
        }
    }
    public interface ClickListener{
        void onClickItem(View v,int position);
    }
}
