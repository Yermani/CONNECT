/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.net.URL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.connectapp.entities.Offer.Internships;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class UpdateDeleteInternshipController implements Initializable {

    Connection cnx = DBConnect.getConnect();

    @FXML
    private Label lRef;
    @FXML
    private TextField tfCompany;
    @FXML
    private TextField tfField;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button postuler;
    @FXML
    private DatePicker dpStartDate = new DatePicker();
    @FXML
    private DatePicker dpEndDate = new DatePicker();
    @FXML
    private TextField tfSupervisor;
    @FXML
    private TextField tfStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteInternshipController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() throws SQLException {
        cnx = DBConnect.getConnect();
        Statement statement = cnx.createStatement();
//        String reference = lRef.getText();
        String sql = "SELECT company, field, title, description,  supervisor, status from internship where ref like '%" + lRef.getText() + "%'";

//        String sql = "SELECT company, field, title, description,  supervisor, status from internship where ref = " + reference;
        System.out.println("get lref text ::  aaaa" + lRef.getText());
        System.out.println("AAAAAAAAAAAAAAAAAA" + sql);
//        System.out.println(CMEntrepriseController.REF);
//        lRef.setText(CMEntrepriseController.REF);
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);

        try {
            while (rs.next()) {
                String company = rs.getString("company");
                String field = rs.getString("field");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String supervisor = rs.getString("supervisor");
                String status = rs.getString("status");
                tfCompany.setText(company);
                tfField.setText(field);
                tfTitle.setText(title);
                taDescription.setText(description);
                tfStatus.setText(status);
                dpEndDate.setValue(null);
                dpStartDate.setValue(null);
                tfSupervisor.setText(supervisor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InternshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //change everything in the FXML file

    }

    @FXML
    private void update(ActionEvent event) {
        try {
            String update = "UPDATE internship SET Company=?, field=?, title=?, description=?, start_date=?, end_date=?, supervisor=?, status=? WHERE REF like '%" + lRef.getText() + "%'";

//            String update = "UPDATE internship SET Company=?, field=?, title=?, description=?, start_date=?, end_date=?, supervisor=?, status=? WHERE REF=" + lRef.getText() + ";";
            System.out.println(update);
            PreparedStatement stmt = cnx.prepareStatement(update);
            stmt.setString(1, tfCompany.getText());
            stmt.setString(2, tfField.getText());
            stmt.setString(3, tfTitle.getText());
            stmt.setString(4, taDescription.getText());
            stmt.setString(5, String.valueOf(dpStartDate.getValue()));
            stmt.setString(6, String.valueOf(dpEndDate.getValue()));
            stmt.setString(7, tfSupervisor.getText());
            stmt.setString(8, tfStatus.getText());
            stmt.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void delete() throws SQLException {

        String delete = "UPDATE internship SET status = HEXP WHERE REFlike '%" + lRef.getText() + "%'";

//        String delete = "UPDATE internship SET status = HEXP WHERE REF=" + lRef.getText() + ";";
        System.out.println(delete);
        System.out.println("l ref get text" + lRef.getText());
        System.out.println("l ref " + lRef);
        PreparedStatement stmt = cnx.prepareStatement(delete);
//            stmt.setString(1, "HEXP");
        stmt.executeUpdate();
    }

    void sendData(Internships t) {
        lRef.setText(t.getREF());
        System.out.println("t.get ref : " + t.getREF());
/* To DO         tfCompany.setText(t.getCompany());
        tfField.setText(t.getField());
        tfTitle.setText(t.getTitle());
        taDescription.setText(t.getDescription());
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        tfSupervisor.setText(t.getSupervisor());
        tfStatus.setText(t.getStatus());*/
        // todo 
        // create a constructor on internships that contains those attributes

    }

}
