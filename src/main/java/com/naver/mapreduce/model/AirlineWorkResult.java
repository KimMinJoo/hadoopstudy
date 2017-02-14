/*
 *@(#)AirlineWorkResult.java 2017.02.14
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.mapreduce.model;

import org.apache.hadoop.io.Text;

import com.naver.mapreduce.counter.DelayCounters;

/**
 *
 *
 * @author kim.minjoo
 */
public class AirlineWorkResult {
	private DelayCounters delayCounters;
	private String key;
	private boolean writable = false;

	public DelayCounters getDelayCounters() {
		return delayCounters;
	}

	public String getKey() {
		return key;
	}

	public boolean isWritable() {
		return writable;
	}

	public void setDelayCounters(DelayCounters delayCounters) {
		this.delayCounters = delayCounters;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}
}