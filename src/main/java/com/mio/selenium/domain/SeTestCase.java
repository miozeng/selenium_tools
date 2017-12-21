package com.mio.selenium.domain;

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
@Table(name = "se_test_case")
public class SeTestCase  extends AbstractTimestampEntity{
	
	@Id
    @GeneratedValue
    @Column(name="case_id")
	@JsonView(GenericJsonView.Summary.class)
	private Long caseId;
	
	@Column(name="case_name",nullable = false, length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String caseName;
	
	@Column(name="case_desc",length=3000)
	@JsonView(GenericJsonView.Summary.class)
	private String caseDesc;
	
	@Column(name="case_no",nullable = false, length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String caseNo;
	
	@Column
	@JsonView(GenericJsonView.Summary.class)
	private Integer sequence;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "modul_id", nullable = false)
	@JsonView(GenericJsonView.Summary.class)
	private SeModul modul;
	
	@OneToMany(mappedBy = "testCase", fetch=FetchType.EAGER)
	private List<SeTestStep> steps;

	@JsonView(GenericJsonView.Summary.class)
	private Boolean success;


	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public SeModul getModul() {
		return modul;
	}

	public void setModul(SeModul modul) {
		this.modul = modul;
	}

	public List<SeTestStep> getSteps() {
		return steps;
	}

	public void setSteps(List<SeTestStep> steps) {
		this.steps = steps;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}




	
	
	

}
