package com.ryan.reprisk.model;

import java.util.LinkedList;
import java.util.List;

public class SearchResults {
	public List<String> list;
	public int relevantResults;
	
	public SearchResults() {
		this.list = new LinkedList<String>();
		this.relevantResults = 0;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public int getRelevantResults() {
		return relevantResults;
	}

	public void setRelevantResults(int relevantResults) {
		this.relevantResults = relevantResults;
	}
}