package cmu.vlis.luzhengs.capstone.model;

import java.util.ArrayList;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.Label;

public class LabelDAO extends GenericDAO<Label> {
	public LabelDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Label.class, tableName, cp);
	}
		
	// get the current counts of labels for the current picture
	public Label getRandomLabel() throws RollbackException {
		return read((int)(Math.random() * getCount() + 1));
	}
	
	// update the label counts of the current picture
	public void updateLabels(Label l, ArrayList<String> labels) throws RollbackException {
		for(String s : labels) {
			if(s.equals("funny"))
				l.setFunny(l.getFunny() + 1);
			else if(s.equals("cute"))
				l.setCute(l.getCute() + 1);
			else if(s.equals("animal"))
				l.setAnimal(l.getAnimal() + 1);
			else if(s.equals("nature"))
				l.setNature(l.getNature() + 1);
			else if(s.equals("architecture"))
				l.setArchitecture(l.getArchitecture() + 1);
			else if(s.equals("art"))
				l.setArt(l.getArt() + 1);
			else if(s.equals("fiction"))
				l.setFiction(l.getFiction() + 1);
			else if(s.equals("life"))
				l.setLife(l.getLife() + 1);
			else
				l.setPeople(l.getPeople() + 1);
		}
		update(l);
	}
}