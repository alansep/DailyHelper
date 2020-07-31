package br.com.alansep.dailyhelper.service;

import java.util.List;

import br.com.alansep.dailyhelper.model.Task;
import br.com.alansep.dailyhelper.view.Home;

public interface ViewService {
	
	public void renderTask(Home home);

	public String generateReport(List<Task> tasks);
	
	public Task saveUpdateTask(Task task);
	
	public void saveFile(String report);
	
	public Task extractTask(Home home);
	
}
