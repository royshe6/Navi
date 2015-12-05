package com.example.roy.navi;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class CarActivity extends ActionBarActivity {

    private MyLocationListener m_LocListen;
    private CarSendThread m_SendThread;
    private CarReceiveThread m_ReceiveThread;
    private CommSendInterface m_CommSendInterface;
    private DisplayManager m_DM;
    private Timer m_Timer;
    private Handler m_Handler;
    private UsbManager m_UsbManager;
    private SerialSender m_SerialSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);
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
        if (id == R.id.action_settings)
        {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onPostCreate(Bundle savedInstanceState)
    {
        m_DM = new DisplayManager(findViewById(R.id.sendLatTextView),findViewById(R.id.SendLonTextView),findViewById(R.id.ReceivedLatTextView),findViewById(R.id.ReceivedLonTextView));
        m_CommSendInterface = new UdpSender();
        m_SendThread = new CarSendThread(m_CommSendInterface, m_DM);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        m_LocListen = new MyLocationListener(m_SendThread);


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, m_LocListen);


        m_Timer = new Timer();

        TimerTask timer_task = new TimerTask() {

            @Override
            public void run()
            {
                m_Handler.obtainMessage(1).sendToTarget();
            }
        };

        m_Handler = new Handler() {
            public void handleMessage(Message msg)
            {
                m_DM.UpdateDisplay();
            }
        };


        m_Timer.schedule(timer_task, 1000l, 1000l);

        //Serial test
        TextView ReceivedSerialTV = (TextView)findViewById(R.id.received_serial_text);

        m_UsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        m_SerialSender = new SerialSender(m_UsbManager, ReceivedSerialTV);


        //////////////////

        super.onPostCreate(savedInstanceState);
    }

    public void Start(View view)
    {
        EditText IpAddressText = (EditText)findViewById(R.id.serveraddress);
        m_SendThread.Init(IpAddressText.getText().toString(), 7001);
        UdpReceiver _UdpReceiver = new UdpReceiver(m_CommSendInterface.GetSocket());
        //m_ReceiveThread = new ReceiveThread(_UdpReceiver, m_DM);
        m_ReceiveThread = new CarReceiveThread(_UdpReceiver,m_SerialSender);
        m_SendThread.start();
        try
        {
            wait(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        m_SendThread.StartSendingData();
        m_ReceiveThread.start();
    }

    public void Send(View view)
    {
        m_SendThread.StartSendingData();

    }

    public void Receive(View view)
    {


        m_ReceiveThread.start();
    }

    public void SendSerial(View view)
    {
        //Test
        byte[] TestBuf = {'b','l','a'};
        m_SerialSender.OpenConnection();
        m_SerialSender.SendData(TestBuf);
    }


}
