package com.test.devm.stockalarm.utils;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.devm.stockalarm.model.Alarm;
import com.test.devm.stockalarm.repositories.AlarmRepository;

@Component
public class StockScheduler {
	List<Alarm> currentAlarms;
	
	@Autowired
	AlarmRepository repository;
	
	@Autowired
	AlphaVantageConnector connector;
	
	Map<String, Double> activeStocks = new Hashtable<String, Double>();
	
	@Scheduled(fixedDelay = 10000, initialDelay = 1000)
	public void checkAlarms() {
		activeStocks.clear();
		List<Alarm> alarms = repository.findActiveAlarms();
		System.out.println("Scheduler started and it found " + String.valueOf(alarms.size()));
		
		alarms.parallelStream().forEach(alarm -> processAlarm(alarm));
	}
	
	private void processAlarm(Alarm alarm) {
		
		double currentPrice;
		double originalPrice = alarm.getPrice();
		double variation = alarm.getVariation();
		double targetPrice = originalPrice + originalPrice * variation/100;
		
		String stock = alarm.getStock();
		
		if( activeStocks.get(stock) == null) {
			currentPrice = connector.getPrice(stock);
			activeStocks.put(stock, currentPrice);
		} else {
			currentPrice = activeStocks.get(stock);
		}

		if (( variation > 0 && ( currentPrice > ( targetPrice ))) || 
			( variation < 0 && ( currentPrice < ( targetPrice )))) {
			System.out.println("Process alarm: send alarm for stock " + alarm.getStock() + " new price " + String.valueOf(currentPrice));
			repository.updateStatus(alarm.getId());
		}
	}

}
