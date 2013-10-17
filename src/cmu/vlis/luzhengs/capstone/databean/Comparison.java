package cmu.vlis.luzhengs.capstone.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("cid")
public class Comparison {
	private int cid;
	private int uid;
	private int pid_win;
	private int pid_lose;
	private Date date;
	
	public int getCid() 	{ return cid; }
	public int getUid()		{ return uid; 	}
	public int getPid_win()	{ return pid_win; 	}
	public int getPid_lose(){ return pid_lose; 	}
	public Date getDate() 	{ return date; 	}
	   
	public void setCid(int i)		{ cid = i;		} 
	public void setUid(int i) 		{ uid = i;		}
	public void setPid_win(int i) 	{ pid_win = i; 	}
	public void setPid_lose(int i) 	{ pid_lose = i; }
	public void setDate(Date d) 	{ date = d; 	}	
}
