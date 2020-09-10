package com.example.learderboard;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class LeaderBoardActivity extends AppCompatActivity  {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private ViewPageAdapter mPageAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Objects.requireNonNull(getSupportActionBar()).setTitle("LEADERBOARD");
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        mTabLayout=findViewById(R.id.tab_layout);
        mViewPager=findViewById(R.id.pager);
        mPageAdapter=new ViewPageAdapter(this);
        mPageAdapter.addFragment(new FragmentLearning(),"Learning Leaders");
        mPageAdapter.addFragment(new FragmentSkill(),"Skill Iq Leaders");
        mViewPager.setAdapter(mPageAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position==0){
                            tab.setText("Learning Leaders");
                        }
                        else{
                            tab.setText("Skill Iq Leaders");
                        }
                    }
                }
        ).attach();
        //mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.submit:
                startActivity(new Intent(LeaderBoardActivity.this,SubmissionActivity.class));
                break;
            default:
                break;
        }
        return true;
    }
}