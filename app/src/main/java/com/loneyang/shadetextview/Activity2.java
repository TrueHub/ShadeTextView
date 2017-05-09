package com.loneyang.shadetextview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

public class Activity2 extends FragmentActivity {

    private ShadeTextView tab_0;
    private ShadeTextView tab_1;
    private ShadeTextView tab_2;
    private ViewPager vp;

    private String[] titles = new String[]{"热门","分类","订阅"};
    private MyFragment[] fragments = new MyFragment[titles.length];
    ArrayList<ShadeTextView> shadeTextViews = new ArrayList<>();
    private FragmentPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        initView();
        initData();

    }

    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            fragments[i] = MyFragment.newInstance(titles[i]);
        }
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }
        };
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0)
                {
                    ShadeTextView left = shadeTextViews.get(position);
                    ShadeTextView right = shadeTextViews.get(position + 1);

                    left.setDirection(1);
                    right.setDirection(0);
                    Log.e("MSl", positionOffset+"," + position +"," + positionOffsetPixels);
                    left.setmProgress(1-positionOffset);
                    right.setmProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        tab_0 = (ShadeTextView) findViewById(R.id.tab_0);
        tab_1 = (ShadeTextView) findViewById(R.id.tab_1);
        tab_2 = (ShadeTextView) findViewById(R.id.tab_2);

        shadeTextViews.add(tab_0);
        shadeTextViews.add(tab_1);
        shadeTextViews.add(tab_2);

        vp = (ViewPager) findViewById(R.id.vp);
    }
}
