package cmu.vlis.luzhengs.capstone.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("pid")
public class Label {
	// the count of the labels
	private int pid;
	private int nature;
	private int architecture;
	private int art;
	private int life;
	private int fiction;
	private int animal;
	private int people;
	private int cute;
	private int funny;
	
	public int getPid()		{ return pid; 		}
	public int getNature()	{ return nature; 	}
	public int getArchitecture()	{ return architecture; 	}
	public int getArt()		{ return art; 	}
	public int getLife()	{ return life; 	}
	public int getFiction()	{ return fiction; 	}
	public int getAnimal()	{ return animal; 	}
	public int getPeople()	{ return people;	}
	public int getCute()	{ return cute;		}
	public int getFunny()	{ return funny;		}
	
	public void setPid(int i)		{ pid = i;		} 
	public void setNature(int i)	{ nature = i; 	}
	public void setArchitecture(int i)	{ architecture = i; 	}
	public void setArt(int i)		{ art = i; 		}
	public void setLife(int i)		{ life = i; 	}
	public void setFiction(int i)	{ fiction = i; 	}
	public void setAnimal(int i)	{ animal = i; 	}
	public void setPeople(int i)	{ people = i;	}
	public void setCute(int i) 		{ cute = i;		}
	public void setFunny(int i) 	{ funny = i;	}
}
