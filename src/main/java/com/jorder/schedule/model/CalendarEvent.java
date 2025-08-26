package com.jorder.schedule.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class CalendarEvent {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private Weekday weekday;
	
	private LocalDateTime event_start;
	
	private LocalDateTime event_end;
	
	@Embedded 
	@AttributeOverrides({ 
		@AttributeOverride(name = "before", column = @Column(name = "buffer_before")),
		@AttributeOverride(name = "after", column = @Column(name = "buffer_after"))
	})
	private Buffer buffer;

	public CalendarEvent(UUID id, Weekday weekday, LocalDateTime event_start, LocalDateTime event_end, Buffer buffer) {
		super();
		this.id = id;
		this.weekday = weekday;
		this.event_start = event_start;
		this.event_end = event_end;
		if (buffer == null) {
			this.buffer = new Buffer(0,0);
		} else {
			this.buffer = buffer;
		}
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

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	// event.getEventStart().toLocalTime().minusMinutes((long) event.getBuffer().getBefore())
	
	public LocalTime getEventStartWithBuffer() {
		return this.event_start.toLocalTime().minusMinutes((long) buffer.getBefore());
	}
	
	public LocalTime getEventEndWithBuffer() {
		return this.event_end.toLocalTime().plusMinutes((long) buffer.getAfter());
	}
}


















