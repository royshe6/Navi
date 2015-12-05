package com.example.roy.navi;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roy on 28/11/2015.
 */
public class SerialSender
{
    private UsbManager m_UsbManager;
    private TextView m_ReceivedSerialTV;
    private UsbSerialDriver m_SerialDriver;
    private UsbDeviceConnection m_DeviceConnection;
    private UsbSerialPort m_SerialPort;

    public SerialSender(UsbManager a_UsbManager, TextView TV)
    {
        m_UsbManager = a_UsbManager;
        m_ReceivedSerialTV = TV;
        m_SerialDriver = null;
        m_DeviceConnection = null;
        m_SerialPort = null;
    }

    public int OpenConnection()
    {
        // Find all available drivers from attached devices.
        //     UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(m_UsbManager);
        if (availableDrivers.isEmpty()) {
            return 1;
        }
        else
        {
            m_ReceivedSerialTV.setText("good");
        }

        // Open a connection to the first available driver.
        UsbSerialDriver m_SerialDriver = availableDrivers.get(0);
        UsbDeviceConnection m_DeviceConnection = m_UsbManager.openDevice(m_SerialDriver.getDevice());
        if (m_DeviceConnection == null)
        {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return 1;
        }

        try
        {
            m_SerialPort.open(m_DeviceConnection);
            //port.setBaudRate(115200);
            m_SerialPort.setParameters(9600, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
        }

        catch (IOException e)
        {
            // Deal with error.
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    public int SendData(byte[] a_Data)
    {
        if(m_SerialPort==null)
            return 1;
        int numBytesRead = 0;
        try
        {

            m_SerialPort.write(a_Data,1000);
/////////////////////////////////////LoopbackTest//////////////////////////////////
//            byte buffer[] = new byte[16];
//            byte Receivebuffer[] = new byte[16];
//            buffer[0] = 'b';
//            buffer[1] = 'l';
//            buffer[2] = 'a';
//
//            int numOfSentBytes = m_SerialPort.write(buffer,1000);
//
//            numBytesRead = m_SerialPort.read(Receivebuffer, 1000);
//
//            byte[] ReceivedMessage;
//            String ReceivedString = "Nothing";
//
//            if(numBytesRead>0)
//            {
//                ReceivedMessage = Arrays.copyOf(Receivebuffer, numBytesRead);
//                ReceivedString = new String(ReceivedMessage);
//            }
//            m_ReceivedSerialTV.setText(ReceivedString);
//           // Log.d(TAG, "Read " + numBytesRead + " bytes.")

/////////////////////////////////////LoopbackTest - END//////////////////////////////////
        }
        catch (IOException e)
        {
            // Deal with error.
            e.printStackTrace();
            return 0;
        }
        finally
        {
            try
            {
                m_SerialPort.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        m_SerialPort = null;
        return numBytesRead;
    }

    public int CloseConnection()
    {
        if(m_SerialPort==null)
            return 1;

        try
        {
            m_SerialPort.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            m_SerialPort = null;
            return 1;
        }
        m_SerialPort = null;
        return 0;
    }

}
