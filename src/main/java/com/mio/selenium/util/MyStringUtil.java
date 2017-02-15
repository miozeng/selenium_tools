package com.mio.selenium.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyStringUtil {
	
	public static  String  changeDateToStr(Date date){
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

}
