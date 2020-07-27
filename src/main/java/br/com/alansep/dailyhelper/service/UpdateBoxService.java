package br.com.alansep.dailyhelper.service;

import javax.swing.JComboBox;

import br.com.alansep.dailyhelper.model.Task;

public class UpdateBoxService implements Runnable {

	private JComboBox<Task> comboBox;
	private TaskService taskService;

	public UpdateBoxService(JComboBox<Task> comboBox) {
		this.comboBox = comboBox;
		this.taskService = new TaskService();
	}

	@Override
	public void run() {
		while (true) {
			
			if (true == false) {
				comboBox.removeAllItems();
				taskService.getTasks().forEach(task -> comboBox.addItem(task));
			}
		}
	}

}
