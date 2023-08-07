/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.connectapp.entities.claim.Reclamation;
import tn.connectapp.utils.commun.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private ComboBox choix = new ComboBox();
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXDatePicker date = new JFXDatePicker();
    @FXML
    private JFXTextArea desc;
   

   
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Reclamation reclamation = null;
    private boolean update;
    int recId;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        ObservableList <String> matrialStatusList = FXCollections.observableArrayList("opinion","recommendation","Technical Problem");
         choix.setItems(matrialStatusList);
         
    }
     
  
    @FXML
    private void save(ActionEvent event) {

        connection = MyConnection.getInstance().getCnx();
        String type = (String) choix.getValue();
        String name = nom.getText();
        String pren = prenom.getText();
        String title = titre.getText();
        String date_desc = String.valueOf(date.getValue());
        String description = desc.getText();
        

        if ( type== null || name.isEmpty() || pren.isEmpty()|| title.isEmpty()|| date_desc.isEmpty() || description.isEmpty()) {
            String titre = "Insert";
           String msg = "Please Fill all Data";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.WARNING);
           tray.showAndDismiss(Duration.millis(3000));

        } else if (update == false){
            getQuery();
            insert();
            clean();
           String titre = "Insert";
           String msg = "Insert succed";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));
           
            

        }else {
            getQuery();
            update();
           String titre = "Update";
           String msg = "Update succed";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));
        }

    }

    @FXML
    private void clean() {
        choix.setValue(null);
        nom.setText(null);
        prenom.setText(null);
        titre.setText(null);
        date.setValue(null);
        desc.setText(null);
        
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO Reclamations ( `type`, `nom`, `prenom`, `titre`, `date_declaration`, `description`, `status` ) VALUES (?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE Reclamations SET "
                    + "`type`=?,"
                    + "`nom`=?,"
                    + "`prenom`=?,"
                    + "`titre`=?,"
                    + "`date_declaration`=?,"
                    + "`description`=?WHERE id = '"+recId+"'";
                   
           
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, (String) choix.getValue());
            preparedStatement.setString(2, nom.getText());
            preparedStatement.setString(3, prenom.getText());
            preparedStatement.setString(4, titre.getText());
            preparedStatement.setString(5, String.valueOf(date.getValue()));
            preparedStatement.setString(6, desc.getText());
            preparedStatement.setString(7, "waiting");
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void update() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, (String) choix.getValue());
            preparedStatement.setString(2, nom.getText());
            preparedStatement.setString(3, prenom.getText());
            preparedStatement.setString(4, titre.getText());
            preparedStatement.setString(5, String.valueOf(date.getValue()));
            preparedStatement.setString(6, desc.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    void setTextField(int id,String type,String nam, String pren, String title,LocalDate date1,String descrip) {
       recId = id;
       choix.setValue(type);
        nom.setText(nam);
        prenom.setText(pren);
        titre.setText(title);
        date.setValue(date1);
        desc.setText(descrip);
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }  
}
    

