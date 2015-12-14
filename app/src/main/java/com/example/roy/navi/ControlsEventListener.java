package com.example.roy.navi;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Efi on 09/12/2015.
 */
public class ControlsEventListener implements View.OnTouchListener {
    //private ControllerSendThread m_SendThread;
    private byte m_byte_command;

    public ControlsEventListener(/*ControllerSendThread a_SendThread*/)
    {
        //m_SendThread = a_SendThread;
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
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_forward_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 0,2 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x05);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_forward_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 1,2 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x06);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 0 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x01);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 1 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x02);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_backward:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 3 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x08);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_backward_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 0,3 Set, speed is kept
                    m_byte_command = (byte) ((m_byte_command & (byte) 0xE0) | (byte) 0x09);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
            case R.id.button_backward_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Forward Right = bit 1,3 Set, speed is kept
                    m_byte_command = (byte)((m_byte_command & (byte)0xE0) | (byte)0x0A);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Only speed is kept
                    m_byte_command = (byte) 0xE0;
                    return false;
                }
                return false;
        }
        return false;
    }
}
