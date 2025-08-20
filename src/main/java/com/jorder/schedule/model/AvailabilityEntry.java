package com.jorder.schedule.model;

import java.util.UUID;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
public class AvailabilityEntry {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private Weekday weekday;
	
	@Embedded 
	@AttributeOverrides({ 
		@AttributeOverride(name = "start", column = @Column(name = "start_time_range")),
		@AttributeOverride(name = "end", column = @Column(name = "end_time_range"))
	})
	private TimeRange range;
	
	public boolean isSlotAvailable(CalendarSlot slot) {
		return false;
	}
	
}
