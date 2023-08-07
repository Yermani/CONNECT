/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.events;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import tn.connectapp.entities.user.User;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author Administrator
 */
public class ParticipationService {
    public int nbMotif =0 ;
   public User selectparticipation (String x ){
      User user =new User();
       try {
           String req = " SELECT * from users where login= ? ";
          
            
           Connection con = MyConnection.getInstance().getCnx();;
             PreparedStatement st; st = con.prepareStatement(req);
            st.setString(1, x);
           
             ResultSet rs = st.executeQuery();
            while (rs.next()) {
             /*   
                user.setAge(rs.getInt("age"));
                 user.setNom(rs.getString("Nom"));
                  user.setPrenom(rs.getString("Prenom"));
                   user.setEmail(rs.getString("email"));
                    user.setEcole(rs.getString("ecole"));
                    user.setIdauth(rs.getInt("idauth"));
                    */
            }
            
               
          
                      

            
            //System.out.println("Participation inserée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
       return user;
   }
       
       
}
