package com.example.roy.uilibrary;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class SevenSeg extends View
{
	
	private Bitmap m_BitmapToDisplay;
	private int m_Height;
	private int m_Width;
	private int m_Counter;
	private GestureDetector mDetector;
	private Rect m_Rect;
	private Paint m_Paint;
	private int m_PrevDigit;
	//private Bitmap[] m_BitmapArray;
	private static final int NUM_OF_SEVENSEG_BITMAPS = 11;
	
	
	
	
	 class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
	        
	        
			@Override
			public void onLongPress(MotionEvent e) 
			{
				m_Paint.setColor(m_Paint.getColor()*2);
				super.onLongPress(e);
				
			}

	    }
	
	
	
		
	public SevenSeg(Context con)
	{
		super(con);

		
		
	}
	
	public SevenSeg(Context con,AttributeSet attr)
	{
		super(con,attr);
		BitmapFactory.Options opt = new BitmapFactory.Options();
		
		
		//setBackgroundColor(getResources().getColor(R.color.LightBlue));
//		m_BitmapArray = new Bitmap[NUM_OF_SEVENSEG_BITMAPS];
//		
//		m_BitmapArray[0] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg0);
//		m_BitmapArray[1] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg1);
//		m_BitmapArray[2] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg2);
//		m_BitmapArray[3] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg3);
//		m_BitmapArray[4] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg4);
//		m_BitmapArray[5] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg5);
//		m_BitmapArray[6] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg6);
//		m_BitmapArray[7] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg7);
//		m_BitmapArray[8] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg8);
//		m_BitmapArray[9] = BitmapFactory.decodeResource(getResources(), R.drawable.sevenseg9);
//		m_BitmapArray[10] = BitmapFactory.decodeResource(getResources(), R.drawable.sevensegoff);
		
		
		
		m_PrevDigit = 0;
		
		
		//m_BitmapToDisplay = BitmapFactory.decodeResource(getResources(), R.drawable.sevensegoff);
		mDetector = new GestureDetector(this.getContext(), new MyGestureListener());
		m_Paint = new Paint();
		m_Paint.setColor(getResources().getColor(R.color.LightBlue));
		
	}
	

	
	protected void onDraw(Canvas canvas) 
	{
		
				
		//canvas.drawBitmap(m_Bitmap, 0, 0, m_Paint);
		
//		if(m_Rect!=null)
//		{
			//int col = getResources().getColor(R.color.Red);
			
			canvas.drawRect(m_Rect, m_Paint);
//		}
		
	
		
		
		
		if(m_BitmapToDisplay!=null)
		{
			
			canvas.drawBitmap(m_BitmapToDisplay, 0, 0, null);
		}
		//bringToFront();
		
		super.onDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		
		m_Height = getHeight();
		m_Width = getWidth();
	//	Bitmap TempBitmap;
		
		if(m_Height==0||m_Width==0)
			return;
//		int i;
//		for (i = 0;i<NUM_OF_SEVENSEG_BITMAPS;i++)
//		{
//			if(m_BitmapArray[i]!=null)
//			{
//				TempBitmap = m_BitmapArray[i];
//				m_BitmapArray[i] = Bitmap.createScaledBitmap(TempBitmap, m_Width, m_Height, true);
//				TempBitmap.recycle();
//			}
//		}
	
		//m_BitmapToDisplay = decodeSampledBitmapFromResource(getResources(), R.drawable.sevensegoff, m_Width, m_Height);
//		if(m_BitmapToDisplay!=null && m_Height>0 && m_Width>0)
//		{
	//		TempBitmap = m_BitmapToDisplay;
	//		m_BitmapToDisplay = Bitmap.createScaledBitmap(m_BitmapToDisplay, m_Width, m_Height, true);
	//		TempBitmap.recycle();
	//	}
		m_Rect = new Rect(0,0,m_Width-20,m_Height-20);
		SetDigit(-1);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	public void SetDigit(int a_Digit)
	{
		if(m_PrevDigit==a_Digit)//The digit doesn't change so no need to reload bitmaps
			return;
		
		if (a_Digit>9||a_Digit<-1)
			return;
		
		if(m_Height==0||m_Width==0)
			return;
		
//		if(a_Digit==-1)
//			a_Digit=10;
//		m_BitmapToDisplay = m_BitmapArray[a_Digit];
		
		
		
		int res;
		
		switch(a_Digit)
		{

		case 0:
			res = R.drawable.sevenseg0;
			break;
		case 1:
			res = R.drawable.sevenseg1;
			break;
		case 2:
			res = R.drawable.sevenseg2;
			break;
		case 3:
			res = R.drawable.sevenseg3;
			break;
		case 4:
			res = R.drawable.sevenseg4;
			break;
		case 5:
			res = R.drawable.sevenseg5;
			break;
		case 6:
			res = R.drawable.sevenseg6;
			break;
		case 7:
			res = R.drawable.sevenseg7;
			break;
		case 8:
			res = R.drawable.sevenseg8;
			break;
		case 9:
			res = R.drawable.sevenseg9;
			break;
		case -1:
		default:
			res = R.drawable.sevensegoff;
		break;
					
		}
		if(m_BitmapToDisplay!=null)
			m_BitmapToDisplay.recycle();

		m_BitmapToDisplay = decodeSampledBitmapFromResource(getResources(), res, m_Width, m_Height);
//		m_BitmapToDisplay = BitmapFactory.decodeResource(getResources(),res);
		Bitmap TempBitmap = m_BitmapToDisplay;
		m_BitmapToDisplay = Bitmap.createScaledBitmap(m_BitmapToDisplay, m_Width, m_Height, true);
		TempBitmap.recycle();
		m_PrevDigit = a_Digit;
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		mDetector.onTouchEvent(event);
		int action = event.getActionMasked();

		
		switch(action)
		{
		case MotionEvent.ACTION_DOWN:
//		m_Counter++;
//		m_Counter = m_Counter%10;
//		SetDigit(m_Counter);
		return true;	
				
		}
		return super.onTouchEvent(event);
	
	}
	
	public void Close()
	{
//		int i;
//		for(i=0; i<NUM_OF_SEVENSEG_BITMAPS;i++)
//			m_BitmapArray[i].recycle();
		m_BitmapToDisplay.recycle();
	}
	

	private static int calculateInSampleSize
	(
            BitmapFactory.Options options, int reqWidth, int reqHeight) 
	{
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) 
    {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) 
        {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
	private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) 
	{

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public void ChangeColor()
	{
		m_Paint.setColor(m_Paint.getColor()*4);
	}
	
	public void SetColor (int color)
	{
		m_Paint.setColor(color);
		invalidate();
	}
	
}


