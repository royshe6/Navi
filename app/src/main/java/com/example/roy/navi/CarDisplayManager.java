package com.example.roy.navi;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Roy on 09/12/2015.
 */
public class CarDisplayManager
{
    //Views
    private Context m_Context;
    private ImageView m_DirectionDisplay;
    private TextView m_ThrottleTV;
    private TextView m_SentLatTV;
    private TextView m_SentLonTV;
    private TextView m_ReceivedSerialTV;

    //Values
    private byte[] m_ReceivedCommand;
    private byte[] m_ReceivedSerial;
    private Double m_SentLatValue;
    private Double m_SentLonValue;

    public CarDisplayManager(Context a_Context, ImageView a_ImageView, TextView a_SentLatTV, TextView a_SentLonTV, TextView a_ThrottleTV, TextView a_ReceivedSerialTV)
    {
        m_Context = a_Context;
        m_DirectionDisplay = a_ImageView;
        m_ThrottleTV = a_ThrottleTV;
        m_SentLatTV = a_SentLatTV;
        m_SentLonTV = a_SentLonTV;
        m_ReceivedSerialTV = a_ReceivedSerialTV;

        m_ReceivedCommand =  new byte[]{0x00};
        m_ReceivedSerial = new byte[]{0x00, 0x01};

        m_SentLatValue = 0.0;
        m_SentLonValue = 0.0;
    }

    public void UpdateCommandValue(byte[] a_CommandValue)
    {
        m_ReceivedCommand = a_CommandValue;
    }

    public void UpdateDisplay()
    {

        boolean Right, Left, Forward, Backward;
        Right = false;
        Left = false;
        Forward = false;
        Backward = false;

        byte Res = (byte) (m_ReceivedCommand[0] & 0x01);
        if(Res == 0x01)
        {
            Right = true;
        }

        Res = (byte)(m_ReceivedCommand[0] & 0x02);
        if(Res==0x02)
        {
            Left = true;
        }

        Res = (byte)(m_ReceivedCommand[0] & 0x04);
        if(Res==0x04)
        {
            Forward = true;
        }

        Res = (byte)(m_ReceivedCommand[0] & 0x08);
        if(Res==0x08)
        {
            Backward = true;
        }

        byte Directions = (byte)(m_ReceivedCommand[0] & 0x0f);
        switch (Directions)
        {
            case 0x01:
                //Right
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowright));
                break;
            case 0x02:
                //Left
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowleft));
                break;
            case 0x04:
                //Forward
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowforward));
                break;
            case 0x05:
                //FR
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowforwardright));
                break;
            case 0x06:
                //FL
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowforwardleft));
                break;
            case 0x08:
                //Backward
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowbackward));
                break;
            case 0x09:
                //BR
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowbackwardright));
                break;
            case 0x0A:
                //BL
                m_DirectionDisplay.setImageDrawable(m_Context.getResources().getDrawable(R.drawable.arrowbackwardleft));
                break;
            default:
                //Error
                break;

        }

        Res =(byte) ((m_ReceivedCommand[0]>>5) &  0x07);
        int ThrottleValue = (int)Res;
        Integer ThrottleVal = ThrottleValue;
        m_ThrottleTV.setText(ThrottleVal.toString());


        m_SentLonTV.setText(m_SentLonValue.toString());
        m_SentLatTV.setText(m_SentLatValue.toString());


        String ReceivedSerialString = new String(m_ReceivedSerial);
        m_ReceivedSerialTV.setText(ReceivedSerialString);
    }

    void SetLonLatValue(double a_Lon, double a_Lat)
    {
        m_SentLonValue = a_Lon;
        m_SentLatValue = a_Lat;
    }

    void SetReceivedSerial(byte[] a_ReceivedSerial)
    {
        m_ReceivedSerial = a_ReceivedSerial;
    }


}
