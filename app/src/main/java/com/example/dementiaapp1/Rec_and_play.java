package com.example.dementiaapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dementiaapp1.Adapter.ViewPagerAdapter;
import com.example.dementiaapp1.Fragments.RecorderFragment;
import com.example.dementiaapp1.Fragments.RecordingFragment;
import com.google.android.material.tabs.TabLayout;

public class Rec_and_play extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_and_play);

        toolbar =findViewById(R.id.toolbar_rec_and_play);
        viewPager = findViewById(R.id.viewPager_rec_and_play);
        tabLayout= findViewById(R.id.tabLayout_rec_and_play);
        setSupportActionBar(toolbar);
        setupViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewpager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RecorderFragment(),"Recorder");
        viewPagerAdapter.addFragment(new RecordingFragment(),"Recordings");
        viewPager.setAdapter(viewPagerAdapter);

    }
}