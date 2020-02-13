package com.monnfamily.aptwitterclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monnfamily.aptwitterclone.R;
import com.monnfamily.aptwitterclone.model.TweetModel;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetHolder> {
    Context context;
    List<TweetModel> mData;

    public TweetAdapter(Context context, List<TweetModel> list) {
        this.context = context;
        this.mData = list;
    }

    @NonNull
    @Override
    public TweetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tweet_item, parent, false);
        TweetHolder holder = new TweetHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TweetHolder holder, int position) {
        TweetModel data = mData.get(position);
        holder.txt_UserName.setText(data.getmUserName());
        holder.txt_Tweet.setText(data.getmUserTweet());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TweetHolder extends RecyclerView.ViewHolder {
        TextView txt_UserName, txt_Tweet;

        public TweetHolder(@NonNull View itemView) {
            super(itemView);
            txt_Tweet = itemView.findViewById(R.id.txt_userTweet);
            txt_UserName = itemView.findViewById(R.id.txtUserName);

        }
    }
}
