package com.example.learderboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentLearning extends Fragment {
    View mView;
    TextView tvError;
    private RecyclerView mRvSkills;

    public FragmentLearning() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.learning_fragment,container,false);
        mRvSkills = (RecyclerView) mView.findViewById(R.id.recycler_view2);
        LinearLayoutManager skillLayoutManager=new LinearLayoutManager(mView.getContext(),LinearLayoutManager.VERTICAL,false);
        mRvSkills.setLayoutManager(skillLayoutManager);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            URL hourUrl=ApiUtil.buildHourUrl("/api/hours");
            new LeaderQueryTask().execute(hourUrl);
        } catch (Exception e) {
//            e.printStackTrace();
            Log.d("Error",e.getMessage());
        }
    }
    public class LeaderQueryTask extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl=urls[0];
            String result=null;
            try {
                result=ApiUtil.getJson(searchUrl);
            } catch (IOException e) {
//                e.printStackTrace();
                Log.d("Error:",e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            tvError=mView.findViewById(R.id.tvError2);
            //mProgressBar.setVisibility(View.INVISIBLE);
            if (result==null){
                mRvSkills.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                mRvSkills.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);

            }
            ArrayList<HourLeaders> leaders=ApiUtil.getHourLeadersFromJson(result);
            HoursLeaderAdapter adapter=new HoursLeaderAdapter(leaders);
            mRvSkills.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mProgressBar.setVisibility(View.VISIBLE);
        }
    }
}
