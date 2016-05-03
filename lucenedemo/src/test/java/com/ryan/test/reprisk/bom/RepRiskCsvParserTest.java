package com.ryan.test.reprisk.bom;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ryan.reprisk.bom.RepRiskCsvParser;

public class RepRiskCsvParserTest {
	private RepRiskCsvParser parser;
	private String filename;
	
	@Before
	public void setData() {
		parser = new RepRiskCsvParser();
		filename = "C:\\Lucene\\RepRisk\\company_list.csv";
	}
	
	@Test
	public void testReadFileByStringSuccess() {
		assertTrue(parser.readCsvFile(filename) != null);
	}
	
	@Test
	public void testReadFileByFileSuccess() {
		assertTrue(parser.readCsvFile(new File(filename)) != null);
	}
	
	@Test
	public void testReadFailed() {
		assertFalse(parser.readCsvFile("") != null);
	}
	
	@After
	public void destroyData() {
		parser = null;
	}
}
