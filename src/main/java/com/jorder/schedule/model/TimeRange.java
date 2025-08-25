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
	
	

	public TimeRange(Time start, Time end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}
	
	
	
}
