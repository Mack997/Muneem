package com.scudderapps.muneem.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.scudderapps.muneem.Fragment.GraphFragment;
import com.scudderapps.muneem.Fragment.TransactionsFragment;

public class MainAdapter extends FragmentPagerAdapter {

    String data[] = {"Transactions", "Summary"};

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new TransactionsFragment();
        }
        if (position == 1) {
            return new GraphFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }
}