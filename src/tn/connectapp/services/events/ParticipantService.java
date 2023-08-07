/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.events;

import tn.connectapp.entities.events.Participant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author Administrator
 */
public class ParticipantService {
    public List<Participant> listerParticipant(String x) {
        List<Participant> participantList = new ArrayList<>();
        try {
              
            String requete = "SELECT users.age , users.ecole , users.email, users.Nom, users.prenom FROM users, participantevente where users.idauth = participantevente.idparticipant and  participantevente.idevent= ?";
           Connection con = MyConnection.getInstance().getCnx();
             PreparedStatement st; 
             st = con.prepareStatement(requete);
            st.setString(1,x);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Participant e = new Participant();
                e.setAge(Integer.parseInt(rs.getString("age")));
                e.setEcole(rs.getString("ecole"));
                e.setEmail(rs.getString("email"));
                e.setNom(rs.getString("Nom"));
               e.setPrenom(rs.getString("prenom"));
               
                participantList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return participantList;
    }
}
