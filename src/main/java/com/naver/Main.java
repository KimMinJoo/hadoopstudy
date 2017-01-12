/*
 *@(#)Main.java 2017.01.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naver.singlefile.SingleFile;

/**
 *
 *
 * @author kim.minjoo
 */
public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		if (args.length == 0 ) {
			LOGGER.warn("Please input the arguments");
			return;
		}

		int select = Integer.parseInt(args[0]);

		if (select == 0) {
			executeWriter(args);
			return;
		} else if (select == 1) {
			executeReader(args);
			return;
		}

		System.out.println("Please input the first arguments 0 or 1");
	}

	private static void executeReader(String[] args) {
		if (args.length != 2) {
			LOGGER.warn("Please input 2 arguments");
		}
		SingleFile singleFile = new SingleFile();
		String path = args[1];
		String content = singleFile.readHdfs(path);
		System.out.println(content);
	}

	private static void executeWriter(String[] args) {
		if (args.length != 3) {
			LOGGER.warn("Please input 3 arguments");
		}

		SingleFile singleFile = new SingleFile();
		singleFile.setContent(args[2]);
		String path = args[1];
		singleFile.wirteHdfs(path);
	}
}