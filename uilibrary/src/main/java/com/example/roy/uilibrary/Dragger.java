package com.example.roy.uilibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;

public class Dragger extends View 
{
	private int m_xVal = 100;
	private int m_yVal = 200;
	private Canvas m_canvas;
	private int m_AlphaVal = 50;
	private int m_ViewTopToButtom = 0;
	float m_percent = 0;
	private static final int MAX_AlPHA = 255;
	private boolean m_IsLocked = false;
	private GestureDetector mDetector;
	
	
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        
        
		@Override
		public void onLongPress(MotionEvent e) 
		{
			m_IsLocked = !m_IsLocked;
			super.onLongPress(e);
			
		}

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) 
        {
  
            return true;
        }
    }
	
	
	public Dragger(Context con,AttributeSet attr)
	{
		super(con,attr);
		mDetector = new GestureDetector(this.getContext(), new MyGestureListener());
	}
	

	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		m_canvas = canvas;
		m_ViewTopToButtom = getBottom()-getTop();
		Rect r = new Rect(0,m_yVal,200,getBottom());
		Paint paint = new Paint();
		m_AlphaVal = (m_ViewTopToButtom-m_yVal+1)*MAX_AlPHA/m_ViewTopToButtom;
		paint.setARGB(m_AlphaVal, 255, 0, 0);
		
		canvas.drawRect(r, paint);
		super.onDraw(canvas);
		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{

		
		mDetector.onTouchEvent(event);
		PointerCoords pc = new PointerCoords();
		int action = event.getActionMasked();
	
		int NumOfPointers = event.getPointerCount();
		
		switch (action)
		{
	
		
/*			event.getPointerCoords(0, pc);
			m_xVal = (int) pc.x;
			m_yVal = (int)pc.y;
			invalidate();
			return true;*/
			
		case MotionEvent.ACTION_UP:
			return true;
			
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_DOWN:
			if(m_IsLocked == true)
				return true;
			event.getPointerCoords(0, pc);
			m_xVal =(int) pc.x;
			m_yVal = (int)pc.y;
			if(m_yVal<0)
				m_yVal = 0;
			m_percent = 100*pc.y/m_ViewTopToButtom;
			invalidate();
			return true;
		
			
		default:
			return super.onTouchEvent(event); 
		}
		

	
	}
	
	
	
}
