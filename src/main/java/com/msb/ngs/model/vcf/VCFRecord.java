package com.msb.ngs.model.vcf;

import com.msb.ngs.exception.VariantExceptions;

import htsjdk.variant.variantcontext.VariantContext;

public class VCFRecord {


	private String chromosome;
	private String ref;
	private String alt;
	private long position;
	private String type ;
	private VariantContext context;
	private String validationType;

	public VCFRecord(String chromosome, String ref, String alt, long position, String validationType) {
		super();
		this.chromosome = chromosome;
		this.ref = ref;
		this.alt = alt;
		this.position = position;
		this.validationType = validationType;
		if(ref.length() == alt.length()){

			this.type = "SNP";

		}else if(ref.length() > alt.length()){

			this.type = "Deletion";

		}else if(ref.length() < alt.length()){

			this.type = "Insertion";

		}
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public VariantContext getContext() {
		return context;
	}

	public void setContext(VariantContext context) {
		this.context = context;
	}

	public VCFRecord(String chromosome, String ref, String alt, long position,
			VariantContext context,String validationType) {
		super();
		this.chromosome = chromosome;
		this.ref = ref;
		this.alt = alt;
		this.position = position;
		this.context = context;
		this.validationType = validationType;

		if(ref.length() == alt.length()){

			this.type = "SNP";

		}else if(ref.length() > alt.length()){

			this.type = "Deletion";

		}else if(ref.length() < alt.length()){

			this.type = "Insertion";

		}
	}

	public String getValidationType() {
		return validationType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}

	@Override
	public boolean equals(Object obj){

		if(obj instanceof VCFRecord){
			VCFRecord vcr = (VCFRecord)obj;
			if(vcr.getType().equals(VariantTypes.SNP) && this.getType().equals(VariantTypes.SNP)){
				if(vcr.getChromosome().equals(this.chromosome) && vcr.getPosition() == (this.position)){
					return true;
				}else{
					return false;
				}
			}else if(vcr.getType().equals(VariantTypes.INSERTION)  && this.getType().equals(VariantTypes.INSERTION)){
				if(vcr.getChromosome().equals(this.chromosome) && vcr.getPosition() == (this.position)){
					return true;
				}else{
					return false;
				}
			}else if(vcr.getType().equals(VariantTypes.DELETION) &&  this.getType().equals(VariantTypes.DELETION)){
				if(vcr.getChromosome().equals(this.chromosome) && vcr.getPosition() >= this.position){
					return true;
				}else{
					return false;
				}
			}
			
		}
		return false;
	}

	@Override
	public String toString() {
		return "VCFRecord [chromosome=" + chromosome + ", ref=" + ref
				+ ", alt=" + alt + ", position=" + position + ", type=" + type
				+ ", context=" + context + ", validationType=" + validationType
				+ "]";
	}
	
	public static  Interval getIntervalrepresentation(VCFRecord vcr) throws VariantExceptions{
		
		if(vcr.getType().equals(VariantTypes.SNP) || vcr.getType().equals(VariantTypes.INSERTION)){
		
			return new Interval(vcr.getRef(),(vcr.getPosition()-1),Long.valueOf(vcr.getPosition()));
			
		}else if(vcr.getType().equals(VariantTypes.DELETION)){
			return new Interval(vcr.getRef(),(vcr.getPosition()-1),Long.valueOf((vcr.getPosition()-1)+(vcr.getRef().length()-vcr.getAlt().length())));
		}else{
			throw new VariantExceptions("unsupported variant type !!");
		}
		
	}

}
