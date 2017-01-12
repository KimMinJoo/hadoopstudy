/*
 *@(#)SigleFile.java 2017.01.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.singlefile;


import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author kim.minjoo
 */
public class SingleFile {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingleFile.class);
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void wirteHdfs(String path) {
		if (StringUtils.isEmpty(path)) {
			LOGGER.warn("Path is EmptyString");
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
			Path hdfsPath = new Path(path);
			if (hdfs.exists(hdfsPath)) {
				hdfs.delete(hdfsPath, true);
			}

			FSDataOutputStream outputStream = hdfs.create(hdfsPath);

			outputStream.writeUTF(getContent());
			outputStream.close();

			System.out.println("Success file writing, file is " + path );
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String readHdfs(String path) {
		try {
			Configuration configuration = new Configuration();
			FileSystem hdfs = FileSystem.get(configuration);

			//경로확인
			Path hdfsPath = new Path(path);

			if (hdfs.exists(hdfsPath) == false) {
				LOGGER.warn("{} not exists", hdfsPath);
			}

			FSDataInputStream inputStream = hdfs.open(hdfsPath);
			String inputString = inputStream.readUTF();
			inputStream.close();
			return inputString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
}
