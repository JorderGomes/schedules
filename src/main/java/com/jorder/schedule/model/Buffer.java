package com.jorder.schedule.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Buffer {
	
	private int before;
	private int after;
	
	public Buffer(int before, int after) {
		super();
		this.before = before;
		this.after = after;
	}

	public int getBefore() {
		return before;
	}

	public void setBefore(int before) {
		this.before = before;
	}

	public int getAfter() {
		return after;
	}

	public void setAfter(int after) {
		this.after = after;
	}
	
	
	
}
