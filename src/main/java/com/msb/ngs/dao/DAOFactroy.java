package com.msb.ngs.dao;

public class DAOFactroy {
	
	
	public static UserDao getUserDao(String type) {
		
		
		switch(type) {
		case "RDBMS":
			return new RdbmsUserDao();
		}
		
		return new RdbmsUserDao();	
	}
	
	public static VcfDao getVcfDao(String type) {
		
		
		switch(type) {
		case "RDBMS":
			return new RdbmsVcfDao();
		}
		
		return new RdbmsVcfDao();	
	}

}
