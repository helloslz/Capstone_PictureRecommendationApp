package cmu.vlis.luzhengs.capstone.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("pid")
public class Picture implements Comparable<Picture> {
	private int pid;
	private int rank;
	private double rating;
	private byte[] image;
	
	public int getPid()			{ return pid; 	}
	public int getRank()		{ return rank; 	}
	public double getRating()	{ return rating;}
	public byte[] getImage() 	{ return image; }
	   
	public void setPid(int i) 	{ pid = i; 	}
	public void setRank(int i) 	{ rank = i; }
	public void setRating(double i){ rating = i;}
	public void setImage(byte[] b) 	{ image = b; }	
	
	@Override
	public int compareTo(Picture other) {
		double diff = other.rating - this.rating;
		if(diff > 0)
			return 1;
		else if(diff < 0)
			return -1;
		else
			return 0;
	}
}
