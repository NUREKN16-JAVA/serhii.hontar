package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.db.DatabaseException;
import ua.nure.kn16.hontar.usermanagement.util.Messages;

public class DeletePanel extends JPanel implements ActionListener{
	
	private MainFrame parent;
	
	private JPanel buttonPanel;

	private JButton okButton;
	
	
	public  DeletePanel(MainFrame parent) {
		this.parent = parent;
		initialize();
		
	}
	private void initialize() {
		this.setName("deletePanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getButtonPane(),BorderLayout.SOUTH);
	}
	private Component getButtonPane() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(),null);
		}
		return buttonPanel;
	}
	private JButton  getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(Messages.getString("DeletePanel.ok")); //$NON-NLS-1$
			okButton.setName("okButton"); //$NON-NLS-1$
			okButton.setActionCommand("ok"); //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("ok".equalsIgnoreCase(e.getActionCommand())) {

		this.setVisible(false);
		parent.showBrowsePanel();
		}
	}

}