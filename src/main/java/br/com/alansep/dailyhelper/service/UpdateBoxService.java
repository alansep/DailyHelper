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
			try {
				Thread.sleep(3000);
				if (!comboBox.isPopupVisible()) {
					comboBox.removeAllItems();
					taskService.getTasks().forEach(task -> comboBox.addItem(task));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
