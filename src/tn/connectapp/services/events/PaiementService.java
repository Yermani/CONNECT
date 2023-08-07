/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.events;


import Entities.Paiement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author Administrator
 */
public class PaiementService {
       public void ajouterpaiement(Paiement p){
          try {
                  Connection con = MyConnection.getInstance().getCnx();;
                PreparedStatement ps = con.prepareStatement("INSERT INTO paiement (email, tel,carte, iduser, idevente, ideventc)"
                     + "values(?,?,?,?,?,?) ");
                
                ps.setString(1,p.getEmail() );
                ps.setInt(2, p.getTel());
                   ps.setInt(3,p.getCarte() );
                
                ps.setString(4, p.getIduser());
                ps.setString(5, p.getIdevente());
                   ps.setString(6,p.getIdeventc() );
                
               
                
              
                  
                     
                    
               
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
