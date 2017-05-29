package io.hellsing.data;

import java.util.List;

import org.springframework.web.context.WebApplicationContext;

public interface PersistenceDAO {
	void writeMemoToDb(User user,String name, String content);
	List<Memo> loadFromDb(String email);
	boolean checkIfThisUserExists(User user);
	boolean checkIfThisPasswordMatches(User user);
	void writeNewUserToDb(User user);
	void deleteMemo(Integer id, User user);
	void updateMemo(Integer id, String name, String content, User user);
}
