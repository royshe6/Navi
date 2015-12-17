package com.example.roy.navi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnCarButton(View a_View)
    {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    }

    public void OnControllerButton(View a_View)
    {
        Intent intent = new Intent(this, ControllerActivity.class);
        startActivity(intent);
    }

    public void OnMapButton(View a_View)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
