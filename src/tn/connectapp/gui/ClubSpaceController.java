/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.connectapp.entities.club.Club;
import tn.connectapp.entities.club.Membership;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.services.club.MembershipService;


/**
 * FXML Controller class
 *
 * @author crist
 */
public class ClubSpaceController implements Initializable {
    
        ClubService cs = new ClubService();
        MembershipService ms = new MembershipService();
    
      @FXML
    private Tab ClubData;

    @FXML
    private Button joinClub;

    @FXML
    private Button updateClub;

    @FXML
    private ImageView logoImage;

    @FXML
    private Hyperlink mail;

    @FXML
    private Text phoneNumber;

    @FXML
    private Text univercityText;

    @FXML
    private Text establishmentText;

    @FXML
    private Text CategoryText;

    @FXML
    private Text CreationdateText;

    @FXML
    private Tab eventPane;

    @FXML
    private Button joinClub1;


    @FXML
    private Tab membersPane;

    @FXML
    private Pagination pagination;

    Club currentClub;
    @FXML
    private Label clubName;
    @FXML
    private Text clubDescription;
    @FXML
    private AnchorPane ClubSpace;

    Long clubId ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* Stage stage = (Stage) ClubSpace.getScene().getWindow();
        Club curr = (Club) stage.getUserData();
        System.out.println(curr.getCategory());*/
        
    }    
    
    
        void AddMembersList(ActionEvent event) throws SQLException {
        
        for (Membership m : ms.ReadMembersClub(currentClub.getIdClub()))
        {
            
        }
        

    }
    @FXML
    private void receiveData(MouseEvent event) {
        // Step 1
        AnchorPane node = (AnchorPane) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        Club curr = (Club) stage.getUserData();
        // Step 3
        String name = curr.getName();
        String email = curr.getEmail();
        
        System.out.println(name);
        System.out.println(email);

    }

    @FXML
    private void AddMembersList(Event event) {
    }

    void getUserData(Club selectedItem) {
         clubId = selectedItem.getIdClub();
        clubName.setText(selectedItem.getName());
        clubDescription.setText(selectedItem.getDescription());
        univercityText.setText(selectedItem.getUniversity());
        establishmentText.setText(selectedItem.getInstitue());
        CategoryText.setText(selectedItem.getCategory());
        CreationdateText.setText(selectedItem.getCreationDate().toString());
        mail.setText(selectedItem.getEmail());
        phoneNumber.setText(selectedItem.getPhoneNumber().toString());

    }
    
}
