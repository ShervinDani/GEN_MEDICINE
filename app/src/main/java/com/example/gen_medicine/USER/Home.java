package com.example.gen_medicine.USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.gen_medicine.R;
import com.example.gen_medicine.databinding.ActivityHomeBinding;
import com.example.gen_medicine.databinding.ActivityMainBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.FileReader;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomAppBar;
    Fragment fragment=null;
    FloatingActionButton floatingActionButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent=new Intent();
        bottomAppBar=findViewById(R.id.sidebutton);
        bottomAppBar.setSelectedItemId(R.id.uhome);
        floatingActionButton=findViewById(R.id.buy);
        getSupportFragmentManager().beginTransaction().replace(R.id.body,new HomeFragment()).commit();
        bottomAppBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.uhome)
                {
                    fragment=new HomeFragment();
                }
                else if(item.getItemId()==R.id.consult)
                {
                    fragment=new ConsultFragment();
                }
                else if(item.getItemId()==R.id.reminder)
                {
                    fragment=new ReminderFragment();
                }
                else if(item.getItemId()==R.id.sidebar)
                {
                    fragment=new SideBarFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body,fragment).commit();
                return true;
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new BuyFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.body,fragment).commit();
            }
        });
    }
}