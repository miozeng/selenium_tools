package com.mio.selenium.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.jsonview.GenericJsonView;

@MappedSuperclass
@Audited
public class AbstractTimestampEntity {



	@Column(name = "create_date", updatable = false, nullable = false, columnDefinition = "datetime")
	private Date createDate;

	@Column(name = "last_modified_date", updatable = true, columnDefinition = "datetime")
	@JsonView(GenericJsonView.Summary.class)
	private Date lastModifiedDate;


	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
