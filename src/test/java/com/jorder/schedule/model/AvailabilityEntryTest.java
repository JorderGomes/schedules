package com.jorder.schedule.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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

}
