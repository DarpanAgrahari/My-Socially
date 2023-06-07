package com.msh.frontend;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class FinalActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    HomeFragment homeFragment=new HomeFragment();
    ActivityFragment activityFragment=new ActivityFragment();
    MoreFragment settingsFragment=new MoreFragment();
    NotificationFragment notificationFragment=new NotificationFragment();
    AccountFragment accountFragment=new AccountFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.activity:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, activityFragment).commit();
                        return true;
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                        return true;
                    case R.id.more:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }/*@Override
    public void onBackPressed() {
        // Check if the current fragment is the Notification fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (currentFragment instanceof AccountFragment) {
            // If it is, replace it with the Home fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new HomeFragment());
            transaction.commit();

            // Update the selected item in the navigation bar
            BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
            navigationView.setSelectedItemId(R.id.home);
        }
        else if(currentFragment instanceof MoreFragment)
        {

            // If it is, replace it with the Home fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new HomeFragment());
            transaction.commit();

            // Update the selected item in the navigation bar
            BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
            navigationView.setSelectedItemId(R.id.home);
        }
       /* else if(currentFragment instanceof ActivityFragment)
        {

            // If it is, replace it with the Home fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new HomeFragment());
            transaction.commit();

            // Update the selected item in the navigation bar
            BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
            navigationView.setSelectedItemId(R.id.home);
        }

        else {
            // If it is not, let the super class handle the back button press
            super.onBackPressed();
        }
    }*/


}


