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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import tn.connectapp.entities.claim.Reclamation;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class ReadOnlyController implements Initializable {
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
    @FXML
    private JFXTextField status;
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Reclamation reclamation = null;
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
    
    
    void ReadTextField(int id,String type,String nam, String pren, String title, LocalDate date1, String descrip, String rep, String stat) {
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
        answer.setEditable(false);
        answer.setText(rep);
        status.setEditable(false);
        status.setText(stat);

    }
       
    
}
