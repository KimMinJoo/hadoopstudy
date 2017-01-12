/*
 *@(#)SigleFile.java 2017.01.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.singlefile;


import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author kim.minjoo
 */
public class SigleFile {
	private static final Logger LOGGER = LoggerFactory.getLogger(SigleFile.class);
	private String filename;
	private String content;
	private String path;

	public String getFilename() {
		return filename;
	}

	public String getContent() {
		return content;
	}

	public String getPath() {
		return path;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void wirteHdfs() {
		if (StringUtils.isEmpty(filename)) {
			LOGGER.warn("Filename is EmptyString");
			return;
		}
		if (StringUtils.isEmpty(content)) {
			LOGGER.warn("Content is EmptyString");
			return;
		}

		try{
			//파일 시스템 제어 객체 생성
			Configuration configuration = new Configuration();
			FileSystem hdfs = FileSystem.get(configuration);

			//경로확인
			Path path = new Path(getPath());


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
