package com.example.roy.navi;

import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

/**
 * Created by Efi on 09/12/2015.
 */
public class ControlsEventListener implements View.OnTouchListener {
    private ControllerSendThread m_SendThread;
    private byte m_byte_command;
    private byte m_DefaultSpeed;

    public ControlsEventListener(ControllerSendThread a_SendThread)
    {
        m_SendThread = a_SendThread;
        m_DefaultSpeed = (byte)0xE0;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        /*
       MSB                                                                 LSB
        7          6          5         4        3          2       1       0
     Speed(2)   Speed(1)   Speed(0)   Spare   Backward   Forward   Left   Right
        */

        switch (v.getId()) {
            case R.id.button_forward:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward = bit 2 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x04);
                    
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                    
                }
                break;
                
            case R.id.button_forward_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 0,2 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x05);
                    
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                    
                }
                break;

            case R.id.button_forward_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 1,2 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x06);
                    
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                    
                }
                break;

            case R.id.button_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 0 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x01);
                    
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                    
                }
                break;

            case R.id.button_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 1 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x02);
                     
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                     
                }
                break;

            case R.id.button_backward:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 3 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x08);
                     
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                     
                }
                break;

            case R.id.button_backward_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 0,3 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x09);
                     
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;
                     
                }
                break;

            case R.id.button_backward_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 1,3 Set, speed is kept
                    m_byte_command = (byte)((m_byte_command & (byte)0xE0) | (byte)0x0A);
                     
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) m_DefaultSpeed;

                }
                break;

        }
        byte[] ByteToSend = new byte[1];
        Arrays.fill(ByteToSend, m_byte_command);

        m_SendThread.SetCommandToSend(ByteToSend);
         return true;
    }
}
