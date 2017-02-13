/*
 *@(#)Main.java 2017.01.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.naver.mapreduce.map.DelayCountMapper;
import com.naver.mapreduce.reduce.DelayCountReducer;

/**
 *
 *
 * @author kim.minjoo
 */
public class DelayCount extends Configuration implements Tool {
	private Configuration configuration = new Configuration();

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new DelayCount(), args);
		System.out.println("MR-Job Result : " + res);
	}

	public int run(String[] args) throws Exception {
		Configuration configuration = getConf();
		String[] otherArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();

		if (args.length != 2) {
			System.err.println("Usage : DelayCount <input><output>");
			System.exit(2);
		}

		//잡 이름 설정
		Job job = new Job(configuration, "DelayCount");

		//입출력 데이터 경로 설정
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		//잡 클래스 설정
		job.setJarByClass(DelayCount.class);
		job.setMapperClass(DelayCountMapper.class);
		job.setReducerClass(DelayCountReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.waitForCompletion(true);
		return 0;
	}

	public void setConf(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConf() {
		return configuration;
	}
}