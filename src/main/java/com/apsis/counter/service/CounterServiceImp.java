package com.apsis.counter.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CounterServiceImp implements CounterService {

	private ConcurrentHashMap<String, Counter> counterList = new ConcurrentHashMap<String, Counter>();

	@Override
	public synchronized Counter createCounter(Counter counter) {
		increaseList(counter);
		return counter;
	}

	@Override
	public synchronized Counter getCurrentCounterNumber(String counterName) {
		return (Counter) counterList.get(counterName);
	}

	@Override
	public synchronized Counter incrementCounterNumber(String counterName) {
		if (counterList.containsKey(counterName)) {
			updateCounter(counterName);
			return (Counter) counterList.get(counterName);
		}
		return null;
	}

	private synchronized void updateCounter(String counterName) {
		Counter c = (Counter) counterList.get(counterName);
		c.setCounterNumber(c.getCounterNumber() + 1);
		counterList.put(c.getCounterName(), c);

	}

	@Override
	public Map<String, Counter> getCounterMap() {
		return counterList;
	}

	public void setCounterList(ConcurrentHashMap<String, Counter> counterList) {
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
