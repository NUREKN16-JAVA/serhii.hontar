package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener{

	private MainFrame parent;
	
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	
	private JButton cancelButton;
	
	private JLabel lastName;
	private JLabel firstName;
	private JLabel dateOfBirth;
	private JLabel id;

	
	private final User user;
	public DetailsPanel(MainFrame parent) {
		this.parent = parent;
		user = parent.getSelectedUser();
		initialize();
		
	}
	private void initialize() {
		this.setName("detailsPanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(),BorderLayout.NORTH);
		this.add(getButtonPane(),BorderLayout.SOUTH);
	}
	private Component getButtonPane() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getCancelButton(),null);
		}
		return buttonPanel;
	}
	private JButton  getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("DetailsPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}
	private JPanel getFieldPanel() {
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(4, 1));
			addLabel(fieldPanel,Messages.getString("DetailsPanel.id"),getId()); //$NON-NLS-1$
			addLabel(fieldPanel,Messages.getString("DetailsPanel.first_name"),getFirstName()); //$NON-NLS-1$
			addLabel(fieldPanel,Messages.getString("DetailsPanel.last_name"),getLastName()); //$NON-NLS-1$
			addLabel(fieldPanel,Messages.getString("DetailsPanel.date_of_birth"),getDateOfBirth()); //$NON-NLS-1$

		}
		return fieldPanel;
	}
	
	private void addLabel(JPanel panel, String labelText, JLabel jLabel) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(jLabel);
		panel.add(label);
		panel.add(jLabel);
	}
	
	
	private JLabel getId() {
        if (id == null) {
            id = new JLabel();
            id.setName("id"); //$NON-NLS-1$
            id.setText(String.valueOf(user.getId()));
        }
        return id;
	}
	
	private JLabel getFirstName() {
        if (firstName == null) {
            firstName = new JLabel();
            firstName.setName("firstName"); //$NON-NLS-1$
            firstName.setText(user.getFirstName());
        }
        return firstName;
	}
	
	private JLabel getLastName() {
        if (lastName == null) {
            lastName = new JLabel();
            lastName.setName("lastName"); //$NON-NLS-1$
            lastName.setText(user.getLastName());
        }
        return lastName;
	}

	private JLabel getDateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = new JLabel();
            dateOfBirth.setName("dateOfBirth"); //$NON-NLS-1$
            dateOfBirth.setText(user.getDateOfBirthd().toString());
        }
        return dateOfBirth;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        if ("cancel".equalsIgnoreCase(e.getActionCommand())) {//$NON-NLS-1$
            this.setVisible(false);
        }
        this.setVisible(false);
        parent.showBrowsePanel();
    }

}
