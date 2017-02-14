package com.naver.mapreduce.map;

import java.util.Map;

import org.apache.hadoop.io.Text;

import com.naver.mapreduce.model.AirlineWorkResult;

/**
 * Created by AD on 2017-02-13.
 */
@FunctionalInterface
public interface ProcessMap {
	public AirlineWorkResult runMap(Text value);
}
