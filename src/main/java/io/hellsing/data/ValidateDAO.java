package io.hellsing.data;

public interface ValidateDAO {
	boolean accountExists(User user);
	boolean passwordMatches(User user);

}
