package io.hellsing.data;

import java.util.List;

import org.springframework.web.context.WebApplicationContext;

public interface PersistenceDAO {
	void writeToFile(User user, WebApplicationContext wac);
	List<Memo> loadFromFile(String email);
	boolean doesThisAccountExist(User user, WebApplicationContext wac);
	boolean doesThisPasswordMatch(User user, WebApplicationContext wac);
	void writeNewUserToFile(User user);
}
