package cmu.vlis.luzhengs.capstone.functionality;

import java.io.IOException;

import org.genericdao.RollbackException;

import cmu.vlis.luzhengs.capstone.databean.Label;
import cmu.vlis.luzhengs.capstone.databean.Picture;
import cmu.vlis.luzhengs.capstone.model.LabelDAO;
import cmu.vlis.luzhengs.capstone.model.Model;
import cmu.vlis.luzhengs.capstone.model.PictureDAO;
import cmu.vlis.luzhengs.capstone.utility.LabelFrame;
import cmu.vlis.luzhengs.capstone.utility.SwingConsole;

public class LabelAction {
	private LabelDAO labelDAO;
	private PictureDAO pictureDAO;
	
	public LabelAction(Model model) {
		labelDAO = model.getLabelDAO();
		pictureDAO = model.getPictureDAO();
	}
	
	// get a random picture for the user to label
	public void labelPictures(int times) throws RollbackException, IOException {
		Label label;
		Picture picture;
		for(int i = 0; i < times; i ++) {
			label = labelDAO.getRandomLabel();
			picture = pictureDAO.read(label.getPid());
			LabelFrame lf = new LabelFrame(picture);
			SwingConsole.run(lf, 700, 550);
			
			synchronized(LabelAction.class) {
				while(LabelFrame.finished == false) {
					try {
						LabelAction.class.wait();
					} catch (InterruptedException e) {
						System.out.println("Labeling interrupted.");
						e.printStackTrace();
					}
				}
			}
			lf.dispose();	// remove the previous JFrame window
			
			// update the labeling result into database
			labelDAO.updateLabels(label, lf.selectedLabels);
			
			System.out.format("You have labeled %d pictures.\n", i + 1);	
		}
	}
}
