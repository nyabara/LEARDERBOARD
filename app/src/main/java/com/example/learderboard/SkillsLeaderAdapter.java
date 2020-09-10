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

public class SkillsLeaderAdapter extends RecyclerView.Adapter<SkillsLeaderAdapter.SkillsViewHolder> {
    ArrayList<Leader> mLeaders;
    public SkillsLeaderAdapter(ArrayList<Leader> leaders) {
        mLeaders = leaders;
    }
    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View itemView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new SkillsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        Leader leader=mLeaders.get(position);
        holder.bind(leader);

    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView score;
        TextView country;
        ImageView mImageView;

        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            score=itemView.findViewById(R.id.score);
            country=itemView.findViewById(R.id.country);
        }
        public void bind(Leader leader){
            name.setText(leader.name);
            score.setText(leader.score + " skill IQ Score,");
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
