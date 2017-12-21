package com.mio.selenium.service;

import java.util.List;

import com.mio.selenium.domain.SeTestStep;

public interface SeTestStepSevice {
	
	List<SeTestStep> findByTestCase(Long caseId);
	
	SeTestStep saveOrUpdate(SeTestStep step);
	
	SeTestStep findById(Long stepId);
	
	void delete(Long stepId);
	
	public Integer getMaxSeq( Long caseId);
}
