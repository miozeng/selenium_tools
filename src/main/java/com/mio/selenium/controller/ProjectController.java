package com.mio.selenium.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.domain.SeProject;
import com.mio.selenium.jsonview.GenericJsonView;
import com.mio.selenium.service.SeProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/project")
@Api(value = "/project", description = "project API")
public class ProjectController {
	
	@Autowired
	private SeProjectService projectService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(notes = "find  Project .  ", httpMethod = "GET", value = "find all ")
	@JsonView(GenericJsonView.Summary.class)
	public List<SeProject> getList() {
		return projectService.findAll();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(notes = "save  Project .  ", httpMethod = "POST", value = "save ")
	public String postUser(@RequestBody SeProject project) {
		projectService.save(project);
		return "success";
	}

	@RequestMapping(value = "/runTest/{projectId}/{iscreate}", method = RequestMethod.GET)
	@ApiOperation(notes = "runTest ", httpMethod = "GET", value = "runTest")
	public Map<String,Object> runTest(@PathVariable Long projectId,@PathVariable boolean iscreate){
		return projectService.runProjectTest(projectId, iscreate);
	}
	
	
}
