package cmu.vlis.luzhengs.capstone.functionality;

import java.io.IOException;

import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.Picture;
import cmu.vlis.luzhengs.capstone.model.Model;
import cmu.vlis.luzhengs.capstone.model.PictureDAO;

public class RankAction {
	private PictureDAO pictureDAO;
	
	public RankAction(Model model) {
		pictureDAO = model.getPictureDAO();
	}
	
	// update the rank of pictures with high ratings
	public void updateRank() throws RollbackException, IOException {
		Picture[] highRanker = pictureDAO.getHighRatings();
		for (int i = 0; i < highRanker.length; i++) {
			highRanker[i].setRank(i + 1);
			pictureDAO.update(highRanker[i]);
		}
	}
	
	// get top n ranked pictures
	public void getHighRankers(int n) throws RollbackException {
		System.out.format("The top %d pictures are:\n", n);
		for(Picture p : pictureDAO.getHighRankers(n)) {
			System.out.println(p.getPid());
		}
		return;
	}
}
