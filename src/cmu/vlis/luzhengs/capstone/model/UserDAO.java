package cmu.vlis.luzhengs.capstone.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import cmu.vlis.luzhengs.capstone.databean.User;

public class UserDAO extends GenericDAO<User> {
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(User.class, tableName, cp);
	}
	
	// if the username is in the database, read the user; or return null
	public User read(String username) throws RollbackException {
		User[] users = match(MatchArg.equals("username", username));
		if(users.length == 0)
			return null;
		else
			return users[0];
	}
		
	// for concurrency control
	public void addUser(User user) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(user);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
}
