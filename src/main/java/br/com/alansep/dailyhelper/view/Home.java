package br.com.alansep.dailyhelper.view;

import java.awt.EventQueue;
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
import br.com.alansep.dailyhelper.service.TaskService;
import br.com.alansep.dailyhelper.service.UpdateBoxService;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
		
		setStyle();
		
		setTitle("Daily Helper");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 522);
		taskService = new TaskService();
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 484, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Task Title");
		lblNewLabel_1.setBounds(10, 75, 279, 14);
		panel.add(lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 448, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -433, SpringLayout.EAST, getContentPane());

		JLabel lblNewLabel_2 = new JLabel("Task Description");
		lblNewLabel_2.setBounds(10, 149, 279, 14);
		panel.add(lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -427, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 467, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -374, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -6, SpringLayout.NORTH, lblNewLabel_2);
		comboBox = new JComboBox<Task>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					System.out.println(e.getItem());
				}
			}
		});
		comboBox.setBounds(10, 30, 279, 22);
		panel.add(comboBox);
		Thread comboBoxThread = new Thread(new UpdateBoxService(comboBox));
		comboBoxThread.start();
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 340, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -323, SpringLayout.EAST, getContentPane());

		
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 18, SpringLayout.SOUTH, lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Select a created task:");
		lblNewLabel.setBounds(10, 11, 632, 14);
		panel.add(lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 295, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -225, SpringLayout.SOUTH, getContentPane());

		titleField = new JTextField();
		titleField.setBounds(10, 97, 279, 20);
		panel.add(titleField);
		springLayout.putConstraint(SpringLayout.WEST, titleField, 360, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, titleField, -303, SpringLayout.EAST, getContentPane());
		titleField.setColumns(10);

		descriptionField = new JTextArea();
		descriptionField.setBounds(10, 174, 279, 245);
		panel.add(descriptionField);
		springLayout.putConstraint(SpringLayout.WEST, descriptionField, 372, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, descriptionField, -291, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, descriptionField, 157, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, descriptionField, 402, SpringLayout.NORTH, getContentPane());

		JSeparator separator = new JSeparator();
		separator.setBounds(-10, 430, 315, 2);
		panel.add(separator);
		springLayout.putConstraint(SpringLayout.WEST, separator, 398, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator, -245, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, titleField, -138, SpringLayout.NORTH, separator);
		springLayout.putConstraint(SpringLayout.NORTH, separator, 5, SpringLayout.SOUTH, descriptionField);

		JButton btnNewButton_1 = new JButton("Generate Report");
		btnNewButton_1.setBounds(155, 443, 134, 23);
		panel.add(btnNewButton_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 462, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, -339, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 21, SpringLayout.SOUTH, separator);

		JButton btnNewButton = new JButton("Add Task");
		btnNewButton.setBounds(10, 443, 134, 23);
		panel.add(btnNewButton);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 471, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 353, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 479, SpringLayout.WEST, getContentPane());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskService.saveTask(
						Task.builder().title(titleField.getText()).description(descriptionField.getText()).build(), comboBox);
				titleField.setText("");
				descriptionField.setText("");
			}
		});
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
