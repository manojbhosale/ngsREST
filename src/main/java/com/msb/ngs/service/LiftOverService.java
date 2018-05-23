package com.msb.ngs.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

import com.msb.ngs.model.liftover.BedInterval;
import com.msb.ngs.model.liftover.ChainException;
import com.msb.ngs.model.liftover.IntervalTree;
import com.msb.ngs.utils.LiftOverDriver;

public class LiftOverService {
	
	
	public static SortedSet<BedInterval> convertCoordinate(String chromosome, Integer start, Integer stop) throws ChainException, IOException {
		
		HashMap<String,IntervalTree<List<Object>>> index = LiftOverDriver.getChainIndex();
		SortedSet<BedInterval> res =  LiftOverDriver.liftOverCoordinate(index, chromosome, start, stop);
		return res;
	}
	
	public static SortedSet<BedInterval> convertCoordinate(String chromosome, Integer start, Integer stop, String strand) throws ChainException, IOException {
		
		HashMap<String,IntervalTree<List<Object>>> index = LiftOverDriver.getChainIndex();
		SortedSet<BedInterval> res =  LiftOverDriver.liftOverCoordinate(index, chromosome, start, stop, strand);
		return res;
	}
	
	

}
