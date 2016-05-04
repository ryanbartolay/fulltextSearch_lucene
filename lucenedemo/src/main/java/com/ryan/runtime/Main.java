package com.ryan.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.ryan.reprisk.bom.RepRiskCsvParser;
import com.ryan.reprisk.bom.RepRiskSearcher;
import com.ryan.reprisk.model.Company;

public class Main {
	public static void main(String[] args) {
		// 1st create index for all xml files, set the index dir and data dir (xml location)
		//RepRiskIndexer.createIndex();
		//RepRiskIndexer.createIndex(AppConstants.INDEX_DIR, AppConstants.DATA_DIR);
		
		// 2nd lets get the contents from csv (RepRisk Companies)
		RepRiskCsvParser csvParser = new RepRiskCsvParser();
		csvParser.setDELIMITER(';');
		List<Company> companies = csvParser.readCsvFile("C:\\Lucene\\RepRisk\\company_list.csv");
		System.out.println(companies);
		
		// 3rd lets get relevant documents for each of the companies
		Map<Company, List<Document>> relevantDocs = new HashMap<>();
		RepRiskSearcher searcher = new RepRiskSearcher();
		
		for(Company company : companies) {
			relevantDocs.put(company, searcher.relevantSearch(company.getName()));
		}
		System.out.println(relevantDocs);
		
	}
}
