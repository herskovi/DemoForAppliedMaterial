package com.amat.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amat.model.Candidate;
import com.amat.services.CandidateRepository;
import com.amat.services.CandidateServiceImpl;

public class CandidateServiceTest {
	
	@Mock
	private CandidateRepository candidateRepository;
	
	@InjectMocks
	private CandidateServiceImpl candidateService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllCandidates(){
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		Candidate can = new Candidate("herskovi77@gmail.com","Moshe Herskovits","+972524265342","Amdocs", "Purchase UVision7", new Date());
		Candidate can2 = new Candidate("herskovi@zahav.net.il","Michal Goldenberg ","+9724444444444","Applied Material", "Purchase Reflexion", new Date());
		Candidate can3 =new Candidate("hsagm@yahoo.com","Alex Rotem","+9725555555555","Intel", "Information", new Date());
		candidateList.add(can);
		candidateList.add(can2);
		candidateList.add(can3);
		when(candidateRepository.findAll()).thenReturn(candidateList);
		
		List<Candidate> result = candidateService.findAll();
		assertEquals(3, result.size());
	}
	

	@Test
	public void saveCandidate()
	{
		Candidate can = new Candidate("herskovi77@gmail.com","Moshe Herskovits","+972524265342","Amdocs", "Purchase UVision7", new Date());
		when(candidateRepository.save(can)).thenReturn(can);
		Candidate result = candidateService.save(can);

		assertEquals("herskovi77@gmail.com", result.getEmail());
		assertEquals("Moshe Herskovits", result.getFullName());
	}
	
	

}
