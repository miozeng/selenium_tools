package com.mio.selenium.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mio.selenium.dao.SeTestCaseDao;
import com.mio.selenium.dao.SeTestStepDao;
import com.mio.selenium.domain.SeTestCase;
import com.mio.selenium.domain.SeTestStep;

@Service
public class SeTestCaseServiceImpl implements SeTestCaseService {
	@Autowired 
	private SeTestCaseDao seTestCaseDao;
	
	
	@Autowired
	private SeTestStepDao seTestStepDao;
	
	@Override
	public SeTestCase save(SeTestCase tcase) {
		if(tcase.getCaseId()==null){
			tcase.setCreateDate(new Date());
		}
		tcase.setLastModifiedDate(new Date());
		seTestCaseDao.saveOrUpdate(tcase);
		
		if(tcase.getSteps() != null){
			for (SeTestStep step : tcase.getSteps()) {
				step.setTestCase(tcase);
				if(step.getStepId()==null){
					step.setCreateDate(new Date());
				}
				step.setLastModifiedDate(new Date());
				seTestStepDao.saveOrUpdate(step);
			}
		}
		return tcase;
	}

	@Override
	public SeTestCase findById(Long id) {
		
		return seTestCaseDao.findById(id);
	}

	@Override
	public List<SeTestCase> findAllByModul(Long modulId) {
		return seTestCaseDao.findByProperty("modul.modulId", modulId);
	}

}
