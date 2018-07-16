package com.apsis.counter.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Counter {
	
	@JsonProperty
	private String counterName;
	
	@JsonProperty
	private int counterNumber;
	
	
	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public int getCounterNumber() {
		return counterNumber;
	}

	public void setCounterNumber(int counterNumber) {
		this.counterNumber = counterNumber;
	}

	
	
	

}
