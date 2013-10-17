package cmu.vlis.luzhengs.capstone.model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.Picture;

public class PictureDAO extends GenericDAO<Picture> {
	public PictureDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Picture.class, tableName, cp);
	}
		
	// read two random pictures to compete
	public Picture[] getCompetitor() throws RollbackException {
		Picture[] competitor = new Picture[2];
		
		competitor[0] = read((int)(Math.random() * getCount() + 1));
		competitor[1] = read((int)(Math.random() * getCount() + 1));
		while(competitor[0].getPid() == competitor[1].getPid())
			competitor[1] = read((int)(Math.random() * getCount() + 1));

		return competitor;
	}
	
	// get pictures whose rating is higher than 0, in order from high to low
	public Picture[] getHighRatings() throws RollbackException {
		Picture[] result = match(MatchArg.greaterThan("rating", 0.0));
		Arrays.sort(result);
		return result;
	}
	
	public Picture[] getHighRankers(int n) throws RollbackException {
		return match(MatchArg.lessThanOrEqualTo("rank", n));
	}
}
