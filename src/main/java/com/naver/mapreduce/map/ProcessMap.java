package com.naver.mapreduce.map;

import java.util.Map;

import org.apache.hadoop.io.Text;

/**
 * Created by AD on 2017-02-13.
 */
@FunctionalInterface
public interface ProcessMap {
	public Map<String, Object> runMap(Text value);
}
