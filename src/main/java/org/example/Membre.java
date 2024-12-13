package org.example;

import lombok.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Data
public class Membre {
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    List<Incident> incidentList;

    public Membre(String s, String s1, String s2, String s3, String s4) {
        this.identifiant = s;
        this.nom = s1;
        this.prenom = s2;
        this.email = s3;
        this.phone = s4;
    }

    @Override
    public boolean equals(Object Obj){
        if(Obj == null){
            return false;
        }
        if(Obj instanceof Membre){
            Membre other = (Membre)Obj;
            return this.identifiant.equals(other.identifiant);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.identifiant.hashCode();
    }

    public interface MembreDao{
        void insrer(Membre m) throws SQLException;
        List<Incident> chargeListIncidents() throws SQLException;
        Set<Membre> chargeListMembres(String file ) throws IOException;
    }
}




