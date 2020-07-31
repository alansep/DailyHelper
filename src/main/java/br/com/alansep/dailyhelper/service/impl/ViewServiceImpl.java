package br.com.alansep.dailyhelper.service.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JFileChooser;

import br.com.alansep.dailyhelper.model.Task;
import br.com.alansep.dailyhelper.service.ViewService;
import br.com.alansep.dailyhelper.view.Home;

public class ViewServiceImpl implements ViewService {

	private ReportService reportService;
	private TaskService taskService;
	private Task screenTask;

	public ViewServiceImpl() {
		reportService = new ReportService();
		taskService = new TaskService();
		screenTask = TaskService.getEmptyTask();
	}

	@Override
	public void renderTask(Home home) {
		home.getTaskTitleTextbox().setText(screenTask.getTitle());
		home.getTaskDescriptionTextArea().setText(screenTask.getDescription());
		home.getTaskComboBox().setSelectedItem(screenTask);
	}

	@Override
	public String generateReport(List<Task> tasks) {
		return reportService.generateReport(tasks);
	}

	@Override
	public Task saveUpdateTask(Task task) {
		if(taskService.isNewTask(task)) {
			return taskService.addTask(task);
		} else {
			return taskService.updateTask(task);
		}
	}

	@Override
	public void saveFile(String report) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(null);
		String fileName = fileChooser.getSelectedFile().getAbsoluteFile().getAbsolutePath();
		if (!fileName.contains(".txt")) {
			fileName = fileName + ".txt";
		}
		BufferedWriter arquivo;
		try {
			arquivo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
			arquivo.write(report);
			arquivo.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public Task extractTask(Home home) {
		String title = home.getTaskTitleTextbox().getText();
		String description = home.getTaskDescriptionTextArea().getText();
		return Task.builder().title(title).description(description).build();
	}

}
