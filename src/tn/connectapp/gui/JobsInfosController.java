/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import tn.connectapp.entities.Offer.Internships;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class JobsInfosController implements Initializable {

    Connection cnx = DBConnect.getConnect();

    @FXML
    private Label lInternship;
    @FXML
    private Label lDescription;
    @FXML
    private Label lCompany;
    @FXML
    private Label lStartDate;
    @FXML
    private Label lEndDate;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfFamilyName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Label lSupervisor;
    @FXML
    private Button postuler;
    @FXML
    private Button uploadFile;
    private File imageFile;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refresh();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(JobsInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() throws SQLException {
        cnx = DBConnect.getConnect();
        Statement statement = cnx.createStatement();
//        String sql = "select * from table where column like '%" + textfield1.getText() + "%'";

        String sql = "SELECT company, description, start_date, end_date, supervisor from internship where title like '%" + lInternship.getText() + "%'";
        System.out.println(StudentController.title);
        lInternship.setText(StudentController.title);
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);

        try {
            while (rs.next()) {
                String company = rs.getString("company");
                String description = rs.getString("description");
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");
                String supervisor = rs.getString("supervisor");

                lCompany.setText(company);
                lDescription.setText(description);
                lStartDate.setText(start_date);
                lEndDate.setText(end_date);
                lSupervisor.setText(supervisor);

            }
        } catch (SQLException ex) {
            Logger.getLogger(JobsInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showFileChooser() {
        imageFile = getImageFromFileChooser(getStage());

    }

    private Stage getStage() {
        return (Stage) uploadFile.getScene().getWindow();
    }

    private File getImageFromFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterImages = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(extFilterImages);
        fileChooser.setInitialDirectory(new File("C:\\Users\\Thinkpad\\OneDrive\\Bureau"));
        fileChooser.setTitle("Select an image");

        File selectedImage = fileChooser.showOpenDialog(stage);
        return selectedImage;
    }
//    public void upload(Stage s) {
//
//        s.setTitle("JavaFX App");
//
//        FileChooser fileChooser = new FileChooser();
//
//        uploadFile.setOnAction(e -> {
//            File selectedFile = fileChooser.showOpenDialog(s);
//        });
//
//        VBox vBox = new VBox(uploadFile);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        s.setScene(scene);
//        s.show();

//        Button b;
//
//        uploadFile.setOnAction(e
//                -> {
//            FileChooser file = new FileChooser();
//            file.setTitle("Save Image");
//            //System.out.println(pic.getId()); 
//            file.setInitialDirectory(new File("C:\\Users\\Thinkpad\\OneDrive\\Bureau"));
//            file.showOpenDialog(s);
//            File file1 = file.showSaveDialog(s);
//            System.out.println(file1);
//
//        }
//        );
    @FXML
    public void applyInternship(ActionEvent event) throws SQLException {
        getStage();
        // to do         
        String sql = "INSERT INTO candidats (`name`, `family_name`,`email`) VALUES (?,?,?);";

        PreparedStatement pre = cnx.prepareStatement(sql);

        pre = cnx.prepareStatement(sql);
        pre.setString(1, tfName.getText());
        pre.setString(2, tfFamilyName.getText());
        pre.setString(3, tfEmail.getText());
        pre.execute();

        pre.executeUpdate();
        System.out.println("INSERTION OK!!");
    }

//    public void transferMessage(String.Internship t) {
////        id.setText(t.getId());
////        author.setText(t.getAuthor());
////        publisher.setText(t.getPublisher());
////        id.setEditable(false);
////        isInEditMode = Boolean.TRUE;
//    }
//    
    void sendData(String t) {
        lInternship.setText(t);
    }

}
