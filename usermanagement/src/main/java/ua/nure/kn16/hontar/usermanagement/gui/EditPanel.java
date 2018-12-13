package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.db.DatabaseException;
import ua.nure.kn16.hontar.usermanagement.util.Messages;

public class EditPanel extends JPanel implements ActionListener {
	
	private static final String EDIT_MESSAGE = "Fill all fields";
	
    private final static int FRAME_WIDTH = 500;
    private final static int FRAME_HEIGHT = 700;
    private Color bgColor = Color.WHITE;
    private MainFrame parent;
    private JButton cancelButton;
    private JButton submitButton;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JTextField dateOfBirthField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private User user;

    EditPanel(MainFrame frame) {
        this.parent = frame;
        user = parent.getSelectedUser();
        initialize();
    }

    private void initialize() {
        this.setName("editPanel");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);

    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabelField(fieldPanel, Messages.getString("name_label"), getFirstNameField());
            addLabelField(fieldPanel, Messages.getString("surname_label"), getLastNameField());
            addLabelField(fieldPanel, Messages.getString("date.of.birth_label"), getDateOfBirthField());
        }

        return fieldPanel;
    }

    private JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField");
        }

        return dateOfBirthField;
    }

    private JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField");
        }

        return lastNameField;
    }

    private void addLabelField(JPanel fieldPanel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        fieldPanel.add(label);
        fieldPanel.add(textField);
    }

    private JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField");
        }

        return firstNameField;
    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }

        return buttonPanel;
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setName("cancelButton");
            cancelButton.setText(Messages.getString("cancel_button"));
            cancelButton.setActionCommand("cancel_button");
            cancelButton.addActionListener(this);
        }

        return cancelButton;
    }

    private JButton getOkButton() {
        if (submitButton == null) {
            submitButton = new JButton();
            submitButton.setName("submitButton");
            submitButton.setText(Messages.getString("submit_edit_button"));
            submitButton.setActionCommand("submit");
            submitButton.addActionListener(this);
        }

        return submitButton;
    }

    private void clearFields() {
        getFirstNameField().setText("");
        getFirstNameField().setBackground(bgColor);

        getLastNameField().setText("");
        getLastNameField().setBackground(bgColor);

        getDateOfBirthField().setText("");
        getDateOfBirthField().setBackground(bgColor);
    }

    public void actionPerformed(ActionEvent e) {
        label: if ("submit".equalsIgnoreCase(e.getActionCommand())) {
            String firstName = getFirstNameField().getText();
            String lastName = getLastNameField().getText();
            String dateOfBirth = getDateOfBirthField().getText();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !dateOfBirth.isEmpty()) {
                user.setFirstName(getFirstNameField().getText());
                user.setLastName(getLastNameField().getText());
                DateFormat format = DateFormat.getDateInstance();
                try {
                    user.setDateOfBirthd(format.parse(getDateOfBirthField().getText()));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, e1.getMessage() + "\nCorrect form is 'yyyy-mm-dd'", "Error", JOptionPane.ERROR_MESSAGE);
                    getDateOfBirthField().setBackground(Color.RED);
                    break label;
                }

                try {
                    parent.getDao().update(user);
                } catch (DatabaseException e1) {
                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            clearFields();
            this.setVisible(false);
            parent.showBrowsePanel();
            } else {
                JOptionPane.showMessageDialog(this, EDIT_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
                getFirstNameField().setBackground(Color.RED);
                getLastNameField().setBackground(Color.RED);
                getDateOfBirthField().setBackground(Color.RED);
            }
        } else if ("cancel_button".equalsIgnoreCase(e.getActionCommand())) {
            clearFields();
            this.setVisible(false);
            parent.showBrowsePanel();
        }
    }


}
