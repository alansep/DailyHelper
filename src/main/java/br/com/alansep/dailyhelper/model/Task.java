package br.com.alansep.dailyhelper.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Task {
	
	private Byte id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String title;
	private String description;
	
	@Override
	public String toString() {
		if(id == null) {
			return title;
		} else {
			return (id + 1) + " - " + title;			
		}
	}
	
	

}
