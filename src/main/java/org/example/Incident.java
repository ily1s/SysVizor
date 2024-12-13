package org.example;

import lombok.Data;

import java.sql.SQLException;
import java.util.Set;
import java.sql.Time;
@Data
public class Incident {
    private String reference;
    private Time time;
    private String status;
    Membre membre;

    public interface incidentDao{
        public void inserer(Incident i) throws SQLException;
        public void inser(Set<Incident> is) throws SQLException;
    }
}
