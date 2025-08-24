package com.jorder.schedule.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CalendarSlot {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private LocalDateTime start;
	
	private LocalDateTime end;
	
	private int durationMinutes;
	
	
	
	public CalendarSlot(UUID id, LocalDateTime start, int durationMinutes) {
		super();
		this.id = id;
		this.start = start;
		this.end = start.plusMinutes((long) durationMinutes);
		this.durationMinutes = durationMinutes;
	}

	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	
	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	
	
	
}
