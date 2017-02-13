/*
 *@(#)ArrivalDealyCountMapper.java 2017.02.13
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.mapreduce.map;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.naver.mapreduce.model.AirlinePerformanceParser;

/**
 *
 *
 * @author kim.minjoo
 */
public class ArrivalDelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final static IntWritable outputValue = new IntWritable(1);

	private  Text outputKey = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);

		outputKey.set(parser.getYear() + " " + parser.getMonth());

		if (parser.getArriveDelayTime() > 0) {
			context.write(outputKey, outputValue);
		}
	}

}