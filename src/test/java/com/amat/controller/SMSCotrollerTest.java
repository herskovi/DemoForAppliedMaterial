package com.amat.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.amat.consts.SMSConstants;
import com.amat.entity.CandidateMapper;
import com.amat.model.Candidate;

public class SMSCotrollerTest 
{
	
	
	@Test
	public void testToNumberStartsWithZero(){
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		CandidateMapper can = new CandidateMapper();
//			can.setEmail("herskovi77@gmail.com");
//			can.setFullName("Moshe Herskovits");
			can.setTelephone("0524265342");
//			can.setCompany("Amdocs");
//			can.setComments("Purchase UVision7");
//			can.setCreatedDate(new Date());

		NexmoSmsController controoler = new NexmoSmsController(can, SMSConstants.SMS_VENDOR_TEXT_MESSAGE_VALUE);
		controoler.getToNumber();
		assertEquals("972524265342", controoler.getToNumber());
	}
	
	@Test
	public void testToNumberIsNull()
	{
		List<Candidate> candidateList = new ArrayList<Candidate>();
		CandidateMapper can = new CandidateMapper();
		can.setTelephone(null);
		NexmoSmsController controler = new NexmoSmsController(can, SMSConstants.SMS_VENDOR_TEXT_MESSAGE_VALUE);
		controler.getToNumber();
		assertEquals(null, controler.getToNumber());
	}
	
	@Test
	public void testToNumberStartsWithPlusSign()
	{
		List<Candidate> candidateList = new ArrayList<Candidate>();
		CandidateMapper can = new CandidateMapper();
		can.setTelephone("+972524265342");
		NexmoSmsController controler = new NexmoSmsController(can, SMSConstants.SMS_VENDOR_TEXT_MESSAGE_VALUE);
		controler.getToNumber();
		assertEquals("+972524265342", controler.getToNumber());
	}

}
