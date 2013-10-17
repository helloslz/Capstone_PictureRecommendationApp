package cmu.vlis.luzhengs.capstone.functionality;

import java.io.IOException;
import java.util.Date;

import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.Comparison;
import cmu.vlis.luzhengs.capstone.databean.Picture;
import cmu.vlis.luzhengs.capstone.model.ComparisonDAO;
import cmu.vlis.luzhengs.capstone.model.Model;
import cmu.vlis.luzhengs.capstone.model.PictureDAO;
import cmu.vlis.luzhengs.capstone.utility.RateFrame;
import cmu.vlis.luzhengs.capstone.utility.SwingConsole;

public class CompareAction {
	private PictureDAO pictureDAO;
	private ComparisonDAO comparisonDAO;
	
	public CompareAction(Model model) {
		pictureDAO = model.getPictureDAO();
		comparisonDAO = model.getComparisonDAO();
	}
	
	public void compare(int uid, int rateTimes) throws RollbackException, IOException {
		Picture[] competitor;
		Comparison comm;
		for (int i = 0; i < rateTimes; i++) {
			competitor = pictureDAO.getCompetitor();
			RateFrame rf = new RateFrame(competitor[0], competitor[1]);
			SwingConsole.run(rf, 1000, 550);	
			
			synchronized(CompareAction.class) {
				while(RateFrame.finished == false) {
					try {
						CompareAction.class.wait();
					} catch (InterruptedException e) {
						System.out.println("Comparing interrupted.");
						e.printStackTrace();
					}
				}
			}
			rf.dispose();	// remove the previous JFrame window
			
			// add the comparison result into database
			comm = new Comparison();
			if(Integer.parseInt(rf.winner) == competitor[0].getPid()) {
				comm.setPid_win(competitor[0].getPid());
				comm.setPid_lose(competitor[1].getPid());
				
				updateRating(pictureDAO, competitor[0], competitor[1], 1);
			} else {
				comm.setPid_win(competitor[1].getPid());
				comm.setPid_lose(competitor[0].getPid());
				
				updateRating(pictureDAO, competitor[0], competitor[1], 0);
			}
			comm.setUid(uid);
			comm.setDate(new Date());
			comparisonDAO.addComparison(comm);
		
			System.out.format("You have rated %d pairs.\n", i + 1);	
		}
	}
	
	/**
	 * Compute the score of the two competitors according to the current rating
	 * @param compA competitor A
	 * @param compB competitor B
	 * @param result the rating of the user, 1 for compA and 2 for compB
	 * @throws RollbackException 
	 */
	private void updateRating(PictureDAO dao, Picture compA, Picture compB, int result) throws RollbackException {
		int sa, sb; // actual score of A and B in this round
		sa = result;
		sb = 1 - result;
		
		int K = 32;
		// the current rank points of the competitors
		double ra = compA.getRating();	
		double rb = compB.getRating();
		// the expected score of the competitors in this round
		double ea = (double)1 / (1 + Math.pow(10, (rb -ra) / 400));	
		double eb = (double)1 / (1 + Math.pow(10, (ra -rb) / 400));	
		// the updated rank points after this round
		compA.setRating(ra + K * (sa - ea)); 		
		compB.setRating(rb + K * (sb - eb));
		
		dao.update(compA);
		dao.update(compB);
	}
	
}
