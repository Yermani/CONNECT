/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import tn.connectapp.entities.user.User;
import tn.connectapp.services.user.UserService;
import tn.connectapp.utils.commun.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Wissal
 */
public class SignInController implements Initializable {

    @FXML
    private AnchorPane AP1;
    @FXML
    private Rectangle Rect1;
    @FXML
    private Rectangle FormLog;
    @FXML
    private Pane pane2;
    @FXML
    private Text titleLogIn;
    @FXML
    private Hyperlink hlForgotPassword;
    @FXML
    private Button btnLog;
    @FXML
    private AnchorPane carre;

    private UserService us;
    @FXML
    private TextField TFEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private ImageView btnFermer;

    Connection cnx;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private ImageView background;
    @FXML
    private ImageView tfLogo;
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MyConnection.getInstance().getCnx();
        //   us = new UserService();

    }

    @FXML
    private void ButtonLogIn(ActionEvent event) throws IOException {

        /*   FXMLLoader root = new FXMLLoader(getClass().getResource("ProfileUser02.fxml"));
                        Parent parent = root.load();
                       // TFEmail.getScene().setRoot(parent);
       ProfileUserController PUControler = root.getController();
       PUControler.myFunction(TFEmail.getText());
       Stage  stage = new Stage();
       stage.setScene(new Scene(parent));
       stage.show();
         */
        String Email = TFEmail.getText();
        String Password = tfPassword.getText();
        System.out.println(Email);
        System.out.println(Password);

        String query = "SELECT * FROM connect.user WHERE `Email` = ? AND `Password` = ?";

        if (Email.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Your Email", "Empty Username", 2);
        } else if (Password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Your Password", "Empty Password", 2);
        } else {

            try {
                // Maconnexion con = new Maconnexion();
                pst = cnx.prepareStatement(query);

                pst.setString(1, Email);
                pst.setString(2, Password);
                rs = pst.executeQuery();

                if (rs.next()) {

                    User current = new User();

                    current.setId_user(rs.getInt("id_user"));
                    current.setFirstName(rs.getString("FirstName"));
                    current.setLastName(rs.getString("LastName"));
                    current.setEmail(rs.getString("Email"));
                    current.setGender(rs.getString("Gender"));
                    current.setClub(rs.getString("Club"));
                    current.setDateBirth(rs.getDate("DateBirth").toString());
                    current.setRole(rs.getString("Role"));

                    FXMLLoader root = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                    Parent parent = root.load();
                 //   TFEmail.getScene().setRoot(parent);

                    /*        FXMLLoader root = new FXMLLoader(getClass().getResource("../commun/Acceuil.fxml"));
            Parent parent = root.load();
            TFEmail.getScene().setRoot(parent);*/
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    AcceuilController PUControler = root.getController();
                    PUControler.getUserData(current);
                    // Stage  stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.show();

                    /*  FXMLLoader root = new FXMLLoader(getClass().getResource("ProfileUser02.fxml"));
                        Parent parent = root.load();
                        TFEmail.getScene().setRoot(parent);
                     */
                    //  root.getNamespace().put(TFEmail.getText(), );
//                SignInControllerController sic = root.getController();
//                sic.setRef(u.getRef());
                } else {
                    // error message
                    JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error", 2);
                }

            } catch (SQLException | IOException ex) {
                System.out.println(ex);
            }

        }

//
//              showMessageDialog(null, "TEST Button"+textPassWord.getText());
        /*         String username=TFEmail.getText();
              String password=tfPassword.getText();
                    

             if(username.equals("")&& password.equals("")){
                 JOptionPane.showMessageDialog(null, "Username or Password Blank");
             }
             else
                 
         */
    }

    @FXML
    private void HLForgotPW(ActionEvent event) {
    }

    @FXML
    private void ActionFermer(Event event) {
        // showMessageDialog(null, "TEST Button");

        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("StartPage1.fxml"));
            Parent parent = root.load();
            btnFermer.getScene().setRoot(parent);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void ResetForm(ActionEvent event) {
        TFEmail.setText("");
        tfPassword.setText("");
    }

}
