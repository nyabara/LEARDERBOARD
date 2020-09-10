package com.example.learderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentStateAdapter {
    private final List<Fragment> mFragmentList=new ArrayList<>();
    private final List<String> lstTitles=new ArrayList<>();

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getItemCount() {
        return lstTitles.size();
    }
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        lstTitles.add(title);

    }

}
