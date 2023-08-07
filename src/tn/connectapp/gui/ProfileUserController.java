/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import static javax.swing.JOptionPane.showMessageDialog;
import tn.connectapp.entities.user.User;
import tn.connectapp.services.user.UserService;
import tn.connectapp.utils.commun.MyConnection;


/**
 * FXML Controller class
 *
 * @author Wissal
 */
public class ProfileUserController implements Initializable {

    @FXML
    private ImageView imgProfile;
    @FXML
    private ImageView logprofile;
    @FXML
    private AnchorPane APeditProf;
    @FXML
    private Text fNameProf;
    @FXML
    private Button inserImgProf;
    @FXML
    private Button insertCv;
    @FXML
    private Button editProf;
    private Button btnClose;
    @FXML
    private Text lastNameProf;
    @FXML
    private AnchorPane APcadre;
    @FXML
    private Rectangle cadreEdit;
    private Button btnSave;
    @FXML
    private Text titleSave;
    @FXML
    
     private DatePicker tfdate;
    @FXML
    private RadioButton rbw;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton rbM;
    @FXML
    private Label txtGender;
    @FXML
    private TextField tfClub;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfFirstName;
    @FXML
    private Button btnupdate;
    
     public UserService us ;
     public Connection cnx ;
     public  PreparedStatement pst;
     public   ResultSet rs;
    @FXML
    private Button btnClose1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx =     MyConnection.getInstance().getCnx();
        us = new UserService();
        
        
      // btnSave.setVisible(false);
       btnupdate.setVisible(false);
        tfFirstName.setVisible(false);
        
        tfFirstName.setVisible(false);
        txtGender.setVisible(false);
        tfClub.setVisible(false);
        tfLastName.setVisible(false);
        tfEmail.setVisible(false);
        tfPassword.setVisible(false);
        tfFirstName.setVisible(false);
        rbM.setVisible(false);
        tfdate.setVisible(false);
        rbw.setVisible(false);
        titleSave.setVisible(false);
        
        
    }    

    public void myFunction(String text){
    
        fNameProf.setText(text);
    }
    
    @FXML
    private void buttonClose(ActionEvent event) {
//         Stage stage = (Stage) btnClose.getScene().getWindow();
//         stage.close();

       try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("StartPage1.fxml"));
            Parent parent = root.load();
            btnClose.getScene().setRoot(parent);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void buttonInsert(ActionEvent event) {
        
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Choose an image", "*.tif", "*.tiff", "*.bmp","*.jpg", "*.jpeg","*.gif", "*.png", "*.eps", "*.raw", "*.cr2", "*.nef", "*.orf", "*.sr2"));
        File selectedFile= fc.showOpenDialog(null);
       
         if (selectedFile != null){ inserImgProf.setText(selectedFile.getName());
        }
         }
    

    @FXML
    private void buttonInsertCV(ActionEvent event) {
        
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Choose a CV", "*.txt", "*.pdf"));
        File selectedFile= fc.showOpenDialog(null);
       
         if (selectedFile != null){ insertCv.setText(selectedFile.getName());
        }
    }

    @FXML
    private void hlEdit(ActionEvent event) {
        
        
        String EmailLabel= fNameProf.getText();
        System.out.println(EmailLabel);
        
          String query = "SELECT * FROM connect.user WHERE `Email` = ? ";
        try {
            pst = cnx.prepareStatement(query);
            pst.setString(1, EmailLabel);
            rs = pst.executeQuery();
            
             if (rs.next()) {
                
                 //firstname
                String FirstName= rs.getString(2);
                tfFirstName.setText(FirstName);
                //lastname
                String LastName= rs.getString(3);
                tfLastName.setText(LastName);
                //Club
                String Club= rs.getString(4);
                tfClub.setText(Club);
                //Email
                String Email= rs.getString(5);
                tfEmail.setText(Email);
                //Password
                String Password= rs.getString(6);
                tfPassword.setText(Password);
                //DateBirth
                Date DateBirth= rs.getDate(7);
                tfdate.setValue(DateBirth.toLocalDate());
                //Gender
                String Gender= rs.getString(8);
       
             }
             
        } catch (SQLException ex) {
           System.out.println(ex);
        }
       

          
        //btnSave.setVisible(true);
        btnupdate.setVisible(true);
        tfFirstName.setVisible(false);
        
        tfFirstName.setVisible(true);
        txtGender.setVisible(true);
        tfClub.setVisible(true);
        tfLastName.setVisible(true);
        tfEmail.setVisible(true);
        tfPassword.setVisible(true);
        tfFirstName.setVisible(true);
        rbM.setVisible(true);
        tfdate.setVisible(true);
        rbw.setVisible(true);
        titleSave.setVisible(true);
        
        
        
        
    }

    @FXML
    private void checkrbw(ActionEvent event) {
        
         String message="";
        if(rbM.isSelected()){
            message+= rbM.getText()+ "\n";
            System.out.println("Woman");
        }
    }

    @FXML
    private void checkrbm(ActionEvent event) {
        
         String message="";
        if(rbM.isSelected()){
            message+= rbM.getText()+ "\n";
           // Gender.g
        }
    }

    @FXML
    private void UpdateUser(ActionEvent event) {
        
        
                 //firstname
                String FirstName= tfFirstName.getText();
                //lastname
                String LastName= tfLastName.getText();           
                //Club
                String Club= tfClub.getText();               
                //Email
                String Email= tfEmail.getText();                
                //Password
                String Password= tfPassword.getText();                
                //DateBirth     
                String Date = tfdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));                
                //Gender
                String Gender= "F";
              
        try {
            
            us.updateUser(new User( tfFirstName.getText(), tfLastName.getText(),  tfClub.getText(), tfEmail.getText(),
                    tfPassword.getText() ,Date , Gender  ));
            
              showMessageDialog(null, "Update Done");
             tfFirstName.setText("");
             tfLastName.setText("");
             tfClub.setText("");
             tfEmail.setText("");
             tfPassword.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }
}

