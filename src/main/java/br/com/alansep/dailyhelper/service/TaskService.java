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
		tasks.add(task);
		combo.removeAllItems();
		tasks.stream().forEach(savedTask -> combo.addItem(savedTask));
		combo.addItem(Task.builder().title("New Task").build());
	}
	
}
