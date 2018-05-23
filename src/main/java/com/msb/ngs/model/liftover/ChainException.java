package com.msb.ngs.model.liftover;


public class ChainException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8101021563504232128L;
	String chainProblem = "Some problem with chain!!!";

	public ChainException(){
		
	}
	
	public ChainException(String msg){
		
		System.out.println("");
		
	}
	
	public void printChainException(){
		
		System.out.println(this.chainProblem);
		
	}
	
	
}

