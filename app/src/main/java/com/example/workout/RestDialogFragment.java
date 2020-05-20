package com.example.workout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class RestDialogFragment extends DialogFragment {

    private long timeLeft;
    private  TextView mCountText;
    public static RestDialogFragment newInstance(String title)
    {
        RestDialogFragment frags = new RestDialogFragment();
        Bundle args = new Bundle();
        frags.setArguments(args);
        args.putString("Title",title);
        return frags;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.rest_dialog_layout,null);
        mCountText = view.findViewById(R.id.restCount);
        alertDialog.setTitle(getArguments().getString("Title"));
        alertDialog.setView(view);
        alertDialog.setCancelable(false);
       new CountDownTimer(5000,1000){

           @Override
           public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateText();
           }

           @Override
           public void onFinish() {
               Toast.makeText(getContext(),"Continuing",Toast.LENGTH_SHORT);
           }
       }.start();

       return alertDialog.create();
    }
    private void updateText()
    {
        int seconds = (int)timeLeft/1000;
        String time = String.format(Locale.getDefault(),"%02d",seconds);
        mCountText.setText(time);
    }
}

