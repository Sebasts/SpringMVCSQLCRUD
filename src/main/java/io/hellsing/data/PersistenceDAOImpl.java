package io.hellsing.data;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

import org.springframework.web.context.WebApplicationContext;


public class PersistenceDAOImpl implements PersistenceDAO {
	
//	@Autowired
//	private WebApplicationContext wac;
	
	private static final String ACCOUNTDB="/WEB-INF/accounts.csv";

	@Override
	public void writeNewUserToFile(User user, WebApplicationContext wac) {
		InputStream is = wac.getServletContext().getResourceAsStream(ACCOUNTDB);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(ACCOUNTDB, true));
			pw.println(user.getEmail()+","+user.getPassword());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadFromFile(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doesThisAccountExist(User user, WebApplicationContext wac) {
		if(wac == null){
			System.out.println("wac is null");
		}
		InputStream is = wac.getServletContext().getResourceAsStream(ACCOUNTDB);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line;
		
		try {
			while((line = br.readLine())!=null){
				String[] lineSplit = line.split(",");
				System.out.println(Arrays.deepToString(lineSplit));
				if(lineSplit[0].trim().equals(user.getEmail())){
					return true;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean doesThisPasswordMatch(User user, WebApplicationContext wac) {
		InputStream is = wac.getServletContext().getResourceAsStream(ACCOUNTDB);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line;
		
		try {
			while((line = br.readLine())!=null){
				String[] lineSplit = line.split(",");
				System.out.println(Arrays.deepToString(lineSplit));
				if(lineSplit[1].trim().equals(user.getPassword())){
					return true;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void writeToFile(User user, WebApplicationContext wac) {
		// TODO Auto-generated method stub
		
	}




	

}