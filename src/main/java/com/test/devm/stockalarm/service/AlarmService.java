package com.test.devm.stockalarm.service;

import java.util.List;
import java.util.Optional;

import com.test.devm.stockalarm.model.Alarm;

public interface AlarmService {

	public Alarm save(Alarm newAlarm);
	
	public void update(Alarm alarm);
	
	public void delete(Long alarmId);
	
	public List<Alarm> getAlarms();
	
	public Optional<Alarm> getAlarm(Long alarmId);
	
	public List<Alarm> getAlarmForStock(String stock);
	
	public List<Alarm> getActiveAlarms();
	
}
