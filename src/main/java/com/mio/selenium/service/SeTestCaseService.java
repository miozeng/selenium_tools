package com.mio.selenium.service;

import java.util.List;

import com.mio.selenium.domain.SeTestCase;

public interface SeTestCaseService {

   public SeTestCase save(SeTestCase testCase);
	
	public SeTestCase findById(Long id);
	
	public List<SeTestCase> findAllByModul(Long modulId);
}
