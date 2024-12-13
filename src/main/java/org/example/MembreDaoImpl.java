package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class MembreDaoImpl implements Membre.MembreDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/supervisor_system";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    @Override
    public void insrer(Membre m) throws SQLException {
        String query = "insert into membre values(?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, m.getIdentifiant());
            preparedStatement.setString(2, m.getNom());
            preparedStatement.setString(3, m.getPrenom());
            preparedStatement.setString(4, m.getEmail());
            preparedStatement.setString(5, m.getPhone());

            if (preparedStatement.executeUpdate() == 1) {
                System.out.println("Membre inserted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Incident> chargeListIncidents() throws SQLException {
        List<Incident> incidents = new ArrayList<>();
        String query = "select * from incident";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Incident incident = new Incident();
                incident.setReference(resultset.getString("Ref"));
                incident.setTime(resultset.getTime("Time"));
                incident.setStatus(resultset.getString("Status"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Set<Membre> chargeListMembres(String file ) throws IOException {
        Set<Membre> set = new HashSet();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] element = line.split(",");
            set.add(new Membre(UUID.randomUUID().toString(),element[0], element[1], element[2], element[3]));
        }
        br.close();
        return set;
    }
}
