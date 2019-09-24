package com.test.devm.stockalarm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.devm.stockalarm.model.Alarm;
import com.test.devm.stockalarm.model.User;
import com.test.devm.stockalarm.service.AlarmService;
import com.test.devm.stockalarm.utils.AlphaVantageConnector;

@RestController
@RequestMapping("/alarms")
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	AlphaVantageConnector connector;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Alarm register(@RequestBody Alarm newAlarm) {
		double price = connector.getPrice(newAlarm.getStock());
		if(price != 0) {
			newAlarm.setPrice(price);
			return alarmService.save(newAlarm);
		}
		return null;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void update(@PathVariable("id") Long id, @RequestBody Alarm alarm) {
		alarm.setId(id);
		alarmService.update(alarm);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Alarm> getAlarm(@PathVariable("id") Long id) {
		System.out.println("alarm get method called");
		return alarmService.getAlarm(id);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Alarm> getAlarms() {
		return alarmService.getAlarms();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		alarmService.delete(id);
	}

}
