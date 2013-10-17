package cmu.vlis.luzhengs.capstone.utility;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cmu.vlis.luzhengs.capstone.databean.Picture;
import cmu.vlis.luzhengs.capstone.functionality.LabelAction;

@SuppressWarnings("serial")
public class LabelFrame extends JFrame {
	private JButton submit;
	private JCheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9;
	Picture compA, compB;
	public ArrayList<String> selectedLabels = new ArrayList<String>();
	public static boolean finished;
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// use a lock to notify the main thread when a button is pressed
			synchronized(LabelAction.class) {
				if(cb1.isSelected())
					selectedLabels.add("funny");
				if(cb2.isSelected())
					selectedLabels.add("cute");
				if(cb3.isSelected())
					selectedLabels.add("life");
				if(cb4.isSelected())
					selectedLabels.add("nature");
				if(cb5.isSelected())
					selectedLabels.add("architecture");
				if(cb6.isSelected())
					selectedLabels.add("art");
				if(cb7.isSelected())
					selectedLabels.add("people");
				if(cb8.isSelected())
					selectedLabels.add("animal");
				if(cb9.isSelected())
					selectedLabels.add("fiction");
				
				finished = true;
				LabelAction.class.notifyAll();
			}
		}
	};
	
	public LabelFrame(Picture pic) throws IOException {
		finished = false;
		
		Image scaledImage = ImageIO.read(
				new ByteArrayInputStream(pic.getImage())).getScaledInstance(450, 450, Image.SCALE_DEFAULT);
		
		cb1 = new JCheckBox("funny");
		cb2 = new JCheckBox("cute");
		cb3 = new JCheckBox("life");
		cb4 = new JCheckBox("nature");
		cb5 = new JCheckBox("architecture");
		cb6 = new JCheckBox("art");
		cb7 = new JCheckBox("people");
		cb8 = new JCheckBox("animal");
		cb9 = new JCheckBox(" fiction");
		submit = new JButton("submit");
		submit.addActionListener(listener);
		
	    Panel choices = new Panel();
	    choices.add(cb1); choices.add(cb2); choices.add(cb3); 
	    choices.add(cb4); choices.add(cb5); choices.add(cb6);
	    choices.add(cb7); choices.add(cb8); choices.add(cb9);
	    choices.add(submit);
		
		setLayout(new FlowLayout()); 
	    add(new JLabel(new ImageIcon(scaledImage))); 
	    add(choices); 
	}
}
