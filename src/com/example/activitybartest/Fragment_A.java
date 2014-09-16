package com.example.activitybartest;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("NewApi")
public class Fragment_A extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.out.println("ComputerFragment--->onCreate");
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        System.out.println("ConputerFragment--->onCreateView");
        return inflater.inflate(R.layout.tab_a, container, false);//返回R.layout.tab_a所指的view
    }

    @Override
    public void onStop()
    {
        System.out.println("ConputerFragment--->onStop");
        super.onStop();
    }
}



