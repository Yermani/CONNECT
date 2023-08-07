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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
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
public class RepondreController implements Initializable {

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
     @FXML
    private JFXTextArea answer;
     
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList <String> matrialStatusList = FXCollections.observableArrayList("opinion","recommendation","Technical Problem");
         choix.setItems(matrialStatusList);
    }    
    
    @FXML
    private void save(ActionEvent event) {

        connection = MyConnection.getInstance().getCnx();
       String rep = answer.getText();
        

        if ( rep.isEmpty()) {
            String titre = "Insert";
           String msg = "Please Fill all Data";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.WARNING);
           tray.showAndDismiss(Duration.millis(3000));
        } else {
            getQuery();
            insert();
            String titre = "Insert";
           String msg = "Insert succed";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));

        }

    }
    
    private void getQuery() {

        
            query = "UPDATE Reclamations SET `answer`=? WHERE id = '"+recId+"'";
                   
           
    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void InProgressStatu(){
        connection = MyConnection.getInstance().getCnx();
        query = "UPDATE Reclamations SET `status`='in progress' WHERE id = '"+recId+"'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
             String titre = "Update";
           String msg = "Status update succeded";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));
        } catch (SQLException ex) {
            Logger.getLogger(RepondreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void DoneStatu(){
        connection = MyConnection.getInstance().getCnx();
        query = "UPDATE Reclamations SET `status`='done' WHERE id = '"+recId+"'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            String titre = "Update";
           String msg = "Status update succeded";
           TrayNotification tray = new TrayNotification();
           AnimationType anim = AnimationType.POPUP;
           tray.setAnimationType(anim);
           tray.setTitle(titre);
           tray.setMessage(msg);
           tray.setNotificationType(NotificationType.SUCCESS);
           tray.showAndDismiss(Duration.millis(3000));
        } catch (SQLException ex) {
            Logger.getLogger(RepondreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setTextField(int id,String type,String nam, String pren, String title,LocalDate date1,String descrip, String rep) {
        recId = id;
        choix.setDisable(true);
        choix.setEditable(false);
        choix.setValue(type);
        nom.setEditable(false);
        nom.setText(nam);
        prenom.setEditable(false);
        prenom.setText(pren);
        titre.setEditable(false);
        titre.setText(title);
       date.setValue(date1);
       date.setEditable(false);
        desc.setEditable(false);
        desc.setText(descrip);
        
        answer.setText(rep);
        

    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
