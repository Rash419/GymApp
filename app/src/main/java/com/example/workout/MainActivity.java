package com.example.workout;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<ExerciseList> mExcercise =  new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ExerciseListAdapter mAdapter;
    private String set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ExerciseListAdapter(mExcercise,this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void initialize()
    {
        String[] durationList = getResources().getStringArray(R.array.exercise_duration);
        TypedArray exerciseImage = getResources().obtainTypedArray(R.array.imageExercise);
        mExcercise.clear();
        ArrayList<Integer> randomUnique = new ArrayList<>();
        for(int i=0;i<14;i++)
        {
            randomUnique.add(i);
        }
        Collections.shuffle(randomUnique);
        for(int i=0;i<5;i++)
        {
            mExcercise.add(new ExerciseList(durationList[randomUnique.get(i)],exerciseImage.getResourceId(randomUnique.get(i),0)));
        }
        mAdapter.notifyDataSetChanged();
        exerciseImage.recycle();
    }
    void showDialog()
    {
        FragmentManager fm = getSupportFragmentManager();
        StartExerciseFragment alertDialog = StartExerciseFragment.newInstance(getString(R.string.selectText))   ;
        alertDialog.show(fm, "fragment_alert");
    }


}
