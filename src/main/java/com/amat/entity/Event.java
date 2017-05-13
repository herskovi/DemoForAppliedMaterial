package com.amat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


//@Entity
public class Event 
{

    @Id
    @GeneratedValue
    private Long id;


    public Long getId() {
        return id;
    }

   

    @JsonIgnore
    public String event;
    public String timestamp;

    public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event(String event) {
        this.event = event;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }
    
    public Event(String event, String timestamp) {
        this.event = event;
        this.timestamp = timestamp;
    }

    Event() { // jpa only
    }
}