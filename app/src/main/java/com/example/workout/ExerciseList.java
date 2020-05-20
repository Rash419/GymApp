package com.example.workout;

import  java.io.Serializable;

public class ExerciseList implements Serializable{
    private String mDuration;
    private int mImageResource;

    public ExerciseList(String duration,int id)
    {
        mDuration = duration;
        mImageResource = id;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmDuration() {
        return mDuration;
    }
}
