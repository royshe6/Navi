package com.example.roy.navi;

import java.net.DatagramPacket;

/**
 * Created by Roy on 03/12/2015.
 */
public class CarReceiveThread extends Thread
{
    private UdpReceiver m_UdpReceiver;
    private SerialSender m_SerialSender;
    private CarDisplayManager m_CarDM;

    public CarReceiveThread(UdpReceiver a_UdpReceiver, SerialSender a_SerialSender, CarDisplayManager a_CarDM)
    {
        m_CarDM = a_CarDM;
        m_UdpReceiver = a_UdpReceiver;
        m_SerialSender = a_SerialSender;
        m_SerialSender.OpenConnection();
    }
    public void run()
    {

        while(true)
        {
            DatagramPacket IncomingPacket = m_UdpReceiver.Receive();
            byte[] ReceiveBuf = IncomingPacket.getData();
            ReceiveBuf.toString();

            if(ReceiveBuf.length==1)
            {
                //Send byte to SerialSender
                m_SerialSender.SendData(ReceiveBuf);
            }
        }



    }
}
