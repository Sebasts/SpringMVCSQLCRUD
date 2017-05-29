package io.hellsing.funtionality;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import io.hellsing.data.PersistenceDAO;
import io.hellsing.data.PersistenceDAOImpl;

public class TimeResearcher implements Runnable {

	@Override
	public void run() {
		Map<Integer, String> memos;
		PersistenceDAO pdao = new PersistenceDAOImpl();
		memos = pdao.getSendOffTimes();
		while (true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			memos = pdao.getSendOffTimes();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
			String dateString = sdf.format(date);
			System.out.println(dateString+":00.0");
			for (Integer i : memos.keySet()) {
				System.out.println(memos.get(i) +" "+dateString);
				if (memos.get(i) != null && memos.get(i).equals(dateString+":00.0")) {
					System.out.println("I passed the if test");
					ResultSet rs = pdao.getEmailAndMemo(i);
					try {
						while (rs.next()) {
							Emailer em = new Emailer(rs.getString(1),rs.getString(2),rs.getString(3));
							em.run();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
