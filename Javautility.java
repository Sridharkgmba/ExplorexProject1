package Genericutility;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Javautility 
{
	public int Random()
	{
		   java.util.Random ran = new java.util.Random();
		     int random = ran.nextInt(500);
		      return random;
	}
	
	 public String getsystemdata()
	 {
		   Date dt = new Date(0);
		   String date = dt.toGMTString();
		   return date;
		   
	 }
	 public void formatsystemdate()
	 {
		     SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyy hh:mm:ss");
		     Date dt = new Date(0);
		      String format = dateFormat.format(dt);
		      format.replace(":","-");
		         
	 }

}


