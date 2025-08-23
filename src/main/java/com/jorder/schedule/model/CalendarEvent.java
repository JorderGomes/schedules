package com.jorder.schedule.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CalendarEvent {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private Weekday weekday;
	
	private LocalDateTime event_start;
	
	private LocalDateTime event_end;

	public CalendarEvent(UUID id, Weekday weekday, LocalDateTime event_start, LocalDateTime event_end) {
		super();
		this.id = id;
		this.weekday = weekday;
		this.event_start = event_start;
		this.event_end = event_end;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Weekday getWeekday() {
		return weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	public LocalDateTime getEventStart() {
		return event_start;
	}

	public void setEventStart(LocalDateTime event_start) {
		this.event_start = event_start;
	}

	public LocalDateTime getEventEnd() {
		return event_end;
	}

	public void setEventEnd(LocalDateTime event_end) {
		this.event_end = event_end;
	}
	
	
}
