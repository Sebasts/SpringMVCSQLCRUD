package io.hellsing.data;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface PersistenceDAO {
	void writeMemoToDb(User user,String name, String content, String date);
	List<Memo> loadFromDb(String email);
	boolean checkIfThisUserExists(User user);
	boolean checkIfThisPasswordMatches(User user);
	void writeNewUserToDb(User user);
	void deleteMemo(Integer id, User user);
	void updateMemo(Integer id, String name, String content, User user);
	Map<Integer, String> getSendOffTimes();
	ResultSet getEmailAndMemo(int id);
}
