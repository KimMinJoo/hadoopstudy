/*
 *@(#)DelayCounters.java 2017.02.14
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.mapreduce.counter;

/**
 *
 *
 * @author kim.minjoo
 */
public enum DelayCounters {
	//출발 지연시간이 NA인 경우
	not_available_arrival,
	//정시에 출발한 경우
	scheduled_arrival,
	//예정보다 일찍 출발한 경우
	early_arrival,
	//도착 지연시간이 NA인 경우
	not_available_departure,
	//정시에 도착한 경우
	scheduled_departure,
	//예정보다 일찍 도착한 경우
	early_departure;
}