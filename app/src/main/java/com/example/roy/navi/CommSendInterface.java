package com.example.roy.navi;

import java.net.DatagramSocket;

/**
 * Created by Roy on 17/11/2015.
 */
public interface CommSendInterface
{
    public int initComm(String DestIpAddress, int DestPort);
    public int SendData(byte[] Data, int size);
    public int Connect();
    public DatagramSocket GetSocket();
    //Test2
}
