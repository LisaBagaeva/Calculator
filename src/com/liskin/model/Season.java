package com.liskin.model;

public enum Season {

	SUMMER(91, "Summer") {
		@Override
		public String bar() {
			return "2";
		}

		public void foo() {

		}
	},
	FALL(92, "Fall"), WINTER(91, "Winter"), SPRING(92, "Spring");

	private Season(int numberOfDays, String label) {
		this.numberOfDays = numberOfDays;
		this.label = label;
	}

	private int numberOfDays;
	private String label;

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDatys) {
		this.numberOfDays = numberOfDatys;
	}

	public String bar() {
		return "1";
	}

}
