package com.msb.ngs.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class NgsUtils {

	private NgsUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String uploadFolder = "C:\\tmp\\";

	
	//Singleton design pattern for only one instance creation.Not singleton in muti threaded code
	/*public static SessionFactory getSessionFactory() {
		if(sf == null) {
			sf =  new Configuration().configure().buildSessionFactory();	
		}
		return sf;
	}*/
	//singleton implementation with nested static class 
	//help: https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom#Example_Java_Implementation
	public static class FactoryCreator{
		public static SessionFactory sf = new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactoryInstance() {
		return FactoryCreator.sf;
	}

	
}
