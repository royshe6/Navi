package com.example.roy.navi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Roy on 17/11/2015.
 */



public class UdpSender implements CommSendInterface
{
    private int m_DestPort;
    private String m_DestIpAddress;
    private DatagramSocket m_OutSocket;
    InetAddress m_DestIpInetAddress = null;

    @Override
    public int initComm(String DestIpAddress, int DestPort)
    {

        m_DestPort = DestPort;
        m_DestIpAddress = DestIpAddress;

        try
        {
            m_OutSocket = new DatagramSocket();
        } catch (SocketException e)
        {
            e.printStackTrace();
            return 1;
        }
        try {
            m_DestIpInetAddress = InetAddress.getByName(m_DestIpAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    @Override
    public int SendData(byte[] Data, int size)
    {
        DatagramPacket OutPack = new DatagramPacket(Data, size, m_DestIpInetAddress, m_DestPort);
        try
        {
            m_OutSocket.send(OutPack);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    public int Connect()
    {
        //Send pattern to server
        byte[] pattern = {(byte)0x01,(byte)0x23,(byte)0x45,(byte)0x67,(byte) 0x89,(byte)0xAB,(byte)0xCD,(byte)0xEF};

        DatagramPacket OutPack = new DatagramPacket(pattern, pattern.length, m_DestIpInetAddress, m_DestPort);
        try
        {
            m_OutSocket.send(OutPack);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }
    public DatagramSocket GetSocket()
    {
        return m_OutSocket;
    }
}
