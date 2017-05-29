package io.hellsing.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		String dateString=sdf.format(date);
		System.out.println(dateString);

	}

}
