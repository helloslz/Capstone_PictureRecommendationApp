package cmu.vlis.luzhengs.capstone.functionality;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.User;
import cmu.vlis.luzhengs.capstone.model.Model;

public class Controller {
	public static void main(String[] args) {
		Model m;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		User user = new User();
		try {
			m = new Model();
			// use login 
			while(!input.equals("1") || input.equals("2")) {
				System.out.print("login(1) or signup(2): ");
				input = in.readLine();
				if(input.equals("1"))
					user = new LoginAction(m).login();
				else if(input.equals("2"))
					user = new SignupAction(m).signup();
			}
			System.out.println("You have logged in successfully!");
			
			System.out.println("-------------------------");
			new CompareAction(m).compare(user.getUid(), 3);
			
			System.out.println("-------------------------");
			new RankAction(m).updateRank();
			new RankAction(m).getHighRankers(10);
			
			System.out.println("-------------------------");
			new LabelAction(m).labelPictures(2);
			
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
