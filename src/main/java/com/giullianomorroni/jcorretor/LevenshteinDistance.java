package com.giullianomorroni.jcorretor;

public class LevenshteinDistance implements Comparable<LevenshteinDistance>{

	private int distance;
	private String word;
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int compareTo(LevenshteinDistance ld) {
		return Integer.valueOf(this.distance).compareTo(Integer.valueOf(ld.getDistance()));
	}

}