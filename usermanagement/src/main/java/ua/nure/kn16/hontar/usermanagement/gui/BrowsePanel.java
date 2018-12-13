package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.kn16.hontar.usermanagement.db.DatabaseException;
import ua.nure.kn16.hontar.usermanagement.util.Messages;

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
		
		this.setName("browsePanel"); //$NON-NLS-1$
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

	private JTable getUserTable() {
		 if (userTable == null) {
	            userTable = new JTable();
	            userTable.setName("userTable");

		 }
	        return userTable;
	}

	public void initTable() {
		UserTableModel model;
		 try {
         model = new UserTableModel(parent.getDao().findAll());//$NON-NLS-1$
		 } catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList<>());
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
        getUserTable().setModel(model);
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
			addButton.setText(Messages.getString("BrowsePanel.add"));//localize //$NON-NLS-1$
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
			addButton.addActionListener(this);
		}
		return addButton;
	}


	private JButton getEditButton() {

		if(editButton == null) {
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit"));//localize //$NON-NLS-1$
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}


	private JButton getDeleteButton() {

		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete"));//localize //$NON-NLS-1$
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}


	private JButton getDetailButton() {

		if(detailButton == null) {
			detailButton = new JButton();
			detailButton.setText(Messages.getString("BrowsePanel.details"));//localize //$NON-NLS-1$
			detailButton.setName("detailsButton"); //$NON-NLS-1$
			detailButton.setActionCommand("details"); //$NON-NLS-1$
			detailButton.addActionListener(this);
		}
		return detailButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			this.setVisible(false);
			parent.showAddPanel();
		}
		
	}



}
