package com.msb.ngs.resource;

import javax.ws.rs.QueryParam;

public class FileCompareBean {
	
	private @QueryParam("leftFile") String leftName;
	private @QueryParam("rightFile") String rightName;
	
	
	public String getLeftName() {
		return leftName;
	}
	
	public void setLeftName(String leftName) {
		this.leftName = leftName;
	}
	
	public String getRightName() {
		return rightName;
	}
	
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	
	
}
