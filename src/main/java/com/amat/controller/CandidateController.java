package com.amat.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amat.consts.CandidateRestURIConstants;
import com.amat.consts.SMSConstants;
import com.amat.entity.CandidateMapper;
import com.amat.model.Candidate;
import com.amat.services.CandidateService;


/**
 * Handles requests for the Employee service.
 */

@Controller
public class CandidateController 
{
	@Autowired
	private CandidateService candidateService;
	
	private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);
	//Map to store employees, ideally we should use database
	Map<Integer, CandidateMapper> canData = new HashMap<Integer, CandidateMapper>();
	
	@RequestMapping(value = CandidateRestURIConstants.DUMMY_CANDIDATE, method = RequestMethod.GET)
	public @ResponseBody CandidateMapper getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		CandidateMapper can = new CandidateMapper();
		can.setId(9999);
		can.setFullName("Dummy");
		can.setCreatedDate(new Date());
		canData.put(9999, can);
		return can;
	}
	
	@RequestMapping(value = CandidateRestURIConstants.GET_CANDIDATE, method = RequestMethod.GET)
	public @ResponseBody CandidateMapper getCandidate(@PathVariable("id") int candidateId) {
		logger.info("Start getCandidate. ID="+candidateId);
		
		return canData.get(candidateId);
	}
	
	@RequestMapping(value = CandidateRestURIConstants.GET_ALL_CANDIDATE, method = RequestMethod.GET)
	public @ResponseBody List<CandidateMapper> getAllCandidates() 
	{
		logger.info("Start getAllCandidates.");
		List<CandidateMapper> candidates = new ArrayList<CandidateMapper>();
		Set<Integer> candidateIdKeys = canData.keySet();
		for(Integer i : candidateIdKeys){
			candidates.add(canData.get(i));
		}
		return candidates;
	}
	
	@RequestMapping(value = CandidateRestURIConstants.CREATE_CANDIDATE, method = RequestMethod.POST)
	public @ResponseBody CandidateMapper createCandidates(@RequestBody CandidateMapper can) {
		logger.info("Start createCandidates.");
		can.setCreatedDate(new Date());
		canData.put(can.getId(), can);
		Candidate saveCan = new Candidate();
		saveCan.setEmail(can.getEmail());
		saveCan.setFullName(can.getFullName());
		saveCan.setTelephone(can.getTelephone());
		saveCan.setCompany(can.getCompany());
		saveCan.setComments(can.getComments());
		saveCan.setCreationDate(can.getCreatedDate());
		Candidate candidate = candidateService.save(saveCan);
		
		try {
			//turnOnTheLight();
			//turnOnTheSound();
			//sendSMS(can);
		} catch (Exception e) {
			logger.error("TurnOnTheLight Failed!!!!!");
			
		}
		return can;
	}
	/**
	 * @param string 
	 * 
	 */
	private void sendSMS(CandidateMapper can) 
	{
		SMSController smsController = new NexmoSmsController(can, SMSConstants.SMS_VENDOR_TEXT_MESSAGE_VALUE);
		smsController.sendSMS();
		
	}

	private void turnOnTheLight() throws Exception {
		LightController lightController = new LightController(5);
		lightController.process();
	}
	
	private void turnOnTheSound() throws Exception {
		SoundController soundController = new SoundController(5);
		soundController.process();
	}
	
	
	

	@RequestMapping(value = CandidateRestURIConstants.DELETE_CANDIDATE, method = RequestMethod.PUT)
	public @ResponseBody CandidateMapper deleteCandidate(@PathVariable("id") int candidateId) {
		logger.info("Start deleteCandidate.");
		CandidateMapper emp = canData.get(candidateId);
		canData.remove(candidateId);
		return emp;
	}
	
	/**
	 * 
	 */
	
	

	
}