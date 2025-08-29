package com.jorder.schedule.model;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CalendarSlot {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private LocalDate date;
	
	private LocalTime slotStart;
	
	private LocalTime slotEnd;
	
	private int durationMinutes;
	
	
	
	public CalendarSlot(UUID id, LocalDate date, LocalTime start, int durationMinutes) {
		super();
		this.id = id;
		this.date = date;
		this.slotStart = start;
		this.slotEnd = start.plusMinutes((long) durationMinutes);
		this.durationMinutes = durationMinutes;
	}

	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getSlotStart() {
		return slotStart;
	}

	public void setSlotStart(LocalTime slotStart) {
		this.slotStart = slotStart;
	}

	public LocalTime getSlotEnd() {
		return slotEnd;
	}

	public void setSlotEnd(LocalTime slotEnd) {
		this.slotEnd = slotEnd;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	
	
	
}
