package com.amat.services;
import org.springframework.cache.annotation.Cacheable;

import com.amat.model.Candidate;


public interface CandidateService
{
  public Candidate save(Candidate candidate);
}