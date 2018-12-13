package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrowsePanel extends JPanel implements ActionListener{
	
	private MainFrame parent;
	private JPanel buttonPanel;
    private JButton detailButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JScrollPane tablePanel; 
    private JTable userTable;
    
    

	public BrowsePanel(MainFrame frame) {
		parent = frame;
		initialize();
	}

	private void initialize() {
		
		this.setName("browserPanel");
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(),BorderLayout.CENTER);
		this.add(getButtonPanel(),BorderLayout.SOUTH);
	}
	
	private JScrollPane getTablePanel() {
		if (tablePanel==null) {
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	private Component getUserTable() {
		 if (userTable == null) {
	            userTable = new JTable();
	            userTable.setName("userTable");
	        }

	        return userTable;
	}
	
	private Component getButtonPanel() {
		if(buttonPanel==null)
		{
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(),null);
			buttonPanel.add(getEditButton(),null);
			buttonPanel.add(getDeleteButton(),null);
			buttonPanel.add(getDetailButton(),null);
		}
		return buttonPanel;
	}


	private JButton getAddButton() {

		if(addButton == null) {
			addButton = new JButton();
			addButton.setText("Add");//localize
			addButton.setName("addButton");
			addButton.setActionCommand("add");
			addButton.addActionListener(this);
		}
		return addButton;
	}


	private JButton getEditButton() {

		if(editButton == null) {
			editButton = new JButton();
			editButton.setText("Edit");//localize
			editButton.setName("editButton");
			editButton.setActionCommand("edit");
			editButton.addActionListener(this);
		}
		return editButton;
	}


	private JButton getDeleteButton() {

		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Delete");//localize
			deleteButton.setName("deleteButton");
			deleteButton.setActionCommand("delete");
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}


	private JButton getDetailButton() {

		if(detailButton == null) {
			detailButton = new JButton();
			detailButton.setText("Details");//localize
			detailButton.setName("detailsButton");
			detailButton.setActionCommand("details");
			detailButton.addActionListener(this);
		}
		return detailButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		}
		
	}



}
