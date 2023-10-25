package com.example.lab1doodle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.lab1doodle.R;
import com.example.lab1doodle.view.CustomLine;
import com.example.lab1doodle.view.CustomText;
import com.example.lab1doodle.view.DrawView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AbstractMainActivities {
    @Override
    public void doodle() {

        CustomLine line = (CustomLine) findViewById(R.id.CusLine);
        float y = line.getY();
//        Log.d("", "onCreate: " + y);
        CustomText txt = (CustomText) findViewById(R.id.CusTxt);
//        txt.setX(x + 5);

        txt.setY(y-30);
        NavigationBarView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
//        DrawView dw = (DrawView)findViewById(R.id.drawLayout);
        bottomNavigationView.setSelectedItemId(R.id.activity1);
        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.activity1){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));


                    overridePendingTransition(0,0);
                    return true;
                }

                else if(id == R.id.activity2){
                    startActivity(new Intent(getApplicationContext(),Part2ActivityActivity.class));
//                    dw.setAnimation(true);

                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doodle();

    }

}