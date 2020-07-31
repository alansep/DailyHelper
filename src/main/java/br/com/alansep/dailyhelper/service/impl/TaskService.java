package br.com.alansep.dailyhelper.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alansep.dailyhelper.model.Task;

class TaskService {

	private static List<Task> tasks = new ArrayList<Task>();

	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	public boolean isNewTask(Task task) {
		return tasks.contains(task);
	}

	public Task updateTask(Task task) {
		Task oldTask = tasks.get(task.getId());
		oldTask.setTitle(task.getTitle());
		oldTask.setDescription(task.getDescription());
		return oldTask;
	}

	public Task addTask(Task task) {
		task.setId((byte) (tasks.size()));
		tasks.add(task);
		return task;
	}

	public static Task getEmptyTask() {
		return Task.builder().title("New Task").description("").build();
	}

}
