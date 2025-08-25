package com.jorder.schedule.model;

import java.time.LocalTime;
import java.util.List;
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
	
	
	
	public AvailabilityEntry(UUID id, Weekday weekday, int startTimeHours, int startTimeMinutes, int endTimeHours, int endTimeMinutes) {
		super();
		this.id = id;
		this.weekday = weekday;
		Time startTime = new Time(startTimeHours, startTimeMinutes);
		Time endTime = new Time(endTimeHours, endTimeMinutes);
		this.range = new TimeRange(startTime, endTime);
	}



	public boolean isSlotAvailable(CalendarSlot slot) {
		int slotStartTime = (slot.getSlotStart().getHour() * 60) + slot.getSlotStart().getMinute();
		int slotEndTime = (slot.getSlotEnd().getHour() * 60) + slot.getSlotEnd().getMinute();
		
		int rangeStartTime = range.getStart().getTimeInMinutes();
		int rangeEndTime = range.getEnd().getTimeInMinutes();
		
		if ((slotStartTime >= rangeStartTime) && (slotEndTime <= rangeEndTime)) {
			return true;
		}
		
		return false;
	}
	
	public boolean isBetween(LocalTime targetTime, LocalTime startTime, LocalTime  endTime) {
		boolean isAfterOrEqualStart = targetTime.isAfter(startTime); 
        boolean isBeforeOrEqualEnd =  targetTime.isBefore(endTime); 

        
        return isAfterOrEqualStart && isBeforeOrEqualEnd;
	}
	
	public boolean isSlotAvailableWithEvents(CalendarSlot slot, List<CalendarEvent> events ) {
		
		if (!isSlotAvailable(slot)) {
			return false;
		}
		
		for (CalendarEvent event: events) {
			if (
					(isBetween(slot.getSlotStart().toLocalTime(), event.getEventStart().toLocalTime(), event.getEventEnd().toLocalTime()))
					||
					(isBetween(slot.getSlotEnd().toLocalTime(), event.getEventStart().toLocalTime(), event.getEventEnd().toLocalTime()))
					) {
				return false;
			}
		}
		
		return true;
	}
	
}










