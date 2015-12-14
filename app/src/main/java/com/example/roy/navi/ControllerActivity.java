package com.example.roy.navi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class ControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        UdpSender udp_sender = new UdpSender();

        ControllerSendThread controller_send_thread = new ControllerSendThread(udp_sender);
        controller_send_thread.Init ("192.168.1.15",7001);
        ControlsEventListener EventListener = new ControlsEventListener(controller_send_thread);
        controller_send_thread.start();

        ImageView Forward = (ImageView) findViewById(R.id.button_forward);
        ImageView ForwardRight = (ImageView) findViewById(R.id.button_forward_right);
        ImageView ForwardLeft = (ImageView) findViewById(R.id.button_forward_left);
        ImageView Right = (ImageView) findViewById(R.id.button_right);
        ImageView Left = (ImageView) findViewById(R.id.button_left);
        ImageView Backward = (ImageView) findViewById(R.id.button_backward);
        ImageView BackwardRight = (ImageView) findViewById(R.id.button_backward_right);
        ImageView BackwardLeft = (ImageView) findViewById(R.id.button_backward_left);


        Forward.setOnTouchListener(EventListener);
        ForwardRight.setOnTouchListener(EventListener);
        ForwardLeft.setOnTouchListener(EventListener);
        Right.setOnTouchListener(EventListener);
        Left.setOnTouchListener(EventListener);
        Backward.setOnTouchListener(EventListener);
        BackwardRight.setOnTouchListener(EventListener);
        BackwardLeft.setOnTouchListener(EventListener);

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




}
