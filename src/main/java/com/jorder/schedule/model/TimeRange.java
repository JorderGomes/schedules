package com.jorder.schedule.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TimeRange {
	
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "hours", column = @Column(name = "start_hours") ),
		@AttributeOverride(name = "minutes", column = @Column(name = "start_minutes"))
	})
	private Time start;
	
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "hours", column = @Column(name = "end_hours") ),
		@AttributeOverride(name = "minutes", column = @Column(name = "end_minutes"))
	})
	private Time end;
	
}
