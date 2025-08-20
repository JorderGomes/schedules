package com.jorder.schedule.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CalendarSlot {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private LocalDateTime start;
	
	private int durationMinutes;
	
}
