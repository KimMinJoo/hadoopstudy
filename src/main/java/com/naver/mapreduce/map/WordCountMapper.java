/*
 *@(#)WordCountMapper.java 2017.02.07
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.mapreduce.map;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * WordCount Map 클래스
 *
 * @author kim.minjoo
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	//각 단어마다 1개씩밖에 나올 수 없다. (예제에서의 조건인듯...?)
	private final static IntWritable INT_WRITABLE = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//value 는 line이고 각 line을 whitespace로 잘래낸다.(단어별로)
		StringTokenizer itr = new StringTokenizer(value.toString());

		//각 단어들을 돌면서 1개씩 체크를 해준다.
		while (itr.hasMoreTokens()) {
			word.set(itr.nextToken());
			context.write(word, INT_WRITABLE);
		}
	}
}