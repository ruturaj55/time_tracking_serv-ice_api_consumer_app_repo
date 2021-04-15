package com.timetrackingrecord.model;

public class Record {
	
	private String start;
	private String end;
	private String email;
	
	public Record(){
		
	}

	public Record(String start, String end, String email) {
		super();
		this.start = start;
		this.end = end;
		this.email = email;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Record [start=" + start + ", end=" + end + ", email=" + email + "]";
	}
	
	
}
