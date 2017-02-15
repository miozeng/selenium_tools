package com.mio.selenium.service;

import java.util.List;
import java.util.Map;

import com.mio.selenium.domain.SeModul;

public interface SeModulService {
	
	List<SeModul> findList(Long projectId);
	
	SeModul saveOrUpdate(SeModul modul);
	
	SeModul findById(Long modulId);

	public Map<String,Object> runModulTest(Long id, boolean iscreate );
}
