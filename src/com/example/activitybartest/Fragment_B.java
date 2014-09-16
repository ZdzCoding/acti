package com.example.activitybartest;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class Fragment_B extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.out.println("EidtFragment--->onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        System.out.println("EidtFragment--->onCreateView");
        return inflater.inflate(R.layout.tab_b, container, false);
    }

    @Override
    public void onStop()
    {
        System.out.println("EidtFragment--->onStop");
        super.onStop();
    }
}


