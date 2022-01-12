package com.fastcampus.ch2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
public class MyDate {
	@Override
	public String toString() {
		return "MyDate [year=" + year + ", month=" + month + ", day=" + day + "]";
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	private int year;
	private int month;
	private int day;
}
