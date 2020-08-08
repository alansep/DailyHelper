package br.com.alansep.dailyhelper.service.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;

import br.com.alansep.dailyhelper.model.Task;
import br.com.alansep.dailyhelper.view.Home;

public class ViewService {

	private ReportService reportService;
	private TaskService taskService;
	private Home home;

	public ViewService(Home home) {
		this.home = home;
		reportService = new ReportService();
		taskService = new TaskService();
	}

	public void saveTask(Task task) {
		boolean isNew = task.getId() == null ? true : false;
		Task emptyTask = TaskService.getEmptyTask();

		taskService.addTask(task);
		home.getTaskComboBox().removeAllItems();
		taskService.getTasks().forEach(savedTask -> home.getTaskComboBox().addItem(savedTask));

		home.getTaskComboBox().addItem(emptyTask);
		if (isNew) {
			home.getTaskComboBox().setSelectedItem(emptyTask);
		} else {
			home.getTaskComboBox().setSelectedItem(task);
		}
	}

	public void generateReport() {
		String report = reportService.generateReport(taskService.getTasks());
		
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

}
