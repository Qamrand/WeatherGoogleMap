package com.example.wheathergooglemap.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wheathergooglemap.R;
import com.example.wheathergooglemap.ui.fragment.CityListFragment;
import com.example.wheathergooglemap.ui.fragment.MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.include_fragment)
    FrameLayout mFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //updateFragment(new MapsFragment());
        setBottomNavigationView();

        updateFragment(new MapsFragment(0));
    }

    private void setBottomNavigationView(){
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.my_location_item_menu:
                    //mFrameLayout.setVisibility(View.GONE);
                    updateFragment(new MapsFragment(0));
                    break;
                case R.id.city_list_item_menu:
                    //mFrameLayout.setVisibility(View.GONE);
                    updateFragment(new CityListFragment());
                    break;
                case R.id.city_location_item_menu:
                    //mFrameLayout.setVisibility(View.GONE);
                    updateFragment(new MapsFragment(1));
                    break;
            }
            return true;
        });
    }

    private void updateFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fm.beginTransaction().replace(R.id.include_fragment, fragment).commit();
    }
}