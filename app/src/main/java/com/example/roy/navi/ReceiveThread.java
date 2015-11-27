package com.example.roy.navi;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by Roy on 21/11/2015.
 */
public class ReceiveThread extends Thread
{
    private UdpReceiver m_UdpReceiver;
    private DisplayManager m_DM;

    public ReceiveThread(UdpReceiver a_UdpReceiver, DisplayManager a_DM)
    {
        m_UdpReceiver = a_UdpReceiver;
        m_DM = a_DM;
    }

    public void run()
    {
        while(true)
        {
            int i;
            i=1;
            DatagramPacket IncomingPacket = m_UdpReceiver.Receive();
            byte[] ReceiveBuf = IncomingPacket.getData();
            final ByteArrayInputStream bais=new ByteArrayInputStream(ReceiveBuf);
            final DataInputStream dais=new DataInputStream(bais);
            double Lat = 0.0;
            double Lon = 0.0;
            try
            {
                Lat = dais.readDouble();
                Lon = dais.readDouble();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            m_DM.UpdateReceiveDataDisplay(Lat,Lon);
            int a;
            a=1;
        }
    }

}
