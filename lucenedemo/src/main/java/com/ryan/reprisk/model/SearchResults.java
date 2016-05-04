package com.ryan.reprisk.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.document.Document;

public class SearchResults {
	public List<String> list;
	//public int relevantResults;
	
	// coming from RepRiskSearcher.absoluteSearch
	public List<Document> relevantResults; // true positives
	
	// coming from RepRiskSearcher.relevantSearch	
	public List<Document> relevantDocs; // false positives
	
	public SearchResults(List<Document> relevantResults, List<Document> relevantDocs) {
		this.list = new LinkedList<String>();
		this.relevantResults = relevantResults;
		this.relevantDocs = relevantDocs;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<Document> getRelevantResults() {
		return relevantResults;
	}

	public void setRelevantResults(List<Document> relevantResults) {
		this.relevantResults = relevantResults;
	}

	public List<Document> getRelevantDocs() {
		return relevantDocs;
	}

	public void setRelevantDocs(List<Document> relevantDocs) {
		this.relevantDocs = relevantDocs;
	}
}