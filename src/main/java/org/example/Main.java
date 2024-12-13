package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Interface add member
        AddMember addMember = new AddMember();
        addMember.start();

        // Create an instance of MembreDaoImpl
        MembreDaoImpl membreDao = new MembreDaoImpl();

        // Get the CSV file path from the resources folder
        URL resource = Main.class.getClassLoader().getResource("membres.csv");
        if (resource == null) {
            System.err.println("CSV file not found in the resources folder.");
            return;
        }

        String csvFilePath = resource.getPath();

        try {
            // Load members from the CSV file
            Set<Membre> membres = membreDao.chargeListMembres(csvFilePath);

            // Print out loaded members
            System.out.println("Loaded members from CSV:");
            for (Membre membre : membres) {
                System.out.println("ID: " + membre.getIdentifiant() +
                        ", Name: " + membre.getNom() +
                        ", First Name: " + membre.getPrenom() +
                        ", Email: " + membre.getEmail() +
                        ", Phone: " + membre.getPhone());
            }

            // Optionally, add them to the database
            System.out.println("\nSaving members to the database...");
            for (Membre membre : membres) {
                membreDao.insrer(membre);
            }
            System.out.println("All members saved to the database successfully!");

        } catch (IOException e) {
            System.err.println("Failed to load members from CSV: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}