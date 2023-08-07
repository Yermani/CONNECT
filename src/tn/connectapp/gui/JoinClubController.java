/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.connectapp.entities.club.Category;
import tn.connectapp.entities.club.Club;
import tn.connectapp.entities.club.Membership;
import tn.connectapp.entities.club.Post;
import tn.connectapp.services.club.CategoryService;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.services.club.MembershipService;
import tn.connectapp.services.club.PostService;
import tn.connectapp.utils.commun.InputControl;

/**
 * FXML Controller class
 *
 * @author crist
 */
public class JoinClubController implements Initializable {
    
    MembershipService ms = new MembershipService();
    PostService ps = new PostService();

    @FXML
    private AnchorPane JoinClubPane;
    @FXML
    private Pane addCategrytf;
    @FXML
    private Button resetCateButton;
    @FXML
    private TextArea catedescriptiontf;
    @FXML
    private ImageView exittf;
    @FXML
    private Button savePostButton;
    @FXML
    private Button addCategory;
    @FXML
    private ComboBox<String> Posttf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try
        {
            for (Post cat : ps.ReadListPost("EXPL")) {
                list.add(cat.getPostName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddClubFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Posttf.setItems(list);
    }
    @FXML
    private void resetCurrent(ActionEvent event) {
        catedescriptiontf.setText("");
        Posttf.setValue("");                
    }


    @FXML
    private void closeCurrent(MouseEvent event) {
        JoinClubPane.setVisible(false);
    }

    @FXML
    private void addJoinRequest(ActionEvent event) {
                String currentUser = "Me";
        Date creationDate;
        Date sysdate = new Date(System.currentTimeMillis());

        if (InputControl.isNull(catedescriptiontf.getText())) {
            InputControl.genAlert("101", "ERROR", "Name", "", "").show();
        } else if (Posttf.getSelectionModel().isEmpty()) {
            InputControl.genAlert("103", "ERROR", "Category", "", "").show();
        } else {

                try {
                    Long postid = ps.ReadPost(null,Posttf.getSelectionModel().getSelectedItem()).getPostId();
                    ms.createMembership(new Membership(null, postid, 123L, 123L, "ETUD", sysdate, 123L, currentUser, currentUser, Posttf.getSelectionModel().getSelectedItem(), currentUser));

                    System.out.println("INSERTION OK!!");
                    InputControl.genAlert("502", "CONFIRMATION", "Membership request", "", "").show();

                } catch (Exception ex) {
                    InputControl.genAlert("300", "ERROR", "", "", ex.getMessage()).show();

                }

        }

    }

    @FXML
    private void showaddPost(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddPostFXML.fxml"));
        JoinClubPane.getChildren().set(2, pane);
    }
    
}
