/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.events;

import java.sql.Connection;
import tn.connectapp.entities.events.invites;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author Administrator
 */
public class invitesService {
    public void ajouterreviews(invites inv){
          try {
                  Connection con = MyConnection.getInstance().getCnx();
                PreparedStatement ps = con.prepareStatement("INSERT INTO invites (idevente, userid)"
                     + "values(?,?) ");
                
                ps.setString(1,inv.getEvente() );
                ps.setString(2, inv.getUser());
              
                  
                     
                    
               
                /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(jTextField3.getDate());
                ps.setString(3, addDate);*/
               
               
                ps.executeUpdate();
               // Show_Products_In_JTable();
               
                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            }
       
    }
    
}
