package com.arch.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Viewpoint implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	
	private String rationale;
	private String model;
	private String conventions;
	private String source;
	
    
	public List<Stakeholder> getStakeholders() {
		return concern.getStakeholders();
	}
/**	
	@Lob
	private byte[] fileUP;
	
	public byte[] getFileUP() {
		return fileUP;
	}
	
	public void setFileUP(byte[] fileUP) {
		this.fileUP = fileUP;
	}  **/
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="concern_id"))
	private Concern concern;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRationale() {
		return rationale;
	}
	public void setRationale(String rationale) {
		this.rationale = rationale;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getConventions() {
		return conventions;
	}
	public void setConventions(String conventions) {
		this.conventions = conventions;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	
	public Concern getConcern() {
		return concern;
	}
	public void setConcern(Concern concern) {
		this.concern = concern;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
