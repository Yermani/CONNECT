/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import tn.connectapp.entities.user.User;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Wissal
 */
public class ViewUsersController implements Initializable {

    @FXML
    private AnchorPane AP1Admin;
    @FXML
    private ChoiceBox<?> btnDeconnexion;
    @FXML
    private ImageView iconeAdmin;
    @FXML
    private ImageView logoAdmin;
    @FXML
    private ComboBox<?> SelectTeam;
    @FXML
    private Hyperlink APmenu;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAddUser;
    @FXML
    private TableView<User> TableView1;
    @FXML
    private TableColumn<User, String> TCfirstName;
    @FXML
    private TableColumn<User, String> tcLastName;
    @FXML
    private TableColumn<User, String> tcClub;
    @FXML
    private TableColumn<User, String> tcEmail;
    @FXML
    private TableColumn<User, String> tcPassword;
    @FXML
    private TableColumn<?, ?> tcDateBirth;
    @FXML
    private TableColumn<?, ?> tcGender;
    
    ObservableList<User> list= FXCollections.observableArrayList(
            
           new User("Wissal", "YERMANI", "club X1 ", "wissal.yermani1@esprit.tn", "", "", "F"),
           new User("Eya", "LAKHAL", "ClubY", "eya.lakhal@esprit.tn", "", "", "F"),
           new User("Ines", "DKHILI", "ClubA", "ines.dkhili@esprit.tn", "", "", "F"),
           new User("Haifa", "JOUINI", "ClubY", "haifa.jouini@esprit.tn", "", "", "F"),
           new User("Noussayer", "DERBEL", "ClubZ", "noussayer.derbel@esprit.tn", "", "", "F"),
           new User("MohamedAli", "CHATTI", "ClubY", "MedAli.chatti@esprit.tn", "", "", "F")
    );
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //FirstName.setCellValueFactory(new Property ValueFactory<user, String> ("FirstName"));
       // LastName.setCellValueFactory(new Property ValueFactory<user, String> ("LastName"));
    }    

    @FXML
    private void btnAddUserAdmin(ActionEvent event) {
        
        
    }
    
}
