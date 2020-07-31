package br.com.alansep.dailyhelper.model;


import br.com.alansep.dailyhelper.model.enums.Period;
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
	private String title;
	private String description;
	private Period period;
	
	@Override
	public String toString() {
		if(id == null) {
			return title;
		} else {
			return (id + 1) + " - " + title;			
		}
	}
	
	

}
