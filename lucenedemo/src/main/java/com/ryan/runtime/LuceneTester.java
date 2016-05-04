package com.ryan.runtime;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.ryan.reprisk.Indexer;
import com.ryan.reprisk.TextFileFilter;
import com.ryan.reprisk.bom.Searcher;
import com.ryan.reprisk.constants.AppConstants;

public class LuceneTester {
	Indexer indexer;
	Searcher searcher;

	public static void main(String[] args) throws Exception {
		LuceneTester tester = null;
		try {
			tester = new LuceneTester();
			//tester.createIndex();
			tester.search("\"Cable News Network, Inc\"");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void createIndex() throws IOException{
		indexer = new Indexer(AppConstants.INDEX_DIR);
		int numIndexed;
		long startTime = System.currentTimeMillis();	
		numIndexed = indexer.createIndex(AppConstants.DATA_DIR, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed+" File indexed, time taken: " +(endTime-startTime)+" ms");		
	}

	private void search(String searchQuery) throws IOException, ParseException{
		searcher = new Searcher(AppConstants.INDEX_DIR);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.println("File: " + doc.get(AppConstants.FILE_PATH));
		}
		searcher.close();
	}
}