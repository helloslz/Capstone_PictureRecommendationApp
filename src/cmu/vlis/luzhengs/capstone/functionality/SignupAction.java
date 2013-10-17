package cmu.vlis.luzhengs.capstone.functionality;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.User;
import cmu.vlis.luzhengs.capstone.model.Model;
import cmu.vlis.luzhengs.capstone.model.UserDAO;

public class SignupAction {
	private UserDAO userDAO;
	
	public SignupAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public User signup() throws RollbackException, IOException {
		User user = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String username, password, email, fullname;
		
		// check username
		while(true) {
			System.out.print("Please input your username: ");
			username = in.readLine();
			user = userDAO.read(username);
	   	    if(user != null) {
	   	    	System.out.println("This username has already be taken!");
	   	    } else {
	   	    	break;
	   	    }
		}
		user = new User();
		
		System.out.print("Please input your password: ");
		password = in.readLine();

		System.out.print("Please input your fullname: ");
		fullname = in.readLine();
		
		System.out.print("Please input your email: ");
		email = in.readLine();
	
		user.setUsername(username);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setEmail(email);
		
		userDAO.addUser(user);
		return user;
	}
}
