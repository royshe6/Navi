package com.example.roy.navi;

import java.net.DatagramPacket;
import java.util.Arrays;

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
            int length = IncomingPacket.getLength();
            byte[] SendToSerialBuf = Arrays.copyOf(ReceiveBuf,length);


            m_CarDM.UpdateCommandValue(SendToSerialBuf);

       //     if(SendToSerialBuf.length==1)
            {
                //Send byte to SerialSender
                m_SerialSender.SendData(SendToSerialBuf);
               byte[] ReadFeedback =  m_SerialSender.ReadFeedback();
               if(ReadFeedback!=null)
               {


                   m_CarDM.SetReceivedSerial(ReadFeedback);
               }
            }
        }



    }
}
