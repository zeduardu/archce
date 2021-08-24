package com.arch.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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

	/**
	 * @Lob private byte[] fileUP;
	 * 
	 *      public byte[] getFileUP() { return fileUP; }
	 * 
	 *      public void setFileUP(byte[] fileUP) { this.fileUP = fileUP; }
	 **/
	@ManyToMany(mappedBy = "viewpoints", fetch = FetchType.EAGER)
	private List<Concern> concerns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Concern> getConcerns() {
		return concerns;
	}

	public String getConcernsFormatado() {
		if (concerns == null) {
			return "";
		}
		try {
			String aux = concerns.stream().map(c -> c.getDescription()).sorted().collect(Collectors.joining(", "));
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public void setConcerns(List<Concern> concerns) {
		this.concerns = concerns;
	}

	public String getTypicalStakeholders() {
		if (this.concerns == null) {
			return "";
		}
		return this.concerns.stream().flatMap(c -> Stream.ofNullable(c.getStakeholders())).flatMap(st -> st.stream())
				.map(st -> st.getNome()).distinct().sorted().collect(Collectors.joining(", "));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viewpoint other = (Viewpoint) obj;
		return Objects.equals(id, other.id);
	}

}
