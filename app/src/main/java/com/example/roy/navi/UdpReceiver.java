package com.example.roy.navi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

/**
 * Created by Roy on 19/11/2015.
 */
public class UdpReceiver
{

    private DatagramSocket m_Socket;
    private DatagramPacket m_IncomingPacket;
    private byte[] m_IncomingBuffer;

    public UdpReceiver(DatagramSocket a_Socket)
    {
        m_Socket = a_Socket;
        m_IncomingBuffer = new byte[256];
        m_IncomingPacket = new DatagramPacket(m_IncomingBuffer,m_IncomingBuffer.length);
    }

    public DatagramPacket Receive()
    {
         Arrays.fill(m_IncomingBuffer, (byte) 0);

        try
        {
            m_Socket.receive(m_IncomingPacket);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return m_IncomingPacket;
    }

}
