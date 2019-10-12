package calenGUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyRunnable implements Runnable {
	calender fr;
	private Locale l=new Locale("en");
public MyRunnable(calender app) 
	{
		fr=app;
	}
	public void run() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss",l);
		while(true) {
			try {
				Thread.sleep(1000);
				Date date=new Date();
				fr.setTimeLabel(dateFormat.format(date));
			}
			catch(InterruptedException IntEx){
				System.out.println(IntEx.toString());
			}
		}
	}
}

