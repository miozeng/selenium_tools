package com.mio.selenium.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mio.selenium.dao.SeTestStepDao;
import com.mio.selenium.domain.SeTestStep;

@Service
public class SeTestStepSeviceImpl implements SeTestStepSevice {
	
	@Autowired
	private SeTestStepDao seTestStepDao;

	@Override
	public List<SeTestStep> findByTestCase(Long caseId) {
		return seTestStepDao.findByProperty("testCase.caseId", caseId);
	}

	@Override
	public SeTestStep saveOrUpdate(SeTestStep step) {
		if(step.getStepId()==null){
			step.setCreateDate(new Date());
		}
		step.setLastModifiedDate(new Date());
		seTestStepDao.saveOrUpdate(step);
		return step;
	}

}
