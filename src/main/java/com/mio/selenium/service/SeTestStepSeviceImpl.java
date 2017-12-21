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
		return seTestStepDao.findByProperty("testCase.caseId", caseId,"sequence" ,true);
	}

	@Override
	public SeTestStep saveOrUpdate(SeTestStep step) {
		if(step.getStepId()==null){
			step.setCreateDate(new Date());
			step.setLastModifiedDate(new Date());
			seTestStepDao.saveOrUpdate(step);
		}else{
			SeTestStep s = seTestStepDao.findById(step.getStepId());
			s.setDoType(step.getDoType());
			s.setElementName(step.getElementName());
			s.setElementValue(step.getElementValue());
			s.setExcptValue(step.getExcptValue());
			s.setGetType(step.getGetType());
			s.setLastModifiedDate(new Date());
			s.setSequence(step.getSequence());
			s.setStepDesc(step.getStepDesc());
			s.setStepName(step.getStepName());
			s.setStepNo(step.getStepNo());
			s.setTakePhoto(step.getTakePhoto());
			s.setUrl(step.getUrl());
			s.setWaitTime(step.getWaitTime());
			s.setElementSeq(step.getElementSeq());
			seTestStepDao.saveOrUpdate(s);
		}
	
		
		return step;
	}

	@Override
	public SeTestStep findById(Long stepId) {
		
		return seTestStepDao.findById(stepId);
	}

	@Override
	public void delete(Long stepId) {
		SeTestStep t = seTestStepDao.findById(stepId);
		if(t != null){
			seTestStepDao.delete(t);
		}
	
		
	}

	@Override
	public Integer getMaxSeq(Long caseId) {
		return (seTestStepDao.getMaxSeq(caseId)+1);
	}

}
