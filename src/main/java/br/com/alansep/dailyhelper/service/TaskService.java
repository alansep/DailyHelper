package br.com.alansep.dailyhelper.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alansep.dailyhelper.model.Task;

public class TaskService {
	
	private static List<Task> tasks = new ArrayList<Task>();
	
	public List<Task> getTasks(){
		return Collections.unmodifiableList(tasks);
	}

	public void saveTask(Task task) {
		tasks.add(task);
	}
	
}