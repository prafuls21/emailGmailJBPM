package com.codeusingjava.bootdrools.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeusingjava.bootdrools.model.DepositRequest;

@RestController
public class DepositRateController {

	@Autowired
	private KieContainer kieContainer;

	@RequestMapping(value = "/getInterestRate", method = RequestMethod.GET, produces = "application/json")
	public String getInterest(@RequestParam(required = true) String loanType,
			@RequestParam(required = true) Integer durationInYear, @RequestParam(required = true) Integer age) {
		KieSession kieSession = kieContainer.newKieSession();
		DepositRequest depositRequest = new DepositRequest(loanType, durationInYear, age);
		kieSession.insert(depositRequest);
		kieSession.fireAllRules();
		kieSession.dispose();
		return "The interest rate for this application is " + depositRequest.getInterestRate();
	}
}