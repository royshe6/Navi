package com.example.roy.navi;

/**
 * Created by Roy on 03/12/2015.
 */
public class ControllerSendThread extends Thread
{
    private byte[] m_CommandToSend;
    private CommSendInterface m_SendInterface;
    private int m_DestPort;
    private String m_DestIpAddress;


    public ControllerSendThread(CommSendInterface a_SendInterface)
    {
        m_SendInterface = a_SendInterface;
        m_CommandToSend = new byte[]{'0'};
        int test;
        test = 1;

    }

    public void Init(String DestIpAddress, int DestPort)
    {
        m_DestIpAddress = DestIpAddress;
        m_DestPort = DestPort;
        //Init Comm
        m_SendInterface.initComm(m_DestIpAddress, m_DestPort);
    }

    public void SetCommandToSend(byte[] a_CommandToSend)
    {
        m_CommandToSend = a_CommandToSend;
    }

    public void run()
    {
        m_SendInterface.Connect();

        while(true)
        {
            int SentBytes = m_SendInterface.SendData(m_CommandToSend, m_CommandToSend.length);

            try
            {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
