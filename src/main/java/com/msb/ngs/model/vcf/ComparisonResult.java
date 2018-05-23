package com.msb.ngs.model.vcf;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ComparisonResult {
	
	private String file1;
	private String file2;
	private int commonCalls;
	private int newCalls;
	private int missedCalls;
	
	public ComparisonResult() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public int getCommonCalls() {
		return commonCalls;
	}
	public void setCommonCalls(int commonCalls) {
		this.commonCalls = commonCalls;
	}
	public int getNewCalls() {
		return newCalls;
	}
	public void setNewCalls(int newCalls) {
		this.newCalls = newCalls;
	}
	public int getMissedCalls() {
		return missedCalls;
	}
	public void setMissedCalls(int missedCalls) {
		this.missedCalls = missedCalls;
	}
	
	@Override
	public String toString() {
		return "ComparisonResult [file1=" + file1 + ", file2=" + file2
				+ ", commonCalls=" + commonCalls + ", newCalls=" + newCalls
				+ ", missedCalls=" + missedCalls + "]";
	}
	public ComparisonResult(String file1, String file2, int commonCalls,
			int newCalls, int missedCalls) {
		super();
		this.file1 = file1;
		this.file2 = file2;
		this.commonCalls = commonCalls;
		this.newCalls = newCalls;
		this.missedCalls = missedCalls;
	}

	
	
}
