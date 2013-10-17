package cmu.vlis.luzhengs.capstone.model;

import java.io.IOException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;


public class Model {
	private UserDAO userDAO;
	private ComparisonDAO comparisonDAO;
	private PictureDAO pictureDAO;
	private LabelDAO labelDAO;

	// initialize the database connection and table creation
	public Model() throws RollbackException, IOException {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcURL    = "jdbc:mysql:///capstone";
			
		ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
		try {	
			userDAO  = new UserDAO(pool, "user");
			comparisonDAO = new ComparisonDAO(pool, "comparison");
			pictureDAO = new PictureDAO(pool, "picture");
			labelDAO = new LabelDAO(pool, "label");
		} catch (DAOException e) {
			System.out.println("Failed in connect to db.");
			e.printStackTrace();
		}
	}

	
	public UserDAO getUserDAO()  				{ return userDAO; 		}
	public ComparisonDAO getComparisonDAO()		{ return comparisonDAO;	}
	public PictureDAO getPictureDAO()			{ return pictureDAO;	}
	public LabelDAO getLabelDAO()				{ return labelDAO;		}
}
