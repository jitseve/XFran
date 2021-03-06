package kth.jjve.xfran;
/*
This activity is the view of the homepage of XFran.

Made by:
Jitse van Esch
Elisa Perini
Mariah Sabioni
 */

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import kth.jjve.xfran.viewmodels.HomeVM;

public class HomeActivity extends BaseActivity {
    TextView WODTitle, WODDescription, WODExercises, Username;
    HomeVM homeVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.act_home, contentFrameLayout);


        /*----- HOOKS -----*/
        WODTitle = findViewById(R.id.home_WODTitle);
        WODDescription = findViewById(R.id.home_WODDescription);
        WODExercises = findViewById(R.id.home_WODExercises);
        Username = findViewById(R.id.home_username);
        //Todo: get the planned workouts of the day to the home view
//        PlannedWorkouts = findViewById(R.id.home_rv);

        /*-----  VM  -----*/
        homeVM = ViewModelProviders.of(this).get(HomeVM.class);
        homeVM.init(getApplicationContext());
        homeVM.getUserName().observe(this, s -> Username.setText(s));
        homeVM.getWODTitle().observe(this, s -> WODTitle.setText(s));
        homeVM.getWODDescription().observe(this, s -> WODDescription.setText(s));
        homeVM.getWODExercises().observe(this, ls -> {
            StringBuilder exercises = new StringBuilder();
            for (String s : ls){
                exercises.append(s).append("\n");
            }
            WODExercises.setText(exercises);
        });
    }

}