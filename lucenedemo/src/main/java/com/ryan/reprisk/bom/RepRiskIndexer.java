package com.ryan.reprisk.bom;

import java.io.IOException;

import com.ryan.reprisk.Indexer;
import com.ryan.reprisk.TextFileFilter;
import com.ryan.reprisk.constants.AppConstants;

public class RepRiskIndexer {

	public static void createIndex() {
		createIndex(AppConstants.INDEX_DIR, AppConstants.DATA_DIR);
	}
	
	public static void createIndex(String index_dir, String data_dir) {
		try (Indexer indexer = new Indexer(index_dir);) {
			int numIndexed;
			long startTime = System.currentTimeMillis();	
			numIndexed = indexer.createIndex(data_dir, new TextFileFilter());
			long endTime = System.currentTimeMillis();
			
			System.out.println(numIndexed+" File indexed, time taken: " +(endTime-startTime)+" ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
