package br.com.alansep.dailyhelper.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;

import br.com.alansep.dailyhelper.model.Task;

public class TaskService {
	
	private static List<Task> tasks = new ArrayList<Task>();
	
	public List<Task> getTasks(){
		return Collections.unmodifiableList(tasks);
	}

	public void saveTask(Task task, JComboBox<Task> combo) {	
		if(tasks.contains(task)) {;
			Task oldTask = tasks.get(task.getId());
			oldTask.setTitle(task.getTitle());
			oldTask.setDescription(task.getDescription());		
		} else {
			task.setId((byte) (tasks.size()));
			tasks.add(task);			
		}		
		combo.removeAllItems();
		tasks.stream().forEach(savedTask -> combo.addItem(savedTask));
		combo.addItem(Task.builder().title("New Task").build());
	}

	public String generateReport() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("TASKS");
		
		tasks.stream().forEach(savedTask -> {
			sb.append("\n");
			sb.append(savedTask.getId() + 1);
			sb.append(" - ").append(savedTask.getTitle());
			sb.append("\nDescription: ").append(savedTask.getDescription());
			sb.append("\n");
		});	
		
		return sb.toString();
	}
	
	public static Task getEmptyTask() {
		return Task.builder().title("New Task").description("").build();
	}
	
}
