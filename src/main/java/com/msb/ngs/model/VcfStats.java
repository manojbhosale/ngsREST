package com.msb.ngs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VcfStats {


	
	private Integer numTs;
	private Integer numTv;
	private Integer tsTvRatio;
	
	public VcfStats() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public VcfStats(Integer numTs, Integer numTv) {
		super();
		this.numTs = numTs;
		this.numTv = numTv;
		try {
		this.tsTvRatio = numTs/numTv;
		}catch(ArithmeticException e) {
			tsTvRatio = null;
			System.out.println(e.getMessage());
		}
	}



	public Integer getNumTs() {
		return numTs;
	}
	public void setNumTs(Integer numTs) {
		this.numTs = numTs;
	}
	public Integer getNumTv() {
		return numTv;
	}
	public void setNumTv(Integer numTv) {
		this.numTv = numTv;
	}
	public Integer getTsTvRatio() {
		return tsTvRatio;
	}
	public void setTsTvRatio(Integer tsTvRatio) {
		this.tsTvRatio = tsTvRatio;
	}
	
	
	

}
