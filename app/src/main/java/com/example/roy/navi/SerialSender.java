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

    public SerialSender(UsbManager a_UsbManager, TextView TV)
    {
        m_UsbManager = a_UsbManager;
        m_ReceivedSerialTV = TV;
    }

    public int SendData()
    {
        // Find all available drivers from attached devices.
   //     UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(m_UsbManager);
        if (availableDrivers.isEmpty()) {
            return 0;
        }
        else
        {
           m_ReceivedSerialTV.setText("good");
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = m_UsbManager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return 0;
        }

        // Read some data! Most have just one port (port 0).
       // UsbSerialPort port = driver.getPort(0);
        UsbSerialPort port = driver.getPorts().get(0);
        int numBytesRead = 0;
        try
        {

            port.open(connection);
            //port.setBaudRate(115200);
            port.setParameters(9600,UsbSerialPort.DATABITS_8,UsbSerialPort.STOPBITS_1,UsbSerialPort.PARITY_NONE);
            byte buffer[] = new byte[16];
            byte Receivebuffer[] = new byte[16];
            buffer[0] = 'b';
            buffer[1] = 'l';
            buffer[2] = 'a';

            int numOfSentBytes = port.write(buffer,1000);

            numBytesRead = port.read(Receivebuffer, 1000);

            byte[] ReceivedMessage;
            String ReceivedString = "Nothing";

            if(numBytesRead>0)
            {
                ReceivedMessage = Arrays.copyOf(Receivebuffer, numBytesRead);
                ReceivedString = new String(ReceivedMessage);
            }
            m_ReceivedSerialTV.setText(ReceivedString);
           // Log.d(TAG, "Read " + numBytesRead + " bytes.");
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
                port.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return numBytesRead;
    }

}
