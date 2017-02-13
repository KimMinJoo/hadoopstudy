package com.naver.mapreduce.map;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.hadoop.io.Text;

import com.naver.mapreduce.model.AirlinePerformanceParser;

/**
 * Created by AD on 2017-02-13.
 */
public enum WorkType {
	Departure("Departure", (Text value)->  {
		Map<String, Object> map = new HashedMap();
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);

		map.put("key", parser.getYear() + " " + parser.getMonth());

		if (parser.getDepartureDelayTime() > 0) {
			map.put("value", parser.getDepartureDelayTime());
		}

		return map;
	}),
	Arrival("Arrival", (Text value) -> {
		Map<String, Object> map = new HashedMap();
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);

		map.put("key", parser.getYear() + " " + parser.getMonth());

		if (parser.getArriveDelayTime() > 0) {
			map.put("value", parser.getArriveDelayTime());
		}

		return map;
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
