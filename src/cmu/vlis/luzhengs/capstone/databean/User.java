package cmu.vlis.luzhengs.capstone.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("uid")
public class User {
	private int uid;
	private String fullname;
	private String username;
	private String password;
	private String email;
	
	 public int getUid()			{ return uid; 	}
	 public String getFullname() 	{ return fullname; }
	 public String getUsername()	{ return username; 	}
	 public String getPassword() 	{ return password; 	}
	 public String getEmail() 		{ return email; 	}
	    
	 public void setUid(int i)		{ uid = i;		} 
	 public void setFullname(String s) 	{ fullname = s;	}
	 public void setUsername(String s) 	{ username = s; 	}
	 public void setPassword(String s) 	{ password = s; 	}
	 public void setEmail(String s) 	{ email = s; 		}	
}
