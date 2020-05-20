package com.example.workout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;


public class StartExerciseFragment extends DialogFragment {
    private ImageButton plusButton,minusButton;
    private TextView countView;
    private int mCount;
    public static StartExerciseFragment newInstance(String title)
    {
        StartExerciseFragment frag = new StartExerciseFragment();
        Bundle args = new Bundle();

        frag.setArguments(args);
        args.putString("Title",title);
        return frag;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        String title = getArguments().getString("Title");
        alertDialog.setTitle(title);
        View view = inflater.inflate(R.layout.dialog_layout,null);
        alertDialog.setView(view);

       plusButton = view.findViewById(R.id.plusButton);
        minusButton = view.findViewById(R.id.minusButton);
        countView = view.findViewById(R.id.count);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount++;
                countView.setText(String.valueOf(mCount));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount--;
                countView.setText(String.valueOf(mCount));
            }
        });

        alertDialog.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //start Activity
                Intent intent = new Intent(getActivity(),SecondActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ArrayList",MainActivity.mExcercise);
                intent.putExtra("Bundle",args);
                intent.putExtra("Sets",mCount);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel
            }
        });
        return alertDialog.create();
    }
}
