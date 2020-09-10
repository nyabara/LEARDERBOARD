package com.example.learderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HoursLeaderAdapter extends RecyclerView.Adapter<HoursLeaderAdapter.HoursViewHolder> {
    ArrayList<HourLeaders> mLeaders;
    public HoursLeaderAdapter(ArrayList<HourLeaders> leaders) {
        mLeaders = leaders;
    }
    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View itemView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new HoursViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
        HourLeaders leader=mLeaders.get(position);
        holder.bind(leader);

    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView score;
        TextView country;
        ImageView mImageView;

        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            score=itemView.findViewById(R.id.score);
            country=itemView.findViewById(R.id.country);
        }
        public void bind(HourLeaders leader){
            name.setText(leader.name);
            score.setText(leader.hours + " Learning hours,");
            country.setText(leader.country+".");
            showImage(leader.badgeUrl);
        }
        private void showImage(String badgeUrl) {
            if (badgeUrl!=null&&!badgeUrl.isEmpty()){
                Picasso.get().load(badgeUrl).resize(50,50).centerCrop().into(mImageView);
            }
        }
    }


}
