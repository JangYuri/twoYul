package com.twoyul.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

import com.twoyul.myapplication.Model.PollDTO;
import com.twoyul.myapplication.Model.PowerPlant;
import com.twoyul.myapplication.Model.PowerPlants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments
     * for each of the sections. We use a {@link FragmentPagerAdapter} derivative,
     * which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    ImageButton imgbtn_close;
    TextView tv_pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar.setTitle("상세정보");

        imgbtn_close = findViewById(R.id.btn_close);
        imgbtn_close.setOnClickListener(this);

        tv_pageTitle = findViewById(R.id.pageTitle);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        int pageNum = getIntent().getIntExtra("number", 0);
        mViewPager.setCurrentItem(pageNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_close:
            Intent mainIntent = new Intent(this, MainMap.class);
            startActivity(mainIntent);
            finish();
            break;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
     * of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<PowerPlant> data = PowerPlants.getInstance();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PowerPlant matchedData = data.get(position);
            return InfoFragment.newInstance(matchedData.getStrOrgCd(), matchedData.getStrSnumPos(),
                    matchedData.getPlantName());
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }
}
