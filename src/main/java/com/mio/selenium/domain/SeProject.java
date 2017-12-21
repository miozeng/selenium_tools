package com.mio.selenium.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.jsonview.GenericJsonView;


@Entity
@Table(name = "se_project")
public class SeProject  extends AbstractTimestampEntity{


	@Id
    @GeneratedValue
    @Column(name="project_id")
	@JsonView(GenericJsonView.Summary.class)
	private Long projectId;
	
	@Column(name="project_name",nullable = false, length=50)
	@JsonView(GenericJsonView.Summary.class)
	private String projectName;
	
	@Column(name="project_desc",length=3000)
	@JsonView(GenericJsonView.Summary.class)
	private String projectDesc;
	
	@Column(name="project_show")
	@JsonView(GenericJsonView.Summary.class)
	private Boolean projectShow;
	
	@OneToMany(mappedBy = "project", fetch=FetchType.LAZY)
	private List<SeModul> moduls ;



	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public List<SeModul> getModuls() {
		return moduls;
	}

	public void setModuls(List<SeModul> moduls) {
		this.moduls = moduls;
	}

	public Boolean getProjectShow() {
		return projectShow;
	}

	public void setProjectShow(Boolean projectShow) {
		this.projectShow = projectShow;
	}


	
	
}
