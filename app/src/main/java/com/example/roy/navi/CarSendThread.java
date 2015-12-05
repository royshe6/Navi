package com.example.roy.navi;

import android.location.Location;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Roy on 13/11/2015.
 */
public class CarSendThread extends Thread
{

    private int m_DestPort;
    private String m_DestIpAddress;
    private CommSendInterface m_SendInterface;
    private Location m_CurrentLocation;
    private DisplayManager m_DM;
    private boolean m_IsConnect;

    public CarSendThread(CommSendInterface a_SendInterface, DisplayManager a_DM)
    {
        m_SendInterface = a_SendInterface;
        m_CurrentLocation = new Location("Stam");
        m_DM = a_DM;
    }

    public void Init(String DestIpAddress, int DestPort)
    {
        m_DestIpAddress = DestIpAddress;
        m_DestPort = DestPort;
        //Init Comm
        m_SendInterface.initComm(m_DestIpAddress, m_DestPort);
    }

    public synchronized void run()
    {

        //Connect
        if(m_IsConnect==false)
        {
            if(m_SendInterface.Connect()==0)
            {
                m_IsConnect=true;
            }

        }

        byte[] Data;

        try
        {
            wait(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        int test;
        test = 1;

        while(true)
        {
            //Build Out Message

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream daos = new DataOutputStream(baos);


            Double Lat = m_CurrentLocation.getLatitude();
            Double Lon = m_CurrentLocation.getLongitude();
//            TempOverride
//            Lon = 5.1;
//            Lat = 6.2;

            try
            {
                daos.writeDouble(Lat);
                daos.writeDouble(Lon);
                daos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            Data = baos.toByteArray();

            //Updtae SendDisplay
            m_DM.UpdateSentDataDisplay(Lat,Lon);

            //Send Datagram via interface
            m_SendInterface.SendData(Data,Data.length);
            try
            {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }


//            byte[] IncomingBuf = new byte[3];
//            DatagramPacket IncomingPacket = new DatagramPacket(IncomingBuf,3);
//
//            try
//            {
//                m_OutSocket.receive(IncomingPacket);
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            int i;
//            i = 0;
//            IncomingPacket.getAddress();
//            IncomingBuf.toString();
        }
    }


    public void SetCurrentLocation(Location a_CurrentLocation)
    {
        m_CurrentLocation.set(a_CurrentLocation);
    }

    public synchronized void StartSendingData()
    {
        notify();
    }
}
