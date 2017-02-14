package com.naver.mapreduce.map;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.hadoop.io.Text;

import com.naver.mapreduce.counter.DelayCounters;
import com.naver.mapreduce.model.AirlinePerformanceParser;
import com.naver.mapreduce.model.AirlineWorkResult;

/**
 * Created by AD on 2017-02-13.
 */
public enum WorkType {
	Departure("Departure", (Text value)->  {
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);
		AirlineWorkResult airlineWorkResult = new AirlineWorkResult();


		if (parser.isDepartureDelayAvailable() == false) {
			airlineWorkResult.setDelayCounters(DelayCounters.not_available_departure);
			return airlineWorkResult;
		}

		airlineWorkResult.setKey(parser.getYear() + " " + parser.getMonth());

		if (parser.getDepartureDelayTime() == 0) {
			airlineWorkResult.setDelayCounters(DelayCounters.scheduled_departure);
			return airlineWorkResult;
		}

		if (parser.getDepartureDelayTime() < 0) {
			airlineWorkResult.setDelayCounters(DelayCounters.early_departure);
			return airlineWorkResult;
		}

		airlineWorkResult.setWritable(true);
		return airlineWorkResult;
	}),
	Arrival("Arrival", (Text value) -> {
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);
		AirlineWorkResult airlineWorkResult = new AirlineWorkResult();

		if (parser.isArriveDelayAvailable() == false) {
			airlineWorkResult.setDelayCounters(DelayCounters.not_available_arrival);
			return airlineWorkResult;
		}

		airlineWorkResult.setKey(parser.getYear() + " " + parser.getMonth());

		if (parser.getArriveDelayTime() == 0) {
			airlineWorkResult.setDelayCounters(DelayCounters.scheduled_arrival);
			return airlineWorkResult;
		}

		if (parser.getArriveDelayTime() < 0) {
			airlineWorkResult.setDelayCounters(DelayCounters.early_arrival);
			return airlineWorkResult;
		}

		airlineWorkResult.setWritable(true);
		return airlineWorkResult;
	});

	String workType;
	ProcessMap processMap;

	WorkType(String workType, ProcessMap processMap) {
		this.workType = workType;
		this.processMap = processMap;
	}

	public String getWorkType() {
		return workType;
	}

	public ProcessMap getProcessMap() {
		return processMap;
	}

	public static WorkType getInstance(String findWorkType) {
		for (WorkType workType : values()) {
			if (workType.getWorkType().equals(findWorkType)) {
				return workType;
			}
		}

		return null;
	}

}
