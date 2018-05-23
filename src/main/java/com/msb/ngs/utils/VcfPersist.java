package com.msb.ngs.utils;

import java.io.File;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.msb.ngs.model.vcf.VcfEntry;

import htsjdk.samtools.util.CloseableIterator;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;

public class VcfPersist {

	
	public static void main(String[] args) {
		File f = new File("C:\\tmp\\one.vcf");
		
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		
		saveVcf(f,sf);
		
		Query q = sess.createQuery("from vcfentry_table where qual > :qual"); // use to avoid SQL injection
		//sess.createNativeQuery(sqlString)
		//pagination
		q.setFirstResult(10).setMaxResults(2);
		q.setDouble("qual", 0);
		List li = q.list();
		
		for(Object o : li) {
			System.out.println((VcfEntry)o);
		}
		sess.close();
		sf.close();
	}
	
	public static List<VcfEntry> getVcfRecords(SessionFactory sf, String col, String threshold){
		Session sess = sf.openSession();
		sess.beginTransaction();

		Double thr = Double.parseDouble(threshold);
		String column = col;
		Query q = sess.createQuery("from vcfentry_table where "+column+" > :qual"); // use to avoid SQL injection
		//sess.createNativeQuery(sqlString)
		//pagination
		//q.setFirstResult(10).setMaxResults(2);
		q.setParameter("qual", thr);
		List li = q.list();
		
		for(Object o : li) {
			System.out.println((VcfEntry)o);
		}
		
		sess.close();
		return (List<VcfEntry>) li;
	}
	
	public static void saveVcf(File file, SessionFactory sf) {
	try(VCFFileReader vcr = new VCFFileReader(file,false)){

		CloseableIterator<VariantContext>	vcriter = vcr.iterator();

		String sample = vcr.getFileHeader().getSampleNamesInOrder().get(0);
		//SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session sess = sf.openSession();
		sess.beginTransaction();
		
		
		while(vcriter.hasNext()) {
			VariantContext rec = vcriter.next();
			VcfEntry vcrec = new VcfEntry();
			vcrec.setChromosome(rec.getContig());
			vcrec.setPosition(rec.getStart());
			vcrec.setRef(rec.getReference().toString());
			vcrec.setAlt(rec.getAlleles().get(0).toString());
			vcrec.setType(rec.getType().toString());
			String filter = rec.isFiltered() ? "PASS" : "FAIL";
			vcrec.setFilter(filter);
			vcrec.setRsId(rec.getID());
			vcrec.setType(rec.getType().toString());
			vcrec.setAf(Float.parseFloat((String) rec.getAttribute("AF")));
			vcrec.setDp(Integer.parseInt((String) rec.getAttribute("DP")));
			vcrec.setGq(rec.getGenotype(sample).getGQ());
			vcrec.setGt(rec.getGenotype(sample).getGenotypeString());
			vcrec.setQual(rec.getPhredScaledQual());
			vcrec.setInfoString(rec.getAttributes().toString());
			System.out.println(vcrec); 
			
			sess.save(vcrec);

		}
		sess.getTransaction().commit();			
		sess.close();
		//sf.close();

	}finally {
		
	}
	}

}
