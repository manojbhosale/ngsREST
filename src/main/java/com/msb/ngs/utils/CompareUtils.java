package com.msb.ngs.utils;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;

import com.msb.ngs.model.vcf.ComparisonResult;
import com.msb.ngs.model.vcf.VCFRecord;

import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.variantcontext.writer.Options;
import htsjdk.variant.variantcontext.writer.VariantContextWriter;
import htsjdk.variant.variantcontext.writer.VariantContextWriterBuilder;
import htsjdk.variant.variantcontext.writer.VariantContextWriterBuilder.OutputType;
import htsjdk.variant.vcf.VCFFileReader;
import htsjdk.variant.vcf.VCFHeader;

public class CompareUtils {


	private static final boolean CREATE_INDEX = false;
	
	public static void main(String[] args) {
		String stamp = new Date().toString();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = dateFormat.format(new Date());
		System.out.println(str);
	}
/*
	public static void main(String args[]) throws IOException{

		
		String destinationFolder;
		File file1 = new File(args[0]);
		File file2 = new File(args[1]);
		
		if(args.length == 3){
			destinationFolder = args[2];
		}else{
			File f = new File("comparisonResults");
			if(!f.exists()){
				f.mkdir();
			}
			destinationFolder = f.getAbsolutePath();
			System.out.println(destinationFolder);
		}


		//VCFFileReader vcr1 = new VCFFileReader(new File("\\\\PTD09449\\CommonStorage\\WorkflowOutput\\NA10831_13Dec2015_19_15_31_967\\NA10831_13Dec2015_19_15_31_967.vcf"));
		//VCFFileReader vcr2 = new VCFFileReader(new File("\\\\PTD09449\\CommonStorage\\WorkflowOutput\\1_AAACATCG_L001_R1_13Dec2015_19_13_30_877\\input1.vcf"));
		VCFFileReader vcr1 = new VCFFileReader(file1);
		VCFFileReader vcr2 = new VCFFileReader(file2);


		ArrayList<VCFRecord> hmvc = new ArrayList<VCFRecord>();
		for(VariantContext vc : vcr1){

			//System.out.println(vc.getChr()+" "+vc.getStart()+" "+vc.getReference()+" "+vc.getAlternateAllele(0)+" "+vc.getPhredScaledQual()+" "+vc.getFilters()+" "+vc.getAttributes());
			String refAllele = (vc.getReference()).toString().replace("*", "");
			//System.out.println(refAllele);
			VCFRecord vcr = new VCFRecord(vc.getChr(), refAllele , (vc.getAlternateAllele(0)).toString(),  Long.valueOf(vc.getStart()),vc,"FN");
			hmvc.add(vcr);
			//System.out.println(vcr);
		}

		
		for(VCFRecord vcr : hmvc){

			System.out.println(vcr);

		}

		
		File f1 = new File(destinationFolder+"/common.vcf");
		File f2 = new File(destinationFolder+"/snpOld.vcf");
		File f3 = new File(destinationFolder+"/insOld.vcf");
		File f4 = new File(destinationFolder+"/delOld.vcf");
		File f5 = new File(destinationFolder+"/snpNew.vcf");
		File f6 = new File(destinationFolder+"/insNew.vcf");
		File f7 = new File(destinationFolder+"/delNew.vcf");
		f1.createNewFile();
		f2.createNewFile();
		f3.createNewFile();
		f4.createNewFile();
		f5.createNewFile();
		f6.createNewFile();
		f7.createNewFile();


		VCFHeader outputHeader = vcr1.getFileHeader();

		final EnumSet<Options> options = CREATE_INDEX ? EnumSet.of(Options.INDEX_ON_THE_FLY) : EnumSet.noneOf(Options.class);
		options.add(Options.ALLOW_MISSING_FIELDS_IN_HEADER);

		final VariantContextWriter pwCommon = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f1).setOptions(options).setOutputFileType(OutputType.VCF).build();
		final VariantContextWriter pwSNPOldexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f2).setOptions(options).setOutputFileType(OutputType.VCF).build();
		final VariantContextWriter pwInsOldexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f3).setOptions(options).setOutputFileType(OutputType.VCF).build();
		final VariantContextWriter pwDelOldexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f4).setOptions(options).setOutputFileType(OutputType.VCF).build();
		final VariantContextWriter pwSNPNewexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f5).setOptions(options).setOutputFileType(OutputType.VCF).build();
		final VariantContextWriter pwInsNewexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f6).setOptions(options).setOutputFileType(OutputType.VCF).build();
		final VariantContextWriter pwDelNewexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f7).setOptions(options).setOutputFileType(OutputType.VCF).build();




		pwSNPOldexclusive.writeHeader(outputHeader);
		pwInsOldexclusive.writeHeader(outputHeader);
		pwDelOldexclusive.writeHeader(outputHeader);
		pwSNPNewexclusive.writeHeader(outputHeader);
		pwInsNewexclusive.writeHeader(outputHeader);
		pwDelNewexclusive.writeHeader(outputHeader);
		pwCommon.writeHeader(outputHeader);

		
	    for (final VariantContext variantContext : sortedOutput) {
	        out.add(variantContext);
	        writeProgress.record(variantContext.getChr(), variantContext.getStart());
	    }
	    out.close();
		
		List<String> typeList = new ArrayList<String>();
		typeList.add("SNP");
		typeList.add("Insertion");
		typeList.add("Deletion");
		 


		for(VariantContext vc : vcr2){
			String refAllele = (vc.getReference()).toString().replace("*", "");

			VCFRecord vcr = new VCFRecord(vc.getChr(),refAllele, (vc.getAlternateAllele(0)).toString(),  Long.valueOf(vc.getStart()),vc,"FP");
			//System.out.println(vcr);
			if(hmvc.contains(vcr)){
				
				int temp = hmvc.indexOf(vcr);
				VCFRecord vcrc = hmvc.get(temp);
				vcrc.setValidationType("TP");
				hmvc.set(temp, vcrc);
			}else{			
				hmvc.add(vcr);

			}
		}


		for(VCFRecord vcr : hmvc){
			if(vcr.getValidationType().equals("TP")){

				pwCommon.add(vcr.getContext());

			}else if(vcr.getValidationType().equals("FP")){

				//if(vcr.getContext().isSNP()){
				if(vcr.getType().equals("SNP")){
					pwSNPNewexclusive.add(vcr.getContext());
				}else if(vcr.getType().equals("Insertion")){
					pwInsNewexclusive.add(vcr.getContext());
				}else if(vcr.getType().equals("Deletion")){
					pwDelNewexclusive.add(vcr.getContext());
				}


			}else if(vcr.getValidationType().equals("FN")){

				if(vcr.getType().equals("SNP")){
					pwSNPOldexclusive.add(vcr.getContext());
				}else if(vcr.getType().equals("Insertion")){
					pwInsOldexclusive.add(vcr.getContext());
				}else if(vcr.getType().equals("Deletion")){
					pwDelOldexclusive.add(vcr.getContext());
				}




			}

		}
		vcr1.close();
		vcr2.close();

		pwCommon.close();
		pwSNPNewexclusive.close();
		pwInsNewexclusive.close();
		pwDelNewexclusive.close();
		pwSNPOldexclusive.close();
		pwInsOldexclusive.close();
		pwDelOldexclusive.close();

		VariantContext vc = null;
		VCFRecord vr = new VCFRecord("chr1","A", "T",Long.valueOf(100) ,vc,"FP");
		VCFRecord vr1 = new VCFRecord("chr2","A", "T",Long.valueOf(100) ,vc,"FP");
		VCFRecord vr2 = new VCFRecord("chr3","A", "T",Long.valueOf(100) ,vc,"FP");
		VCFRecord vr3 = new VCFRecord("chr4","A", "T",Long.valueOf(100) ,vc,"FP");

		ArrayList<VCFRecord> hmvc1 = new ArrayList<VCFRecord>();
		hmvc1.add(vr);
		hmvc1.add(vr1);
		hmvc1.add(vr2);
		hmvc1.add(vr3);

		System.out.println(hmvc1.contains(new VCFRecord("chr4","A", "T",Long.valueOf(100) ,vc,"FP")));
	}

*/
	public static ComparisonResult compareVcfs(File vcfOne, File vcfTwo) throws IOException{
			System.out.println("LeftFile:"+vcfOne);
			System.out.println("RightFile:"+vcfTwo);
			File f = new File("C:\\tmp\\comparison");
			if(!f.exists()){
				f.mkdir();
			}
			String destinationFolder = f.getAbsolutePath();
			System.out.println(destinationFolder);
		
			VCFFileReader vcr1 = new VCFFileReader(vcfOne,false);
			VCFFileReader vcr2 = new VCFFileReader(vcfTwo,false);


			ArrayList<VCFRecord> hmvc = new ArrayList<VCFRecord>();
			for(VariantContext vc : vcr1){


				String refAllele = (vc.getReference()).toString().replace("*", "");

				VCFRecord vcr = new VCFRecord(vc.getChr(), refAllele , (vc.getAlternateAllele(0)).toString(),  Long.valueOf(vc.getStart()),vc,"FN");
				hmvc.add(vcr);

			}
			String stamp = new Date().toString();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = dateFormat.format(new Date());
			System.out.println(str);
			
			File f1 = new File(destinationFolder+"/common"+str+".vcf");
			File f2 = new File(destinationFolder+"/snpOld"+str+".vcf");
			File f3 = new File(destinationFolder+"/insOld"+str+".vcf");
			File f4 = new File(destinationFolder+"/delOld"+str+".vcf");
			File f5 = new File(destinationFolder+"/snpNew"+str+".vcf");
			File f6 = new File(destinationFolder+"/insNew"+str+".vcf");
			File f7 = new File(destinationFolder+"/delNew"+str+".vcf");
			f1.createNewFile();
			f2.createNewFile();
			f3.createNewFile();
			f4.createNewFile();
			f5.createNewFile();
			f6.createNewFile();
			f7.createNewFile();

			VCFHeader outputHeader = vcr1.getFileHeader();
			
			final EnumSet<Options> options = CREATE_INDEX ? EnumSet.of(Options.INDEX_ON_THE_FLY) : EnumSet.noneOf(Options.class);
			options.add(Options.ALLOW_MISSING_FIELDS_IN_HEADER);

			final VariantContextWriter pwCommon = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f1).setOptions(options).setOutputFileType(OutputType.VCF).build();
			final VariantContextWriter pwSNPOldexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f2).setOptions(options).setOutputFileType(OutputType.VCF).build();
			final VariantContextWriter pwInsOldexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f3).setOptions(options).setOutputFileType(OutputType.VCF).build();
			final VariantContextWriter pwDelOldexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f4).setOptions(options).setOutputFileType(OutputType.VCF).build();
			final VariantContextWriter pwSNPNewexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f5).setOptions(options).setOutputFileType(OutputType.VCF).build();
			final VariantContextWriter pwInsNewexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f6).setOptions(options).setOutputFileType(OutputType.VCF).build();
			final VariantContextWriter pwDelNewexclusive = new VariantContextWriterBuilder().setReferenceDictionary(outputHeader.getSequenceDictionary()).setOutputFile(f7).setOptions(options).setOutputFileType(OutputType.VCF).build();
			
			pwSNPOldexclusive.writeHeader(outputHeader);
			pwInsOldexclusive.writeHeader(outputHeader);
			pwDelOldexclusive.writeHeader(outputHeader);
			pwSNPNewexclusive.writeHeader(outputHeader);
			pwInsNewexclusive.writeHeader(outputHeader);
			pwDelNewexclusive.writeHeader(outputHeader);
			pwCommon.writeHeader(outputHeader);

			for(VariantContext vc : vcr2){
				String refAllele = (vc.getReference()).toString().replace("*", "");

				VCFRecord vcr = new VCFRecord(vc.getChr(),refAllele, (vc.getAlternateAllele(0)).toString(),  Long.valueOf(vc.getStart()),vc,"FP");
				//System.out.println(vcr);
				if(hmvc.contains(vcr)){
					
					int temp = hmvc.indexOf(vcr);
					VCFRecord vcrc = hmvc.get(temp);
					vcrc.setValidationType("TP");
					hmvc.set(temp, vcrc);
				}else{			
					hmvc.add(vcr);

				}
			}

			int commonCalls = 0;
			int newCalls = 0;
			int missedCalls = 0;
			
			//System.out.println(pwCommon);
			for(VCFRecord vcr : hmvc){
				if(vcr.getValidationType().equals("TP")){
					//System.out.println(vcr.getContext());
					pwCommon.add(vcr.getContext());
					commonCalls++;

				}else if(vcr.getValidationType().equals("FP")){
					newCalls++;
					//if(vcr.getContext().isSNP()){
					if(vcr.getType().equals("SNP")){
						pwSNPNewexclusive.add(vcr.getContext());
					}else if(vcr.getType().equals("Insertion")){
						pwInsNewexclusive.add(vcr.getContext());
					}else if(vcr.getType().equals("Deletion")){
						pwDelNewexclusive.add(vcr.getContext());
					}
					
					

				}else if(vcr.getValidationType().equals("FN")){
					missedCalls++;
					if(vcr.getType().equals("SNP")){
						pwSNPOldexclusive.add(vcr.getContext());
					}else if(vcr.getType().equals("Insertion")){
						pwInsOldexclusive.add(vcr.getContext());
					}else if(vcr.getType().equals("Deletion")){
						pwDelOldexclusive.add(vcr.getContext());
					}
				}

			}
			vcr1.close();
			vcr2.close();

			pwCommon.close();
			pwSNPNewexclusive.close();
			pwInsNewexclusive.close();
			pwDelNewexclusive.close();
			pwSNPOldexclusive.close();
			pwInsOldexclusive.close();
			pwDelOldexclusive.close();

			ComparisonResult cr = new ComparisonResult(vcfOne.getName(),vcfTwo.getName(),commonCalls, newCalls, missedCalls);
			//System.out.println(cr);
		return cr;
	}
}
