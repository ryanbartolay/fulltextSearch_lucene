package com.ryan.runtime;

import com.ryan.reprisk.bom.RepRiskSearcher;

public class LuceneTester {

	public static void main(String[] args) throws Exception {
		RepRiskSearcher searcher = new RepRiskSearcher();
		//tester.createIndex();
		searcher.relevantSearch("Cable News Network, Inc");
		
		searcher.absoluteSearch("Cable News Network, Inc");
	}

}