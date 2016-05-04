package com.ryan.reprisk.bom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.ryan.reprisk.Searcher;
import com.ryan.reprisk.constants.AppConstants;

public class RepRiskSearcher {
	
	public List<Document> relevantSearch(String query) {
		return search(query);
	}
	
	// in lucene well do a double quote wildcard for absolute value
	public List<Document> absoluteSearch(String query) {
		return search("\"" + query + "\"");
	}
	
	private List<Document> search(String query) {
		List<Document> documents = new ArrayList<>();
		try (Searcher searcher = new Searcher(AppConstants.INDEX_DIR);) {
			
			long startTime = System.currentTimeMillis();
			TopDocs hits = searcher.search(query);
			long endTime = System.currentTimeMillis();

			System.out.println("Query : " +query+ ". Total hits " + hits.totalHits + " documents found. Time :" + (endTime - startTime));
			
			for(ScoreDoc scoreDoc : hits.scoreDocs) {
				Document document = searcher.getDocument(scoreDoc);
				if(AppConstants.isDebbugingMode) {
					System.out.println("File: " + document.get(AppConstants.FILE_PATH));
				}
				documents.add(document);
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}
}
