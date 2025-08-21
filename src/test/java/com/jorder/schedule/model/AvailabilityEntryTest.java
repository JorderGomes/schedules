package com.jorder.schedule.model;

import static org.junit.jupiter.api.Assertions.*;

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
		fail("Not yet implemented");
	}

}
