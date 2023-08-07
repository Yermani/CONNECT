/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.connectapp.entities.user.User;

/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class AcceuilController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Button btnRemboursement;
    @FXML
    private Button profilebtn;
    @FXML
    private Button claimsBtn;

    User currrentUser;
    @FXML
    private AnchorPane content1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickEvents(ActionEvent event) {
    }

    @FXML
    private void clickAddRemboursement(ActionEvent event) {
        if (currrentUser.getRole().equals("Admin")) {
            try {
                FXMLLoader root = new FXMLLoader(getClass().getResource("RemboursementMenu.fxml"));
                Parent parent = root.load();
                content.getChildren().setAll(parent);
                /*             RemboursementMenuController PUControler = root.getController();
                PUControler.getUserData(currrentUser);*/
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("RemboursementMenuEntreprise.fxml"));
                content.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void clickHome(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            content.getChildren().setAll(root);
        } catch (Exception ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToProfile(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProfileUser02.fxml"));
            content.getChildren().setAll(root);
        } catch (Exception ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToClub(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("ClubDataGridFXML.fxml"));
            Parent parent = root.load();
            ClubDataGridController PUControler = root.getController();
            PUControler.getUserData(currrentUser);
            content.getChildren().setAll(parent);
        } catch (Exception ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToClaims(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("utilisateur.fxml"));
            content.getChildren().setAll(root);
        } catch (Exception ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getUserData(User current) {
        currrentUser = new User(current);
        System.out.println(current.getFirstName() + current.getLastName());
        profilebtn.setText(currrentUser.getFirstName() + " " + currrentUser.getLastName());
    }

}
