package com.example.easypay_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import app.com.sample.R;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    public static DatabaseClass myAppDatabase;
    public static SecurityHandler sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        //Shto tab
        tabLayout.addTab(tabLayout.newTab().setText("Faqja1"));
        tabLayout.addTab(tabLayout.newTab().setText("Faqja2"));
        tabLayout.addTab(tabLayout.newTab().setText("API"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),DatabaseClass.class,"userdata_Table").allowMainThreadQueries().build();

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}


/*
<?xml version="1.0" encoding="utf-8"?>

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Fetch_Activity">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="-40dp"
        android:padding="16sp"
        android:scrollbarSize="25sp"
        tools:context=".MainActivity">

        <LinearLayout
            android:id="@+id/mylayout_api"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center_horizontal"
            android:orientation="vertical"
            android:padding="8dp">



            <Button android:id="@+id/btnPauseService"
                android:layout_width="270dp"
                android:layout_height="wrap_content"
                android:text="pause_service"/>
        </LinearLayout>

    </ScrollView>
</FrameLayout>
 */