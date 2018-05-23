package com.msb.ngs.dao;

import java.io.File;
import java.util.List;

import org.hibernate.SessionFactory;

import com.msb.ngs.model.vcf.VcfEntry;

public interface VcfDao {
	
	public void saveVcf(File file, SessionFactory sf);
	public List<VcfEntry> getRecord(SessionFactory sf, String col, String threshold);

}
