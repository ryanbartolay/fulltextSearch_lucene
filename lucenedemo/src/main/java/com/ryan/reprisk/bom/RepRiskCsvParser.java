package com.ryan.reprisk.bom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.ryan.reprisk.model.Company;

public class RepRiskCsvParser {
	private static final String [] FILE_HEADER_MAPPING = {"RepRisk Company ID","Company Name"};

	private static final String COMPANY_ID = "RepRisk Company ID";
	private static final String COMPANY_NAME = "Company Name";
	private static char DELIMITER = ',';

	public void setDELIMITER(char dELIMITER) {
		DELIMITER = dELIMITER;
	}

	public List<Company> readCsvFile(String fileName) {
		return readCsvFile(new File(fileName));
	}
	
	public List<Company> readCsvFile(File file) {

		//Create the CSVFormat object with the header mapping
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.newFormat(DELIMITER).withHeader(FILE_HEADER_MAPPING);
		List<Company> companies = null;
		try(FileReader fileReader = new FileReader(file);
				CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);) {
			
			//placeholder
			companies = new ArrayList<>();
			Company company = null;

			//Get a list of CSV file records
			List<CSVRecord> csvRecords = csvFileParser.getRecords(); 

			//Read the CSV file records starting from the second record to skip the header
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				//Create a new student object and fill his data
				company = new Company();
				company.setId(Long.valueOf(record.get(COMPANY_ID)));
				company.setName(record.get(COMPANY_NAME));
				companies.add(company);	
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		}
		return companies;
	}
}
