package io.hellsing.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void writeMemoToDb(User user, String name, String content) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			con.setAutoCommit(false);
			String sqlMemoInsert = "INSERT INTO memo (title, content) VALUES(?,?);";
			String sqlUserMemoInsert = "INSERT INTO user_memo (user_id, memo_id)"
					+" VALUES(?,?);";
			String commit = "COMMIT;";
			PreparedStatement memoStmt = con.prepareStatement(sqlMemoInsert,Statement.RETURN_GENERATED_KEYS);
			PreparedStatement userMemoStmt = con.prepareStatement(sqlUserMemoInsert);
			PreparedStatement commitStmt = con.prepareStatement(commit);
			memoStmt.setString(1, name);
			memoStmt.setString(2, content);
			memoStmt.executeUpdate();
			int newId = 0;
			ResultSet keys = memoStmt.getGeneratedKeys();
			if (keys.next()) {
				newId = keys.getInt(1);
				System.out.println(user.getId());
				System.out.println("new id is = "+ newId);
				userMemoStmt.setInt(1, user.getId());
				userMemoStmt.setInt(2, newId);
			}else{
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
					+ " user_memo um ON um.memo_id = m.id JOIN"
					+ " user u ON u.id = um.user_id WHERE u.email = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				temp.add(new Memo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
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
				if(rs.getString(1).equals(user.getPassword())){
					user.setFirstName(rs.getString(2));
					user.setLastName(rs.getString(3));
					user.setId(rs.getInt(4));
					con.close();
				return true;
				} else{
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
		

	}

	@Override
	public void deleteMemo(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
