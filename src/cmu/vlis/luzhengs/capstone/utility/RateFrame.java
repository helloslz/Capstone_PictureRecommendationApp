package cmu.vlis.luzhengs.capstone.utility;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import cmu.vlis.luzhengs.capstone.databean.Picture;
import cmu.vlis.luzhengs.capstone.functionality.CompareAction;

@SuppressWarnings("serial")
public class RateFrame extends JFrame {
	private JButton b1, b2;
	Picture compA, compB;
	public String winner;
	public static boolean finished;
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// use a lock to notify the main thread when a button is pressed
			synchronized(CompareAction.class) {
				winner = e.getActionCommand();
				finished = true;
				CompareAction.class.notifyAll();
			}
		}
	};

	/**
	 * Construct a new Frame, adding two buttons
	 * @param competitorA
	 * @param competitorB
	 * @throws IOException 
	 */
	public RateFrame(Picture competitorA, Picture competitorB) throws IOException {
		finished = false;
		
		compA = competitorA;
		compB = competitorB;
		setLayout(new GridLayout(1, 2)); 
		Image scaledImageA = ImageIO.read(
				new ByteArrayInputStream(compA.getImage())).getScaledInstance(450, 450, Image.SCALE_DEFAULT);
		b1 = new JButton(new ImageIcon(scaledImageA));
		b1.setActionCommand(String.valueOf(compA.getPid()));
		
		Image scaledImageB = ImageIO.read(
				new ByteArrayInputStream(compB.getImage())).getScaledInstance(450, 450, Image.SCALE_DEFAULT);
		b2 = new JButton(new ImageIcon(scaledImageB));
		b2.setActionCommand(String.valueOf(compB.getPid()));
		
		b1.addActionListener(listener);
		b2.addActionListener(listener);
	    add(b1); 
	    add(b2); 
	}
}
