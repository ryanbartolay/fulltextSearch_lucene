package com.ryan.runtime;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ryan.reprisk.bom.PrecisionRecallCalculator;
import com.ryan.reprisk.bom.RepRiskCsvParser;
import com.ryan.reprisk.bom.RepRiskIndexer;
import com.ryan.reprisk.bom.RepRiskSearcher;
import com.ryan.reprisk.constants.AppConstants;
import com.ryan.reprisk.model.Company;
import com.ryan.reprisk.model.PrecisionRecall;
import com.ryan.reprisk.model.SearchResults;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// 1st create index for all xml files, set the index dir and data dir (xml location)
		//RepRiskIndexer.createIndex();
		RepRiskIndexer.createIndex(AppConstants.INDEX_DIR, AppConstants.DATA_DIR);
		long totalIndexed = RepRiskIndexer.getTotalIndexed();
		
		// 2nd lets get the contents from csv (RepRisk Companies)
		RepRiskCsvParser csvParser = new RepRiskCsvParser();
		csvParser.setDELIMITER(';');
		List<Company> companies = csvParser.readCsvFile("C:\\Lucene\\RepRisk\\company_list.csv");
		System.out.println(companies);
		
		// 3rd lets get relevant results and documents for each of the companies
		Map<Company, SearchResults> relevantDocs = new HashMap<>();
		RepRiskSearcher searcher = new RepRiskSearcher();
		
		for(Company company : companies) {
			relevantDocs.put(company, 
					new SearchResults(
							searcher.absoluteSearch(company.getName()), 
							searcher.relevantSearch(company.getName())));
		}
		
		// 4th initialize Precision and Recall
		PrecisionRecallCalculator calc = new PrecisionRecallCalculator(totalIndexed);
		for(Map.Entry<Company, SearchResults> doc : relevantDocs.entrySet()) {
			Company company = doc.getKey();
			PrecisionRecall result = calc.calculate(doc.getValue());
			
			System.out.println(company.getName() + " " + result);
		}
		
		// 5th print results
		calc.calculateAveragePrecision();
		calc.calculate11point();
		calc.printAllResults();
	}
}
