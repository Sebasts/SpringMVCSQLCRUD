package io.hellsing.data;

import java.util.List;

import org.springframework.web.context.WebApplicationContext;

public interface PersistenceDAO {
	void writeToDb(User user, WebApplicationContext wac);
	List<Memo> loadFromDb(String email);
	boolean doesThisAccountExist(User user, WebApplicationContext wac);
	boolean doesThisPasswordMatch(User user, WebApplicationContext wac);
	void writeNewUserToDB(User user);
}
