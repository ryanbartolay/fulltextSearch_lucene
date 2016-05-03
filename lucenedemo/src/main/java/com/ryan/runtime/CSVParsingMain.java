package com.ryan.runtime;

import java.io.File;

import com.ryan.reprisk.bom.RepRiskCsvParser;

public class CSVParsingMain {
	public static void main(String[] args) {
		RepRiskCsvParser parser = new RepRiskCsvParser();
		parser.setDELIMITER(';');
		parser.readCsvFile(new File("C:\\Lucene\\RepRisk\\company_list.csv"));
	}
}
