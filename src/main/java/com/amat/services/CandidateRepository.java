package com.amat.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amat.model.Candidate;


public interface CandidateRepository extends JpaRepository<Candidate, Long> 
{
    public Candidate save(Candidate candidate);

}