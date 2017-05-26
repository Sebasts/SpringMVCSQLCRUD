package io.hellsing.data;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;


public class PersistenceDAOImpl implements PersistenceDAO {
	
	@Autowired
	private ServletContext context;
	
	private static final String URL="jdbc:mysql://localhost:3306/rememberthisdb";
	
	
	public PersistenceDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}
	@Override
	public void writeToDb(User user, WebApplicationContext wac) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Memo> loadFromDb(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean doesThisAccountExist(User user, WebApplicationContext wac) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean doesThisPasswordMatch(User user, WebApplicationContext wac) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void writeNewUserToDB(User user) {
		// TODO Auto-generated method stub
		
	}

	
}
