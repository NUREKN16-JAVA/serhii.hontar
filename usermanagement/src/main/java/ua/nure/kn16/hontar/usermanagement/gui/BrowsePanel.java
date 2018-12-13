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

import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.db.DatabaseException;
import ua.nure.kn16.hontar.usermanagement.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener{
	
	private static final String DELETE_MESSAGE = "Are you sure you want to delete this user?";
	private static final String SELECT_MESSAGE = "Select row of user for opening details";
	
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
			addButton.setText(Messages.getString("BrowsePanel.add"));
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
			addButton.addActionListener(this);
		}
		return addButton;
	}


	private JButton getEditButton() {

		if(editButton == null) {
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit"));
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}


	private JButton getDeleteButton() {

		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete"));
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}


	private JButton getDetailButton() {

		if(detailButton == null) {
			detailButton = new JButton();
			detailButton.setText(Messages.getString("BrowsePanel.details"));
			detailButton.setName("detailsButton"); //$NON-NLS-1$
			detailButton.setActionCommand("details"); //$NON-NLS-1$
			detailButton.addActionListener(this);
		}
		return detailButton;
	}
	
    public User getSelectedUser() {
        int selectedRow = getUserTable().getSelectedRow();
        int selectedColumn = 0;
        Long userId = null;
        User user = null;

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, SELECT_MESSAGE,
                                            "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                userId = (Long) userTable.getValueAt(userTable.getSelectedRow(), selectedColumn);
                user =  parent.getDao().find(userId);
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return user;
    }

    private void deleteUser(User user) {
        int result = JOptionPane.showConfirmDialog(this, DELETE_MESSAGE, "Confirm deleting",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                parent.getDao().delete(user);
                getUserTable().setModel(new UserTableModel(parent.getDao().findAll()));
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    


	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
        if (action.equalsIgnoreCase("add")) {
            this.setVisible(false);
            parent.showAddPanel();
        } else if (action.equalsIgnoreCase("details")) {
            User selectedUser = getSelectedUser();
            JOptionPane.showMessageDialog(this, selectedUser.toString(), "User info", JOptionPane.INFORMATION_MESSAGE);
        } else if (action.equalsIgnoreCase("delete")) {
            User selectedUser = getSelectedUser();
            if (selectedUser != null) {
                deleteUser(selectedUser);
            }
        } else if (action.equalsIgnoreCase("edit")) {
            int selectedRow = userTable.getSelectedRow();
            int selectedColumn = userTable.getSelectedColumn();

            if (selectedRow != -1 | selectedColumn != -1) {
                this.setVisible(false);
                parent.showEditPanel();
            } else {
                JOptionPane.showMessageDialog(this, SELECT_MESSAGE,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
}
