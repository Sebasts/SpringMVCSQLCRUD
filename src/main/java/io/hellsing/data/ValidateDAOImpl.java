package io.hellsing.data;

public class ValidateDAOImpl implements ValidateDAO {
	
	
	@Override
	public boolean accountExists(User user) {
		PersistenceDAO pd = new PersistenceDAOImpl();
		return pd.checkIfThisUserExists(user);
	}

	@Override
	public boolean passwordMatches(User user) {
		PersistenceDAO pd = new PersistenceDAOImpl();
		return pd.checkIfThisPasswordMatches(user);
	}


}
