package com.example.workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ViewHolder> {

    ArrayList<ExerciseList> mExerciseData;
    Context mContext;
    public ExerciseListAdapter(ArrayList<ExerciseList> data,Context context)
    {
        mExerciseData = data;
        mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mDuration;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDuration = itemView.findViewById(R.id.durationTime);
            imageView = itemView.findViewById(R.id.exerciseImage);
        }
    }
    @NonNull
    @Override
    public ExerciseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseListAdapter.ViewHolder holder, int position) {
        String text = mExerciseData.get(position).getmDuration() + " minutes";
        holder.mDuration.setText(text);
        holder.imageView.setImageResource(mExerciseData.get(position).getmImageResource());
    }

    @Override
    public int getItemCount() {
        return mExerciseData.size();
    }
}
