package com.mio.selenium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.domain.SeTestStep;
import com.mio.selenium.jsonview.GenericJsonView;
import com.mio.selenium.service.SeTestStepSevice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/step")
@Api(value = "/step", description = "step API")
public class TestStepController {
	@Autowired
	private SeTestStepSevice testStepSevice;
	
	@RequestMapping(value = "/list/{caseId}", method = RequestMethod.GET)
	@ApiOperation(notes = "find  TestStep .  ", httpMethod = "GET", value = "find all ")
	@JsonView(GenericJsonView.Summary.class)
	public List<SeTestStep> getList(@PathVariable Long caseId) {
		return testStepSevice.findByTestCase(caseId);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(notes = "save  TestStep .  ", httpMethod = "POST", value = "save ")
	public String postUser(@RequestBody SeTestStep step) {
		testStepSevice.saveOrUpdate(step);
		return "success";
	}
	
	
	@RequestMapping(value = "/findById/{stepId}", method = RequestMethod.GET)
	@ApiOperation(notes = "findById ", httpMethod = "GET", value = "findById")
	@JsonView(GenericJsonView.Summary.class)
	public SeTestStep findById(@PathVariable Long stepId){
		return testStepSevice.findById(stepId);
	}
	
	@RequestMapping(value = "/delete/{stepId}", method = RequestMethod.GET)
	@ApiOperation(notes = "delete ", httpMethod = "GET", value = "delete")
	public void delete(@PathVariable Long stepId){
		 testStepSevice.delete(stepId);
	}
	
	@RequestMapping(value = "/getMaxSeq/{caseId}", method = RequestMethod.GET)
	@ApiOperation(notes = "getMaxSeq ", httpMethod = "GET", value = "getMaxSeq")
	public Integer getMaxSeq(@PathVariable Long caseId){
		return testStepSevice.getMaxSeq(caseId);
	}
}
