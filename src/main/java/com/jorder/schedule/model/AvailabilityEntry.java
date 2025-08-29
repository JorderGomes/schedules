package com.jorder.schedule.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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


	public Weekday getWeekday() {
		return weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
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
			if (slot.getSlotStart().equals(event.getEventStartWithBuffer()) || slot.getSlotEnd().equals(event.getEventEndWithBuffer())) {
				return false;
			}
			if (
					(isBetween(slot.getSlotStart(), event.getEventStartWithBuffer() , event.getEventEndWithBuffer()))
					||
					(isBetween(slot.getSlotEnd(), event.getEventStartWithBuffer(), event.getEventEndWithBuffer()))
					) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<CalendarSlot> generateAvailableSlotsForDay (LocalDate day){
		List<CalendarSlot> availableSlots = new ArrayList<>();
		// Obter start_time do range
		LocalTime start_time = LocalTime.of(range.getStart().getHours(), range.getStart().getMinutes());
		// Enquanto start_time <= end_time
		while (start_time.isBefore(LocalTime.of(range.getEnd().getHours(), range.getEnd().getMinutes()))) {
			// Cria um slot com start_time e duração de 30min
			CalendarSlot slot = new CalendarSlot(null, day, start_time, 30);
			// Verifica se o slot está disponível
			if (isSlotAvailable(slot)) {
				// Adiciona slot em availableSlots
				availableSlots.add(slot);
			}
			// Atualiza o start_time adicionando 30 minutos
			start_time.plusMinutes((long) 30);
		}
		return availableSlots;
	}
	
}










