package br.com.alansep.dailyhelper.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.alansep.dailyhelper.model.Task;
import br.com.alansep.dailyhelper.service.TaskService;
import br.com.alansep.dailyhelper.service.UpdateBoxService;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField titleField;
	private JTextArea descriptionField;
	private TaskService taskService;
	private JComboBox<Task> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setTitle("Daily Helper");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 488);
		getContentPane().setLayout(null);
		taskService = new TaskService();
		comboBox = new JComboBox<Task>();
		comboBox.setBounds(10, 35, 246, 22);
		getContentPane().add(comboBox);

		Thread comboBoxThread = new Thread(new UpdateBoxService(comboBox));	
		comboBoxThread.start();
		
		JButton btnNewButton = new JButton("Add Task");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskService.saveTask(Task.builder().title(titleField.getText()).description(descriptionField.getText()).build());
				titleField.setText("");
				descriptionField.setText("");
			}
		});
		btnNewButton.setBounds(10, 425, 118, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Generate Report");
		btnNewButton_1.setBounds(138, 425, 118, 23);
		getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 413, 276, 2);
		getContentPane().add(separator);
		
		titleField = new JTextField();
		titleField.setBounds(10, 97, 246, 20);
		getContentPane().add(titleField);
		titleField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Select a created task:");
		lblNewLabel.setBounds(10, 15, 118, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Task Title");
		lblNewLabel_1.setBounds(10, 78, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		descriptionField = new JTextArea();
		descriptionField.setBounds(10, 157, 246, 245);
		getContentPane().add(descriptionField);
		
		JLabel lblNewLabel_2 = new JLabel("Task Description");
		lblNewLabel_2.setBounds(10, 132, 86, 14);
		getContentPane().add(lblNewLabel_2);
	}
}
