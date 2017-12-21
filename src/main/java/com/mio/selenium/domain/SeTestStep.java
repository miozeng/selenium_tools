package com.mio.selenium.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.jsonview.GenericJsonView;

@Entity
@Table(name = "se_test_step")
public class SeTestStep  extends AbstractTimestampEntity implements Comparable<SeTestStep>{

	@Id
    @GeneratedValue
    @Column(name="step_id")
	@JsonView(GenericJsonView.Summary.class)
	private Long stepId;
	
	@Column(name="step_name",nullable = false, length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String stepName;
	
	@Column(name="step_desc",length=3000)
	@JsonView(GenericJsonView.Summary.class)
	private String stepDesc;
	
	@Column(name="step_no",nullable = false, length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String stepNo;
	
	@Column
	@JsonView(GenericJsonView.Summary.class)
	private Integer sequence;
	
	@Column(name="take_photo")
	@JsonView(GenericJsonView.Summary.class)
	private boolean takePhoto;
	
	@Column(name="element_name", length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String elementName;
	
	@JsonView(GenericJsonView.Summary.class)
	@Column(name="element_value",length=500)
	private String elementValue;
	
	@JsonView(GenericJsonView.Summary.class)
	@Column(name="element_seq")
	private Integer elementSeq;
	
	@JsonView(GenericJsonView.Summary.class)
	@Enumerated(EnumType.STRING)
	private SeGetType getType;
	
	
	@JsonView(GenericJsonView.Summary.class)
	@Column(name="do_type")
	@Enumerated(EnumType.STRING)
	private SeDoType doType;
	
	@JsonView(GenericJsonView.Summary.class)
	@Column(name="excpt_value",length=3000)
	private String excptValue;
	
	@JsonView(GenericJsonView.Summary.class)
	@Column(name="wait_time")
	private Long waitTime;
	
	@JsonView(GenericJsonView.Summary.class)
	@Column( length=500)
	private String url;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "testcase_id", nullable = false)
	private SeTestCase testCase;
	
	@JsonView(GenericJsonView.Summary.class)
	private Boolean success;
	
	@JsonView(GenericJsonView.Summary.class)
	@Column( length=500)
	private String image;



	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public boolean getTakePhoto() {
		return takePhoto;
	}

	public void setTakePhoto(boolean takePhoto) {
		this.takePhoto = takePhoto;
	}

	public SeDoType getDoType() {
		return doType;
	}

	public void setDoType(SeDoType doType) {
		this.doType = doType;
	}

	public String getExcptValue() {
		return excptValue;
	}

	public void setExcptValue(String excptValue) {
		this.excptValue = excptValue;
	}

	public SeTestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(SeTestCase testCase) {
		this.testCase = testCase;
	}
	
	

	public Long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Long waitTime) {
		this.waitTime = waitTime;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	
	public Integer getElementSeq() {
		return elementSeq;
	}

	public void setElementSeq(Integer elementSeq) {
		this.elementSeq = elementSeq;
	}

	public SeGetType getGetType() {
		return getType;
	}

	public void setGetType(SeGetType getType) {
		this.getType = getType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(SeTestStep o) {
		  if(this.sequence>o.sequence){
			  return 1;
		  }else{
			  return -1;
		  }
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	
	
	
	
}
