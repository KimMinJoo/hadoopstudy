/*
 *@(#)DelayCountMapper.java 2017.02.13
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.mapreduce.map;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.naver.DelayCount;

/**
 *
 *
 * @author kim.minjoo
 */
public class DelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private String workType;
	private final static IntWritable outputValue = new IntWritable(1);
	private Text outputKey = new Text();

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		workType = context.getConfiguration().get("workType");
	}

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		WorkType workTypeProcess = WorkType.getInstance(workType);

		Map<String, Object> map = workTypeProcess.getProcessMap().runMap(value);

		if (MapUtils.getInteger(map, "value") != null) {
			outputKey.set(MapUtils.getString(map, "key"));
			context.write(outputKey, outputValue);
		}
	}

}