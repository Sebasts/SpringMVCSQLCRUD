package io.hellsing.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class ValidateDAOImpl implements ValidateDAO {
	
	
	@Autowired
	WebApplicationContext wac;
	
	@Override
	public boolean accountExists(User user) {
		PersistenceDAO pd = new PersistenceDAOImpl();
		return pd.doesThisAccountExist(user, wac);
	}

	@Override
	public boolean passwordMatches(User user) {
		PersistenceDAO pd = new PersistenceDAOImpl();
		return pd.doesThisPasswordMatch(user, wac);
	}


}
