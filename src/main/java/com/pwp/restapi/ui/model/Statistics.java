package com.pwp.restapi.ui.model;

public class Statistics {

	private String stringCount;
	private String minimum;
	private String maximum;
	private String average;
	private String median;
	
	public String getStringCount() {
		return stringCount;
	}
	public void setStringCount(String stringCount) {
		this.stringCount = stringCount;
	}
	
	public String getMinimum() {
		return minimum;
	}
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}
	
	public String getMaximum() {
		return maximum;
	}
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	
	public String getAverage() {
		return average;
	}
	public void setAverage(String average) {
		this.average = average;
	}
	
	public String getMedian() {
		return median;
	}
	public void setMedian(String median) {
		this.median = median;
	}	
}