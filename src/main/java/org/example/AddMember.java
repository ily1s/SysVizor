package org.example;

import java.awt.*;
import java.sql.SQLException;
import java.util.UUID;

public class AddMember {
    Frame frame;
    Panel mainPanel, formPanel;
    Label lNom, lPrenom, lEmail, lPhone;
    TextField NomField, PrenomField, EmailField, PhoneField;
    Button InsertButton;

    public void start() {
        // Create Frame and Main Panel with BorderLayout for padding
        frame = new Frame("Add New Member");
        mainPanel = new Panel(new BorderLayout(10, 10)); // BorderLayout with gaps
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Create a form panel for fields and buttons
        formPanel = new Panel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        // GridBagConstraints for alignment and spacing
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around each component
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left

        // Add "Nom" field
        lNom = new Label("Nom:");
        NomField = new TextField(20); // Set preferred width
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lNom, gbc);

        gbc.gridx = 1;
        formPanel.add(NomField, gbc);

        // Add "Prenom" field
        lPrenom = new Label("Prenom:");
        PrenomField = new TextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lPrenom, gbc);

        gbc.gridx = 1;
        formPanel.add(PrenomField, gbc);

        // Add "Email" field
        lEmail = new Label("Email:");
        EmailField = new TextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lEmail, gbc);

        gbc.gridx = 1;
        formPanel.add(EmailField, gbc);

        // Add "Phone" field
        lPhone = new Label("Phone:");
        PhoneField = new TextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lPhone, gbc);

        gbc.gridx = 1;
        formPanel.add(PhoneField, gbc);

        // Add "Insert" button
        InsertButton = new Button("Insert");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span the button across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        formPanel.add(InsertButton, gbc);

        // Add event listener to Insert button
        InsertButton.addActionListener(e -> {
            if (NomField.getText().isEmpty() || PrenomField.getText().isEmpty() || EmailField.getText().isEmpty() || PhoneField.getText().isEmpty()) {
                Dialog dialog = new Dialog(frame, "Validation Error", true);
                dialog.setLayout(new FlowLayout());
                dialog.add(new Label("Remplir tous les champs!"));
                Button okButton = new Button("OK");
                dialog.add(okButton);
                okButton.addActionListener(a -> dialog.setVisible(false));
                dialog.setSize(300, 150);
                dialog.setVisible(true);
            } else {
                MembreDaoImpl md = new MembreDaoImpl();
                Membre member = new Membre(UUID.randomUUID().toString(), NomField.getText(), PrenomField.getText(), EmailField.getText(), PhoneField.getText());
                try {
                    md.insrer(member);
                } catch (SQLException sqlerror) {
                    sqlerror.printStackTrace();
                }
            }
        });


        // Add padding around the formPanel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Configure Frame
        frame.add(mainPanel);
        frame.setSize(500, 350); // Increased size for better spacing
        frame.setVisible(true);

        // Add window closing event
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        AddMember addMember = new AddMember();
        addMember.start();
    }
}
