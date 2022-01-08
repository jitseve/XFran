package kth.jjve.xfran.repositories;

import android.util.JsonReader;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import kth.jjve.xfran.adapters.AppCtx;
import kth.jjve.xfran.R;
import kth.jjve.xfran.models.Workout;
import kth.jjve.xfran.utils.WorkoutUtils;

public class WorkoutRepo {

    private static WorkoutRepo instance;
    private ArrayList<Workout> dataSet = new ArrayList<>();

    public static WorkoutRepo getInstance() {
        if (instance == null) {
            instance = new WorkoutRepo();
        }
        return instance;
    }

    // Pretend to get data from a webservice or cage or online source
    public MutableLiveData<List<Workout>> getWorkouts() throws IOException {
        setWorkouts(); //Todo: save to firebase to allow custom workouts?
        MutableLiveData<List<Workout>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setWorkouts() throws IOException {
        // For now it reads a json file with workout info
        // open json file
        InputStream workoutData = AppCtx.getContext().getResources().openRawResource(R.raw.workouts_data);
        // initialize reader
        JsonReader reader = new JsonReader(new InputStreamReader(workoutData, StandardCharsets.UTF_8));
        try {
            WorkoutUtils.parseWorkouts(reader, dataSet);
        } finally {
            reader.close();
        }
    }

}
