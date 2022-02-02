package kth.jjve.xfran;
/*
Activity to let the user view results
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kth.jjve.xfran.adapters.EventAdapter;
import kth.jjve.xfran.adapters.ResultsRecyclerAdapter;
import kth.jjve.xfran.models.EventInApp;
import kth.jjve.xfran.models.Result;
import kth.jjve.xfran.viewmodels.ResultVM;

public class ResultsListActivity extends BaseActivity implements ResultsRecyclerAdapter.ListItemClickListener {

    private final String LOG_TAG = getClass().getSimpleName();

    /*------ VIEW MODEL ------*/
    private ResultVM mResultVM;
    private ResultsRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;

    /*------ INTENT ------*/
    public static String WORKOUT_ID = "Workout ID";
    public static String WORKOUT_OBJ = "Workout Obj";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.act_result_view, contentFrameLayout);


        /*------ VIEW ------*/
        mRecyclerView = findViewById(R.id.rv_resultlist);

        /*------ VIEW MODEL ------*/
        mResultVM = ViewModelProviders.of(this).get(ResultVM.class);
        mResultVM.init();
        mResultVM.getResults().observe(this, this::setRecyclerView);

        /*------ INIT ------*/
        mAdapter = new ResultsRecyclerAdapter(this, mResultVM.getResults().getValue(), this, this::onPlan, this::onView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setRecyclerView(List<Result> resultList) {
        // update UI with data retrieved from firebase
        mRecyclerView.setAdapter(new ResultsRecyclerAdapter(this, resultList, this, this::onPlan, this::onView));
        Log.d(LOG_TAG, "new adapter set for new resultList: " + resultList);
    }

    @Override
    public void onListItemClick(int position) {
    }

    public void onPlan(int position) {
        //start activity to plan workout
        Intent intent = new Intent(this, CalendarViewActivity.class);
        intent.putExtra(WORKOUT_ID, position);
        intent.putExtra(WORKOUT_OBJ, Objects.requireNonNull(mResultVM.getResults().getValue().get(position).getWorkout()));
        Log.i(LOG_TAG, "workout sent: " + mResultVM.getResults().getValue().get(position).getWorkout());
        startActivity(intent);
    }

    public void onView(int position) {
        //start activity to view all results of the workout
        //Todo: fix that this starts working properly
//        Intent intent = new Intent(this, ResultsListWODActivity.class);
//        intent.putExtra(WORKOUT_ID, position);
//        intent.putExtra(WORKOUT_OBJ, Objects.requireNonNull(mResultVM.getResults().getValue().get(position).getWorkout()));
//        Log.i(LOG_TAG, "workout sent: " + mResultVM.getResults().getValue().get(position).getWorkout());
//        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();

    }

}