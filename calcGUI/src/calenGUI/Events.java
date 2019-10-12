package calenGUI;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Events {
	public int dayT,monthT,yearT,hourStart,minStart,hourEnd,minEnd;
	public String titleT,locationT;
	public Events() {
		
	}
	
	
	public String toString() {
		String st=new String();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat twoDigit= new DecimalFormat("00",symbols);
		String hS,hE,mS,mE;
		hS=twoDigit.format((hourStart));
		hE=twoDigit.format((hourEnd));
		mS=twoDigit.format((minStart));
		mE=twoDigit.format((minEnd));
		
		st="\nTitle: "+titleT+"\nLocation: "+locationT+"\nEvent starts at: "+hS+":"+mS+"\nEvent ends at: "+hE+":"+mE;
		return st;
	}
}
