package com.mio.selenium.service;


import java.util.List;
import java.util.Map;

import com.mio.selenium.domain.SeProject;

public interface SeProjectService {
	
	public SeProject save(SeProject project);
	
	public SeProject findById(Long id);
	
	public List<SeProject> findAll();
	
	public List<SeProject> findAllShow();
	
	public Map<String,Object> runProjectTest(Long id, boolean iscreate );
	
	void delete(Long modulId);

}
