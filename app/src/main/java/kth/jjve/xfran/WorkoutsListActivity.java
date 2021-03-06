package kth.jjve.xfran;
/*
Activity to let the user view workout list
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import kth.jjve.xfran.adapters.WorkoutsRecyclerAdapter;
import kth.jjve.xfran.viewmodels.WorkoutVM;

public class WorkoutsListActivity extends BaseActivity implements WorkoutsRecyclerAdapter.ListItemClickListener {

    private final String LOG_TAG = getClass().getSimpleName();

    /*------ VIEW MODEL ------*/
    private WorkoutVM mWorkoutVM;
    private WorkoutsRecyclerAdapter mAdapter;

    /*------ INTENT ------*/
    public static String WORKOUT_ID = "Workout ID";
    public static String WORKOUT_OBJ = "Workout Obj";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.act_workouts, contentFrameLayout);

        /*------ VIEW ------*/
        RecyclerView mRecyclerView = findViewById(R.id.rv_workoutlist);

        /*------ VIEW MODEL ------*/
        mWorkoutVM = ViewModelProviders.of(this).get(WorkoutVM.class);
        mWorkoutVM.init();
        mWorkoutVM.getWorkouts().observe(this, workouts -> mAdapter.notifyDataSetChanged());

        /*------ INIT ------*/
        mAdapter = new WorkoutsRecyclerAdapter(this, mWorkoutVM.getWorkouts().getValue(), this, this::onPlan, this::onSave, this::onView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onListItemClick(int position) {
    }

    public void onPlan(int position) {
        // plan selected workout
        Intent intent = new Intent(this, EventPlanActivity.class);
        intent.putExtra(WORKOUT_ID, position);
        intent.putExtra(WORKOUT_OBJ, Objects.requireNonNull(mWorkoutVM.getWorkouts().getValue()).get(position));
        startActivity(intent);
    }

    public void onSave(int position) {
        // save new result for selected workout
        Intent intent = new Intent(this, ResultSaveActivity.class);
        intent.putExtra(WORKOUT_ID, position);
        intent.putExtra(WORKOUT_OBJ, Objects.requireNonNull(mWorkoutVM.getWorkouts().getValue()).get(position));
        startActivity(intent);
    }

    public void onView(int position) {
        // view all saved results for selected workout
        // Todo: fix that this works
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra(WORKOUT_ID, position);
//        intent.putExtra(WORKOUT_OBJ, Objects.requireNonNull(mWorkoutVM.getWorkouts().getValue()).get(position));
//        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();
    }

}