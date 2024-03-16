package com.example.gen_medicine.PARMACY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.gen_medicine.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class PHomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment fragment=null;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phome);
        bottomNavigationView=findViewById(R.id.sidebutton);
        floatingActionButton=findViewById(R.id.add);
        getSupportFragmentManager().beginTransaction().replace(R.id.body,new PHomeFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.phome)
                {
                    fragment=new PHomeFragment();
                }
                else if(item.getItemId()==R.id.pcart)
                {
                    fragment=new OrderFragment();
                }
                else if(item.getItemId()==R.id.pfeedback)
                {
                    fragment=new FeedbackFragment();
                }
                else if(item.getItemId()==R.id.phistory)
                {
                    fragment=new HistoryFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body,fragment).commit();
                return true;
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new AddFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.body,fragment).commit();
            }
        });
    }
}