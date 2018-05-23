package com.msb.ngs.model.vcf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="vcfentry_table")
public class VcfEntry {
	
	@Id
	@GeneratedValue
	@Column(name="variant_id")
	private int recordId;
	@Lob
	private String infoString;
	private String chromosome;
	private Integer position;
	private String ref;
	private String alt;
	private String rsId;
	private String type;
	
	private Double qual;
	private String filter;
	private int dp;
	private Float af;
	private int gq;
	private String gt;
	
	public String getInfoString() {
		return infoString;
	}
	public void setInfoString(String infoString) {
		this.infoString = infoString;
	}
	public Double getQual() {
		return qual;
	}
	public void setQual(Double qual) {
		this.qual = qual;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getRsId() {
		return rsId;
	}
	public void setRsId(String rsId) {
		this.rsId = rsId;
	}	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getChromosome() {
		return chromosome;
	}
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDp() {
		return dp;
	}
	public void setDp(int dp) {
		this.dp = dp;
	}
	public Float getAf() {
		return af;
	}
	public void setAf(Float af) {
		this.af = af;
	}
	public int getGq() {
		return gq;
	}
	public void setGq(int gq) {
		this.gq = gq;
	}
	public String getGt() {
		return gt;
	}
	public void setGt(String gt) {
		this.gt = gt;
	}
	
	
	@Override
	public String toString() {
		return "VCFRecord [recordId=" + recordId + ", chromosome=" + chromosome + ", position=" + position + ", ref="
				+ ref + ", alt=" + alt + ", type=" + type + ", dp=" + dp + ", af=" + af + ", gq=" + gq + ", gt=" + gt
				+ "]";
	}
	
	
	
	
	
	

}
