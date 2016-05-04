package com.ryan.reprisk.bom;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.ryan.reprisk.Searcher;
import com.ryan.reprisk.constants.AppConstants;

public class RepRiskSearcher {

	public static void search(String query) {
		try (Searcher searcher = new Searcher(AppConstants.INDEX_DIR);) {
			
			long startTime = System.currentTimeMillis();
			TopDocs hits = searcher.search(query);
			long endTime = System.currentTimeMillis();

			System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
			for(ScoreDoc scoreDoc : hits.scoreDocs) {
				Document doc = searcher.getDocument(scoreDoc);
				System.out.println("File: " + doc.get(AppConstants.FILE_PATH));
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
