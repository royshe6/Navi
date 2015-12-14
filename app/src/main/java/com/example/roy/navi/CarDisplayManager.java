package com.example.roy.navi;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Roy on 09/12/2015.
 */
public class CarDisplayManager
{
    private ImageView m_DirectionDisplay;
    private TextView m_ThrottleValDisplay;
    private int m_ThrottleValue;
    private byte m_ReceivedCommand;

//    public CarDisplayManager(ImageView a_ImageView, TextView a_TextView)
//    {
//        m_DirectionDisplay = a_ImageView;
//        m_ThrottleValDisplay = a_TextView;
//    }

    public void UpdateCommandValue(byte a_CommandValue)
    {
        m_ReceivedCommand = a_CommandValue;
    }

    public void UpdateDisplay()
    {
        m_ReceivedCommand = (byte) 0xE9;
        boolean Right, Left, Forward, Backward;
        Right = false;
        Left = false;
        Forward = false;
        Backward = false;
        int test;

        byte Res = (byte) (m_ReceivedCommand & 0x01);
        if(Res == 0x01)
        {
            Right = true;
        }

        Res = (byte)(m_ReceivedCommand & 0x02);
        if(Res==0x02)
        {
            Left = true;
        }

        Res = (byte)(m_ReceivedCommand & 0x04);
        if(Res==0x04)
        {
            Forward = true;
        }

        Res = (byte)(m_ReceivedCommand & 0x08);
        if(Res==0x08)
        {
            Backward = true;
        }

        if(Right==true)
        {
            if(Forward==true)
            {
                test = 1;
                test = 2;
                //Display R&F
            }
            else
            {
                if(Backward==true)
                {
                    test = 1;
                    test = 2;
                    //Display R&B
                }
                test = 1;
                test = 2;
                //Display R
            }

        }

        if(Left==true)
        {
            if(Forward==true)
            {
                test = 1;
                test = 2;
                //Display L&F
            }
            else
            {
                if(Backward==true)
                {
                    test = 1;
                    test = 2;
                    //Display L&B
                }
                test = 1;
                test = 2;
                //Display L
            }

        }

        Res =(byte) ((m_ReceivedCommand>>5) &  0x07);
        m_ThrottleValue = (int)Res;
        test = 1;
        test = 2;

    }


}
