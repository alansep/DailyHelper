package br.com.alansep.dailyhelper.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.alansep.dailyhelper.model.Task;
import br.com.alansep.dailyhelper.model.enums.Period;
import br.com.alansep.dailyhelper.service.impl.TaskService;
import br.com.alansep.dailyhelper.service.impl.ViewService;
import lombok.Getter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@Getter
public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField taskTitleTextbox;
	private JTextArea taskDescriptionTextArea;
	private JComboBox<Task> taskComboBox;
	private Task screenTask;
	private JButton createUpdateButton;
	private JComboBox<Period> taskPeriodCombobox;
	private ViewService viewService;

	private void initializeFields() {
		viewService = new ViewService(this);
		Task emptyTask = TaskService.getEmptyTask();
		getTaskComboBox().addItem(emptyTask);
		renderTask(emptyTask);
		TaskService.getPeriods().forEach(period -> getTaskPeriodCombobox().addItem(period));
	}

	private void renderTask(Task task) {
		getTaskComboBox().setSelectedItem(task);
		getTaskTitleTextbox().setText(task.getTitle());
		getTaskDescriptionTextArea().setText(task.getDescription());
		getTaskPeriodCombobox().setSelectedItem(task.getPeriod());
	}

	public Home() {

		setStyle();

		setTitle("Daily Helper");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 522);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 484, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel taskTitleLabel = new JLabel("Task Title");
		taskTitleLabel.setBounds(10, 75, 279, 14);
		panel.add(taskTitleLabel);
		springLayout.putConstraint(SpringLayout.WEST, taskTitleLabel, 448, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, taskTitleLabel, -433, SpringLayout.EAST, getContentPane());

		JLabel taskDescriptionLabel = new JLabel("Task Description");
		taskDescriptionLabel.setBounds(10, 149, 279, 14);
		panel.add(taskDescriptionLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, taskDescriptionLabel, -427, SpringLayout.SOUTH,
				getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, taskDescriptionLabel, 467, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, taskDescriptionLabel, -374, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, taskTitleLabel, -6, SpringLayout.NORTH, taskDescriptionLabel);
		taskComboBox = new JComboBox<Task>();
		taskComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					Task savedTask = (Task) e.getItem();
					renderTask(savedTask);
					if(savedTask.getId() == null) {
						createUpdateButton.setText("Add Task");
					} else {
						createUpdateButton.setText("Update Task");
					}
				}
			}
		});
		taskComboBox.setBounds(10, 30, 279, 22);
		panel.add(taskComboBox);
		springLayout.putConstraint(SpringLayout.WEST, taskComboBox, 340, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, taskComboBox, -323, SpringLayout.EAST, getContentPane());

		springLayout.putConstraint(SpringLayout.NORTH, taskComboBox, 18, SpringLayout.SOUTH, taskDescriptionLabel);

		JLabel taskComboBoxLabel = new JLabel("Select a created task:");
		taskComboBoxLabel.setBounds(10, 11, 279, 14);
		panel.add(taskComboBoxLabel);
		springLayout.putConstraint(SpringLayout.WEST, taskComboBoxLabel, 295, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, taskComboBoxLabel, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, taskComboBoxLabel, -225, SpringLayout.SOUTH, getContentPane());

		taskTitleTextbox = new JTextField();
		taskTitleTextbox.setBounds(10, 97, 279, 20);
		panel.add(taskTitleTextbox);
		springLayout.putConstraint(SpringLayout.WEST, taskTitleTextbox, 360, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, taskTitleTextbox, -303, SpringLayout.EAST, getContentPane());
		taskTitleTextbox.setColumns(10);

		taskDescriptionTextArea = new JTextArea();
		taskDescriptionTextArea.setLineWrap(true);
		taskDescriptionTextArea.setBounds(10, 174, 279, 192);
		panel.add(taskDescriptionTextArea);
		springLayout.putConstraint(SpringLayout.WEST, taskDescriptionTextArea, 372, SpringLayout.WEST,
				getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, taskDescriptionTextArea, -291, SpringLayout.EAST,
				getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, taskDescriptionTextArea, 157, SpringLayout.NORTH,
				getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, taskDescriptionTextArea, 402, SpringLayout.NORTH,
				getContentPane());

		JSeparator separator = new JSeparator();
		separator.setBounds(-10, 430, 315, 2);
		panel.add(separator);
		springLayout.putConstraint(SpringLayout.WEST, separator, 398, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator, -245, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, taskTitleTextbox, -138, SpringLayout.NORTH, separator);
		springLayout.putConstraint(SpringLayout.NORTH, separator, 5, SpringLayout.SOUTH, taskDescriptionTextArea);

		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewService.generateReport();
			}
		});
		generateReportButton.setBounds(155, 443, 134, 23);
		panel.add(generateReportButton);
		springLayout.putConstraint(SpringLayout.WEST, generateReportButton, 462, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, generateReportButton, -339, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, generateReportButton, 21, SpringLayout.SOUTH, separator);

		createUpdateButton = new JButton("Add Task");
		createUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewService.saveTask(extractTask());
			}
		});
		createUpdateButton.setBounds(10, 443, 134, 23);
		panel.add(createUpdateButton);
		springLayout.putConstraint(SpringLayout.NORTH, createUpdateButton, 471, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, createUpdateButton, 353, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, createUpdateButton, 479, SpringLayout.WEST, getContentPane());

		JLabel taskPeriodLabel = new JLabel("Period");
		taskPeriodLabel.setBounds(10, 377, 46, 14);
		panel.add(taskPeriodLabel);

		taskPeriodCombobox = new JComboBox<Period>();
		taskPeriodCombobox.setBounds(10, 397, 279, 22);
		panel.add(taskPeriodCombobox);

		initializeFields();
	}

	private Task extractTask() {
		return Task
				.builder()
				.id(((Task) taskComboBox.getSelectedItem()).getId())
				.title(taskTitleTextbox.getText())
				.description(taskDescriptionTextArea.getText())
				.period((Period) taskPeriodCombobox.getSelectedItem())
				.build();
	}

	private void setStyle() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.err.println("Fail on getting style.");
		}
	}
}
