package com.ryan.reprisk.model;

public class PrecisionRecall {

	private double precision;
	private double recall;

	public PrecisionRecall(double precision, double recall) {
		this.precision = precision;
		this.recall = recall;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public double getRecall() {
		return recall;
	}

	public void setRecall(double recall) {
		this.recall = recall;
	}

	@Override
	public String toString() {
		return "PrecisionRecall [precision=" + precision + ", recall=" + recall + "]";
	}

}
