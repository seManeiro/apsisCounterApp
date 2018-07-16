package com.apsis.counter.service;

import java.util.Map;

public interface CounterService {
	
	
	Counter createCounter(Counter counter);
	
	Counter getCurrentCounterNumber(String counterName);
	
	Counter incrementCounterNumber(String counterName);
	
	Map<String, Counter> getCounterMap();
	

}
