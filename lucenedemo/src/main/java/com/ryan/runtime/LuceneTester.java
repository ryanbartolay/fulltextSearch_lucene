package com.ryan.runtime;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.ryan.reprisk.bom.Indexer;
import com.ryan.reprisk.bom.Searcher;
import com.ryan.reprisk.bom.TextFileFilter;
import com.ryan.reprisk.constants.AppConstants;

public class LuceneTester {

	String indexDir = "C:\\Lucene\\RepRisk\\Index";
	String dataDir = "C:\\Lucene\\RepRisk\\Data";
	Indexer indexer;
	Searcher searcher;

	public static void main(String[] args) throws Exception {
		LuceneTester tester = null;
		try {
			tester = new LuceneTester();
			//tester.createIndex();
			tester.search("ABB Ltd (ABB Asea Brown Boveri Ltd)");

			//tester.sortUsingRelevance("ryan");
			//tester.sortUsingIndex("record3.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void createIndex() throws IOException{
		indexer = new Indexer(indexDir);
		int numIndexed;
		long startTime = System.currentTimeMillis();	
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed+" File indexed, time taken: "
				+(endTime-startTime)+" ms");		
	}

	private void search(String searchQuery) throws IOException, ParseException{
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits +
				" documents found. Time :" + (endTime - startTime));
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.println("File: "
					+ doc.get(AppConstants.FILE_PATH));
		}
		searcher.close();
	}
	
	public String getIndexDir() {
		return indexDir;
	}

	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public Indexer getIndexer() {
		return indexer;
	}

	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}

	public Searcher getSearcher() {
		return searcher;
	}

	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}
}