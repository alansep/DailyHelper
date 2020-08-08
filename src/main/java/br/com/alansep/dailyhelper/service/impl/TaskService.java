package br.com.alansep.dailyhelper.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.alansep.dailyhelper.model.Task;
import br.com.alansep.dailyhelper.model.enums.Period;

public class TaskService {

	private static List<Task> tasks = new ArrayList<Task>();

	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	public boolean taskExists(Task task) {
		return tasks.contains(task);
	}

	public Task addTask(Task task) {
		if (taskExists(task)) {
			Task oldTask = tasks.get(task.getId());
			oldTask.setTitle(task.getTitle());
			oldTask.setDescription(task.getDescription());
			oldTask.setPeriod(task.getPeriod());
			return oldTask;
		} else {
			task.setId((tasks.size()));
			tasks.add(task);
			return task;
		}
	}

	public static Task getEmptyTask() {
		return Task.builder().id(null).title("New Task").description("").period(Period.MORNING).build();
	}

	public static List<Period> getPeriods() {
		return Arrays.asList(Period.MORNING, Period.AFTERNOON);
	}

}
