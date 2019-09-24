package com.test.devm.stockalarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.devm.stockalarm.model.Alarm;
import com.test.devm.stockalarm.repositories.AlarmRepository;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmRepository repository;
	
	@Override
	public Alarm save(Alarm newAlarm) {
		return repository.save(newAlarm);
	}

	@Override
	public void update(Alarm alarm) {
		Optional<Alarm> oldAlarm = repository.findById(alarm.getId());
		if (oldAlarm == null) {
			return;
		}
		repository.save(alarm);
	}

	@Override
	public void delete(Long alarmId) {
		repository.deleteById(alarmId);
	}

	@Override
	public List<Alarm> getAlarms() {
		return (List<Alarm>) repository.findAll();
	}

	@Override
	public Optional<Alarm> getAlarm(Long alarmId) {
		return repository.findById(alarmId);
	}

	@Override
	public List<Alarm> getAlarmForStock(String stock) {
		return repository.findAlarmsForStock(stock);
	}

	@Override
	public List<Alarm> getActiveAlarms() {
		return repository.findActiveAlarms();
	}

	
}
