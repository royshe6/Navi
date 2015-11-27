package com.example.roy.navi;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Roy on 21/11/2015.
 */
public class DisplayManager
{
    private TextView m_SendDataLatTV, m_SendDataLonTV, m_ReceivedDataLatTV, m_ReceivedDataLonTV;
    private Double m_SendLat, m_SendLon, m_ReceiveLat, m_ReceiveLon;
    private boolean m_ViewsInit;

    public DisplayManager(View... a_TextViews)
    {
        int NumOfViews = a_TextViews.length;
        if(NumOfViews==4)
        {
            m_SendDataLatTV = (TextView)a_TextViews[0];
            m_SendDataLonTV = (TextView)a_TextViews[1];
            m_ReceivedDataLatTV = (TextView)a_TextViews[2];
            m_ReceivedDataLonTV = (TextView)a_TextViews[3];
            m_SendLat = 0.0;
            m_SendLon = 0.0;
            m_ReceiveLat = 0.0;
            m_ReceiveLon = 0.0;
            m_ViewsInit = true;
        }
    }

    public void UpdateSentDataDisplay(double Lat, double Lon)
    {
        m_SendLat = Lat;
        m_SendLon = Lon;
    }

    public void UpdateReceiveDataDisplay(double Lat, double Lon)
    {
        m_ReceiveLat = Lat;
        m_ReceiveLon = Lon;//
    }
    public void UpdateDisplay()
    {
        if(m_ViewsInit==false)
            return;

        m_SendDataLatTV.setText(m_SendLat.toString());
        m_SendDataLonTV.setText(m_SendLon.toString());
        m_ReceivedDataLatTV.setText(m_ReceiveLat.toString());
        m_ReceivedDataLonTV.setText(m_ReceiveLon.toString());
    }
}
