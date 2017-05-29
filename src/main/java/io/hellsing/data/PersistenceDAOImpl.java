package io.hellsing.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class PersistenceDAOImpl implements PersistenceDAO {

	@Autowired
	private ServletContext context;

	private static final String URL = "jdbc:mysql://localhost:3306/rememberthisdb";
	private static final String USER = "webbot";
	private static final String PASS = "I w1ll go and walk Axl!";

	public PersistenceDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public void writeMemoToDb(User user, String name, String content, String date) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			con.setAutoCommit(false);
			String sqlMemoInsert = "INSERT INTO memo (title, content,reminder_send_off ) VALUES(?,?,?);";
			String sqlUserMemoInsert = "INSERT INTO user_memo (user_id, memo_id)" + " VALUES(?,?);";
			String commit = "COMMIT;";
			PreparedStatement memoStmt = con.prepareStatement(sqlMemoInsert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement userMemoStmt = con.prepareStatement(sqlUserMemoInsert);
			PreparedStatement commitStmt = con.prepareStatement(commit);
			memoStmt.setString(1, name);
			memoStmt.setString(2, content);
			memoStmt.setString(3, date);
			memoStmt.executeUpdate();
			int newId = 0;
			ResultSet keys = memoStmt.getGeneratedKeys();
			if (keys.next()) {
				newId = keys.getInt(1);
				System.out.println(user.getId());
				System.out.println("new id is = " + newId);
				userMemoStmt.setInt(1, user.getId());
				userMemoStmt.setInt(2, newId);
			} else {
				throw new SQLException();
			}
			userMemoStmt.executeUpdate();
			commitStmt.executeUpdate();
			con.close();
			user.setMemos(loadFromDb(user.getEmail()));
		} catch (SQLException e) {
			String rollback = "ROLLBACK;";
			try {
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement rollbackStmt = con.prepareStatement(rollback);
				rollbackStmt.executeUpdate(rollback);
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	@Override
	public List<Memo> loadFromDb(String email) {
		List<Memo> temp = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT m.id, m.title, m.content, m.reminder_send_off FROM memo m JOIN"
					+ " user_memo um ON um.memo_id = m.id JOIN" + " user u ON u.id = um.user_id WHERE u.email = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				temp.add(new Memo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			con.close();
			return temp;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean checkIfThisUserExists(User user) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM user WHERE email=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkIfThisPasswordMatches(User user) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT password, first_name, last_name, id FROM user WHERE email=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(user.getPassword())) {
					user.setFirstName(rs.getString(2));
					user.setLastName(rs.getString(3));
					user.setId(rs.getInt(4));
					con.close();
					return true;
				} else {
					con.close();
					return false;
				}
			}
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public void writeNewUserToDb(User user) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			con.setAutoCommit(false);
			String sqlUserInsert = "INSERT INTO user (first_name, last_name, email, password) VALUES(?,?,?,?);";
			String commit = "COMMIT;";
			PreparedStatement userStmt = con.prepareStatement(sqlUserInsert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement commitStmt = con.prepareStatement(commit);
			userStmt.setString(1, user.getFirstName());
			userStmt.setString(2, user.getLastName());
			userStmt.setString(3, user.getEmail());
			userStmt.setString(4, user.getPassword());
			userStmt.executeUpdate();
			int newId = 0;
			ResultSet keys = userStmt.getGeneratedKeys();
			if (keys.next()) {
				newId = keys.getInt(1);
				System.out.println(user.getId());
				System.out.println("new id is = " + newId);
				user.setId(newId);
			} else {
				throw new SQLException();
			}
			commitStmt.executeUpdate();
			con.close();
			user.setMemos(loadFromDb(user.getEmail()));
		} catch (SQLException e) {
			String rollback = "ROLLBACK;";
			try {
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement rollbackStmt = con.prepareStatement(rollback);
				rollbackStmt.executeUpdate(rollback);
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	@Override
	public void deleteMemo(Integer id, User user) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			con.setAutoCommit(false);
			String sqlUpdateUserMemo = "DELETE FROM user_memo where user_id = ? AND memo_id = ?;";
			String sqlUpdateMemo = "DELETE FROM memo WHERE id = ?;";
			String commit = "COMMIT;";
			PreparedStatement memoDStmt = con.prepareStatement(sqlUpdateUserMemo);
			PreparedStatement memoUStmt = con.prepareStatement(sqlUpdateMemo);
			PreparedStatement commitStmt = con.prepareStatement(commit);
			memoUStmt.setInt(1, id);
			memoDStmt.setInt(1, user.getId());
			memoDStmt.setInt(2, id);
			memoDStmt.executeUpdate();
			memoUStmt.executeUpdate();
			commitStmt.executeUpdate();
			con.close();
			user.setMemos(loadFromDb(user.getEmail()));
		} catch (SQLException e) {
			String rollback = "ROLLBACK;";
			try {
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement rollbackStmt = con.prepareStatement(rollback);
				rollbackStmt.executeUpdate(rollback);
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	@Override
	public void updateMemo(Integer id, String name, String content, User user) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			con.setAutoCommit(false);
			String sqlUpdateMemo = "UPDATE memo SET title = ?, content = ? WHERE memo.id = ?;";
			String commit = "COMMIT;";
			PreparedStatement memoStmt = con.prepareStatement(sqlUpdateMemo);
			PreparedStatement commitStmt = con.prepareStatement(commit);
			memoStmt.setString(1, name);
			memoStmt.setString(2, content);
			memoStmt.setInt(3, id);
			memoStmt.executeUpdate();
			commitStmt.executeUpdate();
			con.close();
			user.setMemos(loadFromDb(user.getEmail()));
		} catch (SQLException e) {
			String rollback = "ROLLBACK;";
			try {
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement rollbackStmt = con.prepareStatement(rollback);
				rollbackStmt.executeUpdate(rollback);
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	@Override
	public Map<Integer, String> getSendOffTimes() {
		Map<Integer, String> memos = new HashMap<>();
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT id, reminder_send_off FROM memo; ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				memos.put(rs.getInt(1), rs.getString(2));
			} 
				con.close();
				return memos;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memos;
	}

	@Override
	public ResultSet getEmailAndMemo(int id) {
		// select u.email, m.title, m.content FROM user u JOIN user_memo um ON
		// u.id = um.user_id JOIN memo m ON m.id = um.memo_id WHERE m.id=39;
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "select u.email, m.title, m.content FROM user u JOIN user_memo um ON u.id = um.user_id JOIN memo m ON m.id = um.memo_id WHERE m.id=?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
