package com.test.devm.stockalarm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.devm.stockalarm.model.Alarm;

public interface AlarmRepository extends CrudRepository<Alarm, Long> {
	
	@Query("select a from Alarm a where stock=?1")
	List<Alarm> findAlarmsForStock(String stock);
	
	@Query("select a from Alarm a where status=true")
	List<Alarm> findActiveAlarms();
	
	@Query("update Alarm a set status=false where id=?1")
	void updateStatus(Long id);
	

}
