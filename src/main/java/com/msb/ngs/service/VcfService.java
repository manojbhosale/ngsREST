package com.msb.ngs.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.msb.ngs.model.VcfStats;
import com.msb.ngs.model.vcf.ComparisonResult;
import com.msb.ngs.model.vcf.VcfEntry;
import com.msb.ngs.utils.CompareUtils;
import com.msb.ngs.utils.NgsUtils;
import com.msb.ngs.utils.TsTvStatistics;
import com.msb.ngs.utils.VcfPersist;

public class VcfService {

	
	//to get ts tv statistics from the VCF file
	public static VcfStats getTsTvStats(File file, String type) {
		TsTvStatistics tstvstat = new TsTvStatistics(type);
		
		String[] res = tstvstat.calculateTsTvRatio(file);
		return new VcfStats(Integer.parseInt(res[1]), Integer.parseInt(res[2]));
	}
	
	
	//compare two Vcfs
	public static ComparisonResult getComparisonResults(File leftVcf, File rightVcf) throws IOException {
		
		ComparisonResult res = CompareUtils.compareVcfs(leftVcf, rightVcf);
		
		return res;
	}
	
	
	//save vcf which was uploaded
	public static void saveUploadedVcf(String name) {
		VcfPersist.saveVcf(new File(NgsUtils.uploadFolder+name), NgsUtils.getSessionFactoryInstance());
	}
	
	//get Vcf records satisfying the query createrias
	public static List<VcfEntry> getFilteredVariants(String col, String threshold) {
		return VcfPersist.getVcfRecords(NgsUtils.getSessionFactoryInstance(), col, threshold);
	}
}
