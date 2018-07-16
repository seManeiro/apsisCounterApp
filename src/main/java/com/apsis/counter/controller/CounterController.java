package com.apsis.counter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apsis.counter.service.Counter;
import com.apsis.counter.service.CounterServiceImp;

@RestController
@RequestMapping("/apsis/api/counter")
public class CounterController {

	@Autowired
	private CounterServiceImp counterService;

	// this method will validate the new counter to be unique and if succeed
	// will create a new counter.
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Counter createNewCounter(@RequestBody Counter inputCounter, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!counterService.validateCounterName(inputCounter)) {
			counterService.createCounter(inputCounter);
		} else {
			response.getWriter().write("The counter name is an existing one, please choose a new name.");
			return null;
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return inputCounter;

	}

	// this method will increase the counterNumber for a existing counter
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	public @ResponseBody Counter increaseCounter(@RequestParam("counterName") String counterName, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (null != counterService.incrementCounterNumber(counterName)) {
			response.setStatus(HttpServletResponse.SC_OK);
			return counterService.getCounterMap().get(counterName);
		} else {
			response.getWriter().write("There is no found counter with this given name.");
			return null;
		}

	}

	// this method will return the given value from a existing counter.
	@RequestMapping(value = "/getCounterNumber", method = RequestMethod.POST)
	public @ResponseBody String getCounterValue(@RequestParam("counterName") String counterName, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setStatus(HttpServletResponse.SC_OK);
		if (null == counterService.getCurrentCounterNumber(counterName)) {
			response.getWriter().write("There is no found counter with this given name.");
			return null;
		} else {
			return Integer.toString(counterService.getCurrentCounterNumber(counterName).getCounterNumber());
		}

	}

	// this method will return a JSON string will all existing counters.
	@RequestMapping(value = "/getCounters", method = RequestMethod.GET)
	public @ResponseBody String getAllCounters(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject res = new JSONObject(counterService.getCounterMap());
		response.setStatus(HttpServletResponse.SC_OK);
		return res.toString();
	}

}
