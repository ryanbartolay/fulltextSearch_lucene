package com.ryan.reprisk.bom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.ryan.reprisk.model.PrecisionRecall;
import com.ryan.reprisk.model.SearchResults;

public class PrecisionRecallCalculator {

	public List<PrecisionRecall> allResults;
	public List<PrecisionRecall> steps11Results;
	public List<PrecisionRecall> avgPrecisionResults;

	public long relevantDocumentCount;
	private long relevantDocumentsFound;

	public double avgPrecision;

	String name;
	
	public static double TRUE_POSITIVE = 0; //quadrant 1
	public static double FALSE_POSITIVE = 0; // quadrant 2
	public static double FALSE_NEGATIVE = 0; // quadrant 3
	public static double TRUE_NEGATIVE = 0;// quadrant 4
	
	public PrecisionRecallCalculator(long relevantDocumentCount) {
		this.name = "test_name";
		this.relevantDocumentCount = relevantDocumentCount;
		this.allResults = new ArrayList<PrecisionRecall>();
		this.avgPrecisionResults = new ArrayList<PrecisionRecall>();
		this.relevantDocumentsFound = 0;
	}

	public PrecisionRecall calculate(SearchResults results) {

		double tp = results.getRelevantResults().size(); // true positives
		double fp = results.getRelevantDocs().size() - tp; // false positives
		double fn = relevantDocumentCount - results.getRelevantResults().size(); // false negatives

		double precision = tp / (tp + fp);
		double recall = tp / (tp + fn);

		PrecisionRecall precisionRecall = new PrecisionRecall(precision, recall);

		this.allResults.add(precisionRecall);

		// Save value if new relevant document was received
		if(tp > relevantDocumentsFound) {
			// Found a new relevant document!
			this.avgPrecisionResults.add(precisionRecall);
			relevantDocumentsFound++;
		}
		
		return precisionRecall;
	}

	public void calculateAveragePrecision() {
		System.out.println("\nCalculating average precision from " + this.avgPrecisionResults.size() + " results");

		double avgPrecision = 0;

		for(PrecisionRecall precisionRecall : this.avgPrecisionResults) {
			avgPrecision += precisionRecall.getPrecision();
		}

		avgPrecision /= (double) this.avgPrecisionResults.size();

		System.out.println("Average Precision: " + avgPrecision);

		this.avgPrecision = avgPrecision;
	}

	private void do11pointAverage(List<PrecisionRecall> results) {
		this.steps11Results = new ArrayList<PrecisionRecall>();

		double recallStep = 0;

		for (int i = 0; i < results.size(); i++) {
			PrecisionRecall precisionRecall = results.get(i);

			if (precisionRecall.getRecall() >= recallStep
					&& recallStep <= 1) {
				this.steps11Results.add(precisionRecall);
				recallStep += 0.1;
			}
		}
	}

	public void calculate11point() {

		// Interpolate
		/*
		 * Not needed, the assignment required non-interpolated
		 */
		// this.interpolate();

		// 11-point
		this.do11pointAverage(this.allResults);
	}

	public void printAllResults() throws IOException {
		// Over 2000 lines, better to write them to a file
		StringBuffer sb = new StringBuffer();

		sb.append("Recall, Precision\n");

		for (PrecisionRecall precisionRecall : this.allResults) {
			sb.append(precisionRecall.getRecall() + ", "
					+ precisionRecall.getPrecision() + "\n");
		}

		this.writeToFile(sb, this.name + "_all.txt");
	}

	public void print11pointAverage() {
		System.out.println("Recall, Precision");
		for (int step = 0; step < this.steps11Results.size(); step++) {
			PrecisionRecall stepResult = this.steps11Results.get(step);

			System.out.println(stepResult.getRecall() + ", " + stepResult.getPrecision());
		}
	}

	private void writeToFile(StringBuffer sb, String filename) throws IOException {

		Writer out = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");

		try {
			out.write(sb.toString());
		} finally {
			out.close();
		}
	}
}
