package com.loneyang.shadetextview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;

/**
 * Created by user on 2017/5/9.
 */

public class MyFragment extends Fragment {
    private String mTitle = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTitle = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setTextSize(80);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public static MyFragment newInstance(String title) {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }
}
