/*
 *@(#)WordCountReducer.java 2017.02.07
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.mapreduce.reduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * WordCount Reduce작업을 하는 클래스
 *
 * @author kim.minjoo
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		//초기값
		int sum = 0;

		//iterable을 돌면서 합을 구한다.
		for (IntWritable value : values) {
			sum += value.get();
		}

		//구한 합을 셋팅
		result.set(sum);
		context.write(key, result);
	}
}