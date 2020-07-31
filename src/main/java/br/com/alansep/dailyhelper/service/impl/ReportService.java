package br.com.alansep.dailyhelper.service.impl;

import java.util.List;

import br.com.alansep.dailyhelper.model.Task;

class ReportService {

	public String generateReport(List<Task> tasks) {
		StringBuilder sb = new StringBuilder();

		sb.append("TASKS");

		tasks.stream().forEach(savedTask -> {
			sb.append("\n");
			sb.append(savedTask.getId() + 1);
			sb.append(" - ").append(savedTask.getTitle());
			sb.append("\nDescription: ").append(savedTask.getDescription());
		});

		return sb.toString();
	}

}
