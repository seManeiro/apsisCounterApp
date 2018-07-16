package com.apsis.counter.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CounterServiceImp implements CounterService {

	private Map<String, Counter> counterList = new HashMap<>();

	@Override
	public Counter createCounter(Counter counter) {
		increaseList(counter);
		return counter;
	}

	@Override
	public Counter getCurrentCounterNumber(String counterName) {
		return counterList.get(counterName);
	}

	@Override
	public Counter incrementCounterNumber(String counterName) {
		if (counterList.containsKey(counterName)) {
			updateCounter(counterName);
			return counterList.get(counterName);
		}
		return null;
	}

	private void updateCounter(String counterName) {
		Counter c = counterList.get(counterName);
		c.setCounterNumber(c.getCounterNumber() + 1);
		counterList.put(c.getCounterName(), c);

	}

	@Override
	public Map<String, Counter> getCounterMap() {
		return counterList;
	}

	public void setCounterList(Map<String, Counter> counterList) {
		this.counterList = counterList;
	}

	private void increaseList(Counter counter) {
		counterList.put(counter.getCounterName(), counter);
	}

	public boolean validateCounterName(Counter counter) {
		// validate that the new counter name is unique
		return counterList.containsKey(counter.getCounterName());
		
	}

}
