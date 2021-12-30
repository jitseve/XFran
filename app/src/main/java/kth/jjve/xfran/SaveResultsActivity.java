package kth.jjve.xfran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import kth.jjve.xfran.models.Workout;

import static android.view.View.GONE;

public class SaveResultsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = getClass().getSimpleName();

    /*_________ INTENT _________*/
    private int position;
    private Workout mWorkout;

    /*_________ VIEW _________*/
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    /*------ HOOKS ------*/
    //workout_item
    private TextView mName;
    private TextView mDescription;
    private TextView mExercises;
    private Button workoutItemSaveButton;
    private Button workoutItemPlanButton;
    //results
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_results);

        /*------ HOOKS ------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.main_toolbar);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_save_button);
        mName = findViewById(R.id.workout_name);
        mDescription = findViewById(R.id.workout_description);
        mExercises = findViewById(R.id.workout_exercises);
        workoutItemSaveButton = findViewById(R.id.button_save_wod);
        workoutItemPlanButton = findViewById(R.id.button_plan_wod);

        /*------ INIT ------*/
        setSupportActionBar(toolbar);   // Initialise toolbar
        initNavMenu();                  // Initialise nav menu

        /*------ LISTENERS ------*/
        //cancel button listener
        cancelButton.setOnClickListener(v -> cancel());
        //save button listener
        saveButton.setOnClickListener(v -> saveResult());

        /*------ INTENT ------*/
        //get selected workout position and object from intent
        Intent intent = getIntent();
        position = intent.getIntExtra(WorkoutsTabActivity.WORKOUT_ID,1);
        mWorkout = (Workout) intent.getSerializableExtra(WorkoutsTabActivity.WORKOUT_OBJ);
        Log.i(LOG_TAG, "workout read: "+mWorkout);

        //fill the UI with workout info
        mName.setText(mWorkout.getTitle());
        mDescription.setText(mWorkout.getType());
        String exercises = "";
        ArrayList<String> exercisesArray = mWorkout.getExercises();
        for (String s : exercisesArray){
            exercises += s + "\n";
        }
        mExercises.setText(exercises);

        //hide the buttons from workout_item view
        workoutItemSaveButton.setVisibility(GONE);
        workoutItemPlanButton.setVisibility(GONE);

        //TODO finish this activity --> requires result object to create result fields

    }

    @Override
    protected void onResume(){
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_workouts);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_home){
            Intent intent = new Intent(SaveResultsActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_workouts){
            Intent intent = new Intent(SaveResultsActivity.this, WorkoutsTabActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNavMenu(){
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_workouts);
    }

    public void saveResult(){
        // TODO save result object --> requires result object to save result
        Toast.makeText(this, "Save workout in development", Toast.LENGTH_SHORT).show();
    }

    public void cancel(){
        //cancel
        Intent intent = new Intent (this, WorkoutsTabActivity.class);
        startActivity(intent);
    }
}