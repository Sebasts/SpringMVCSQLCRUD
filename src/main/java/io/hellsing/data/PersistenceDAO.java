package io.hellsing.data;

import org.springframework.web.context.WebApplicationContext;

public interface PersistenceDAO {
	void writeToFile(User user, WebApplicationContext wac);
	void loadFromFile(String email);
	boolean doesThisAccountExist(User user, WebApplicationContext wac);
	boolean doesThisPasswordMatch(User user, WebApplicationContext wac);
	void writeNewUserToFile(User user);
}
