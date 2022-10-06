package com.example.chatapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chatapplication.fragments.callsfragment;
import com.example.chatapplication.fragments.chatsfragment;
import com.example.chatapplication.fragments.statusfragment;

public class fragmentadapter extends FragmentPagerAdapter {
    public fragmentadapter(@NonNull  FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new chatsfragment();
            case 1: return new statusfragment();
            case 2: return new callsfragment();
            default: return new chatsfragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable

    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="CHATS";
        }
        if(position==1){
            title="STATUS";
        }
        if(position==2){
            title="CALLS";
        }
        return title;
    }
}
