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
import com.mio.selenium.domain.SeModul;
import com.mio.selenium.jsonview.GenericJsonView;
import com.mio.selenium.service.SeModulService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/modul")
@Api(value = "/modul", description = "modul API")
public class ModulCtroller {
	@Autowired
	private SeModulService modulService;
	
	@RequestMapping(value = "/list/{projectId}", method = RequestMethod.GET)
	@ApiOperation(notes = "find  Modul .  ", httpMethod = "GET", value = "find all ")
	@JsonView(GenericJsonView.Summary.class)
	public List<SeModul> getList(@PathVariable Long projectId) {
		return modulService.findList(projectId);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(notes = "save  Modul .  ", httpMethod = "POST", value = "save ")
	public String postUser(@RequestBody SeModul modul) {
		modulService.saveOrUpdate(modul);
		return "success";
	}
	
	@RequestMapping(value = "/findById/{modulId}", method = RequestMethod.GET)
	@ApiOperation(notes = "findById ", httpMethod = "GET", value = "runTest")
	@JsonView(GenericJsonView.Summary.class)
	public SeModul findById(@PathVariable Long modulId){
		return modulService.findById(modulId);
	}
	
	@RequestMapping(value = "/runTest/{modulId}/{iscreate}", method = RequestMethod.GET)
	@ApiOperation(notes = "runTest ", httpMethod = "GET", value = "runTest")
	public Map<String,Object> runTest(@PathVariable Long modulId,@PathVariable boolean iscreate){
		return modulService.runModulTest(modulId, iscreate);
	}
	
	@RequestMapping(value = "/delete/{modulId}", method = RequestMethod.GET)
	@ApiOperation(notes = "delete ", httpMethod = "GET", value = "delete")
	public void delete(@PathVariable Long modulId){
		modulService.delete(modulId);
	}
	
}
