package com.amat.services;



import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amat.model.Candidate;

/**
 * Created by moshehe on 13.05.2017.
 */
@Service("candidateService")
@Transactional
public class CandidateServiceImpl implements CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  
  public Candidate save(Candidate candidate)
  {
	  Candidate can = candidateRepository.save(candidate);
	  return can;
  }
  

  public List<Candidate> findAll()
  {
	  List<Candidate> canList = candidateRepository.findAll();
	  return canList;
  }
  
}



