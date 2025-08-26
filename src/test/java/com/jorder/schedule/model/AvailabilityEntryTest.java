package com.jorder.schedule.model;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class AvailabilityEntryTest {

	@Test
	@DisplayName("Should return true when a slot is avaliable")
	void isSlotAvailableSuccess() {

		AvailabilityEntry availabilityEntry = new AvailabilityEntry(UUID.randomUUID(), Weekday.MONDAY, 15, 10, 15, 50);

		CalendarSlot slot = new CalendarSlot(UUID.randomUUID(), LocalDateTime.of(2025, 8, 18, 15, 15), 30);

		boolean isAvailable = availabilityEntry.isSlotAvailable(slot);

		assertTrue(isAvailable);
	}
	
	@Test
	@DisplayName("Should return false when a slot is avaliable")
	void isSlotAvailableFail() {

		AvailabilityEntry availabilityEntry = new AvailabilityEntry(UUID.randomUUID(), Weekday.MONDAY, 14, 10, 14, 50);

		CalendarSlot slot = new CalendarSlot(UUID.randomUUID(), LocalDateTime.of(2025, 8, 18, 15, 15), 30);

		boolean isAvailable = availabilityEntry.isSlotAvailable(slot);

		assertFalse(isAvailable);
	}
	
	@Test
	@DisplayName("Should return true when a slot is available and there are no conflicts with already scheduled events.")
	void  isSlotAvailableWithEvents() {
		AvailabilityEntry availabilityEntry = new AvailabilityEntry(UUID.randomUUID(), Weekday.MONDAY, 13, 0, 18, 0);

		CalendarSlot slot = new CalendarSlot(UUID.randomUUID(), LocalDateTime.of(2025, 8, 18, 13, 0), 60);
		
		CalendarEvent event1 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 14, 0), LocalDateTime.of(2025, 8, 18, 15, 0), null);
		CalendarEvent event2 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 17, 0), LocalDateTime.of(2025, 8, 18, 18, 0), null);
		
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		events.add(event1);
		events.add(event2);
		
		boolean result = availabilityEntry.isSlotAvailableWithEvents(slot, events);
		
		assertTrue(result);
	}
	
	@Test
	@DisplayName("Should return false when a slot completely overlaps an already scheduled event.")
	void isSlotAvailableWithEvents_WhenOverlapsEntirely() {
		AvailabilityEntry availabilityEntry = new AvailabilityEntry(UUID.randomUUID(), Weekday.MONDAY, 13, 0, 18, 0);

		CalendarSlot slot = new CalendarSlot(UUID.randomUUID(), LocalDateTime.of(2025, 8, 18, 14, 0), 60);
		
		CalendarEvent event1 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 14, 0), LocalDateTime.of(2025, 8, 18, 15, 0), null);
		CalendarEvent event2 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 17, 0), LocalDateTime.of(2025, 8, 18, 18, 0), null);
		
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		events.add(event1);
		events.add(event2);
		
		boolean result = availabilityEntry.isSlotAvailableWithEvents(slot, events);
		
		assertFalse(result);
	}
	
	@Test
	@DisplayName("Should return false when a slot starts during an already scheduled event.")
	void isSlotAvailableWithEvents_WhenStartsDuringEvent() {
		AvailabilityEntry availabilityEntry = new AvailabilityEntry(UUID.randomUUID(), Weekday.MONDAY, 13, 0, 18, 0);

		CalendarSlot slot = new CalendarSlot(UUID.randomUUID(), LocalDateTime.of(2025, 8, 18, 14, 30), 60);
		
		CalendarEvent event1 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 14, 0), LocalDateTime.of(2025, 8, 18, 15, 0), null);
		CalendarEvent event2 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 17, 0), LocalDateTime.of(2025, 8, 18, 18, 0), null);
		
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		events.add(event1);
		events.add(event2);
		
		boolean result = availabilityEntry.isSlotAvailableWithEvents(slot, events);
		
		assertFalse(result);
	}
	
	@Test
	@DisplayName("Should return false when a slot ends during an already scheduled event.")
	void isSlotAvailableWithEvents_WhenEndsDuringEvent() {
		AvailabilityEntry availabilityEntry = new AvailabilityEntry(UUID.randomUUID(), Weekday.MONDAY, 13, 0, 18, 0);

		CalendarSlot slot = new CalendarSlot(UUID.randomUUID(), LocalDateTime.of(2025, 8, 18, 16, 30), 60);
		
		CalendarEvent event1 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 14, 0), LocalDateTime.of(2025, 8, 18, 15, 0), null);
		CalendarEvent event2 = new CalendarEvent(UUID.randomUUID(), Weekday.MONDAY, LocalDateTime.of(2025, 8, 18, 17, 0), LocalDateTime.of(2025, 8, 18, 18, 0), null);
		
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		events.add(event1);
		events.add(event2);
		
		boolean result = availabilityEntry.isSlotAvailableWithEvents(slot, events);
		
		assertFalse(result);
	}

}
















