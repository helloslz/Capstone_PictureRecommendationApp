package cmu.vlis.luzhengs.capstone.functionality;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.User;
import cmu.vlis.luzhengs.capstone.model.Model;
import cmu.vlis.luzhengs.capstone.model.UserDAO;

public class LoginAction {
	private UserDAO userDAO;
	
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public User login() throws RollbackException, IOException {
		User user = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String username, password = "";
		
		// check username
		while(true) {
			System.out.print("Please input your username: ");
			username = in.readLine();
			user = userDAO.read(username);
	   	    if(user == null) {
	   	    	System.out.println("No such user in our database!");
	   	    } else {
	   	    	break;
	   	    }
		}
		
		// check password
		while(true) {
			System.out.print("Please input your password: ");
			password = in.readLine();
			if(!password.equals(user.getPassword()))
				System.out.println("Incorrect password!");
			else
				break;
		}

		return user;
	}
}
