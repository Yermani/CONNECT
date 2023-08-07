/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class InternshipInfosController implements Initializable {

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
            Logger.getLogger(InternshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InternshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Stage getStage() {
        return (Stage) uploadFile.getScene().getWindow();
    }

    private String getFileName() throws IOException, SQLException {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
        System.out.println(f.getAbsoluteFile());

        String out = f.getAbsolutePath();
        String p = f.getAbsolutePath();
        Path path = Paths.get(out);
        Path filename = path.getFileName();
        String fileName = filename.toString();
//        }
        return fileName;

    }

    private String getFilePath() throws IOException, SQLException {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
//        System.out.println(f.getAbsoluteFile());

        String p = f.getAbsolutePath();
//        }
        return p;

    }

    @FXML
    private void uploadResume() throws Exception {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
        System.out.println(f.getAbsoluteFile());

        String out = f.getAbsolutePath();
        String p = f.getAbsolutePath();
        Path path = Paths.get(out);
        Path filename = path.getFileName();
        String fileName = filename.toString();
//        System.out.println(f.getAbsoluteFile());

        System.out.println("Start");
        FTPUploader ftpUploader = new FTPUploader("172.24.140.193", "noussayr", "15900*");
        //FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
        // files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
        //ftpUploader.uploadFile("C:\\DEV\\ConnectProjectMaster - Copy\\src\\tn\\connectapp\\media\\051232User.png", "image.png", "/");

        ftpUploader.uploadFile(p, fileName, "/");
        ftpUploader.disconnect();
        System.out.println("Done");
    }

    @FXML
    private String uploadFile() throws IOException, SQLException {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
        System.out.println(f.getAbsoluteFile());
        byte[] file = getByteArrayFromFile(f);

        System.out.println("alles gut");
        return null;

    }

    private byte[] getByteArrayFromFile(File f) throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(999999999);
        InputStream in = new FileInputStream(f);
        byte[] buffer = new byte[999999999];
        int read = -1;
        while ((read = in.read(buffer)) > 0) {
            baos.write(buffer);
        }
        in.close();

        return baos.toByteArray();
    }

    @FXML
    public void applyInternship(ActionEvent event) throws SQLException, IOException, Exception {
//        try {
//            uploadResume();
//        } catch (Exception ex) {
//            Logger.getLogger(InternshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //to 
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
        System.out.println(f.getAbsoluteFile());

        String out = f.getAbsolutePath();
        String p = f.getAbsolutePath();
        Path path = Paths.get(out);
        Path filename = path.getFileName();
        String fileName = filename.toString();
//        System.out.println(f.getAbsoluteFile());

        System.out.println("Start");
        FTPUploader ftpUploader = new FTPUploader("172.31.21.88", "noussayr", "15900*");
        //FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
        // files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
        //ftpUploader.uploadFile("C:\\DEV\\ConnectProjectMaster - Copy\\src\\tn\\connectapp\\media\\051232User.png", "image.png", "/");

        ftpUploader.uploadFile(p, fileName, "/");
        ftpUploader.disconnect();
        System.out.println("Done");

        String sql = "INSERT INTO candidats_enternship (`name`, `family_name`,`email`,`cv`) VALUES (?,?,?,?);";

        PreparedStatement pre = cnx.prepareStatement(sql);

        pre = cnx.prepareStatement(sql);
        String email = getEmail();
        String lastName = getLastName();
        pre.setString(1, tfName.getText());
        pre.setString(2, lastName);
        pre.setString(3, email);
        pre.setString(4, fileName);
        pre.execute();
//        pre.executeUpdate();
        System.out.println("INSERTION OK!!");
    }

    public String getEmail() throws SQLException {
        String email = null;
        cnx = DBConnect.getConnect();
        Statement statement = cnx.createStatement();
//        String sql = "select * from table where column like '%" + textfield1.getText() + "%'";

        String sql = "SELECT `Email` from user where FirstName like '%" + tfName.getText() + "%'";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {

            email = rs.getString("email");
            System.out.println(email);
//            return lastName;

        }

        return email;
    }

    public String getLastName() throws SQLException {
        String lastName = null;
        cnx = DBConnect.getConnect();
        Statement statement = cnx.createStatement();
//        String sql = "select * from table where column like '%" + textfield1.getText() + "%'";

        String sql = "SELECT `LastName` from user where FirstName like '%" + tfName.getText() + "%'";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {

            lastName = rs.getString("LastName");

        }

        return lastName;
    }

    void sendData(String t) {
        lInternship.setText(t);
    }

}
