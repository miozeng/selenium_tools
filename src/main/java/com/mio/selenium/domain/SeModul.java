package com.mio.selenium.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.jsonview.GenericJsonView;

@Entity
@Table(name = "se_modul")
public class SeModul extends AbstractTimestampEntity{
	@Id
    @GeneratedValue
    @Column(name="modul_id")
	@JsonView(GenericJsonView.Summary.class)
	private Long modulId;
	
	@Column(name="modul_name",nullable = false, length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String modulName;
	
	@Column(name="modul_desc",length=3000)
	@JsonView(GenericJsonView.Summary.class)
	private String modulDesc;
	
	@Column
	@JsonView(GenericJsonView.Summary.class)
	private Integer sequence;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	@JsonView(GenericJsonView.Summary.class)
	private SeProject project;
	
	@OneToMany(mappedBy = "modul", fetch=FetchType.LAZY)
	private List<SeTestCase> testcases ;


	@Column(name = "excute_date")
	@JsonView(GenericJsonView.Summary.class)
	private Date excuteDate;
	

	public Long getModulId() {
		return modulId;
	}

	public void setModulId(Long modulId) {
		this.modulId = modulId;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public String getModulDesc() {
		return modulDesc;
	}

	public void setModulDesc(String modulDesc) {
		this.modulDesc = modulDesc;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	

	public SeProject getProject() {
		return project;
	}

	public void setProject(SeProject project) {
		this.project = project;
	}

	public List<SeTestCase> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<SeTestCase> testcases) {
		this.testcases = testcases;
	}

	public Date getExcuteDate() {
		return excuteDate;
	}

	public void setExcuteDate(Date excuteDate) {
		this.excuteDate = excuteDate;
	}


	
	
}
