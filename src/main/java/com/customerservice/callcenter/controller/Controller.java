package com.customerservice.callcenter.controller;

import com.customerservice.callcenter.response.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerservice.callcenter.service.ExecuteService;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
	
	/*@Autowired
	private ExecuteService executeService;*/
	
	@RequestMapping(value = "/v2/callcenter", method = RequestMethod.GET)
	public ResponseEntity<Performance> callcenter(){
		ExecuteService executeService = new ExecuteService();
		Performance performance = executeService.processCall();
		return ResponseEntity.ok().body(performance);
		
	}

}
