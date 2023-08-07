/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.connectapp.entities.club.Category;
import tn.connectapp.entities.club.Post;
import tn.connectapp.services.club.PostService;
import tn.connectapp.utils.commun.InputControl;

/**
 * FXML Controller class
 *
 * @author crist
 */
public class AddPostController implements Initializable {

    PostService ps = new PostService();
    @FXML
    private AnchorPane addPostPane;
    @FXML
    private Pane addCategrytf;
    @FXML
    private Button resetCateButton;
    @FXML
    private Button savePostButton;
    @FXML
    private ImageView exittf;
    @FXML
    private TextArea postdescriptiontf;
    @FXML
    private TextField Posttf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resetCurrent(ActionEvent event) {
        Posttf.setText("");
        postdescriptiontf.setText("");
    }


    @FXML
    private void closeCurrent(MouseEvent event) {
        addPostPane.setVisible(false);
    }

    @FXML
    private void addPost(ActionEvent event) {
                Long currentUser = 1234566L;

        Date sysdate = new Date(System.currentTimeMillis());

        if (InputControl.isNull(Posttf.getText())) {
            InputControl.genAlert("101", "ERROR", "Name", "", "").show();
        } else {
            if (!InputControl.isString(Posttf.getText())) {
                InputControl.genAlert("201", "ERROR", Posttf.getText(), "", "").show();
            } else {
                try {
                    ps.createPost(new Post(null, Posttf.getText(), currentUser, sysdate, "ETUD", postdescriptiontf.getText(),null));

                } catch (NumberFormatException | SQLException ex) {
                    InputControl.genAlert("300", "ERROR", "", "", "").show();
                }
                 addPostPane.getParent().setUserData(Posttf.getText());

                addPostPane.setVisible(false);
                InputControl.genAlert("500", "CONFIRMATION", "Post", "", "").show();

            }
        }

    }
    
}
