package cmu.vlis.luzhengs.capstone.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import cmu.vlis.luzhengs.capstone.databean.Comparison;

public class ComparisonDAO extends GenericDAO<Comparison> {
	public ComparisonDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Comparison.class, tableName, cp);
	}
		
	public void addComparison(Comparison comm) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(comm);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
