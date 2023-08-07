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
import tn.connectapp.entities.offer.Work;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class UpdateDeleteJobController implements Initializable {

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
    private TextField tfPosition;

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
            Logger.getLogger(UpdateDeleteJobController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() throws SQLException {
        cnx = DBConnect.getConnect();
        Statement statement = cnx.createStatement();
        String reference = lRef.getText();
        String sql = "SELECT company, field, title, description,  position, status from work where ref = " + reference;
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
                String position = rs.getString("position");
                String status = rs.getString("status");
                tfCompany.setText(company);
                tfField.setText(field);
                tfTitle.setText(title);
                taDescription.setText(description);
                tfStatus.setText(status);
                dpStartDate.setValue(null);
                tfPosition.setText(position);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InternshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //change everything in the FXML file

    }

    @FXML
    private void update(ActionEvent event) {
        try {
            String update = "UPDATE work SET Company=?, field=?, title=?, description=?, start_date=?, position=?, status=? WHERE REF=" + lRef.getText() + ";";
            System.out.println(update);
            PreparedStatement stmt = cnx.prepareStatement(update);
            stmt.setString(1, tfCompany.getText());
            stmt.setString(2, tfField.getText());
            stmt.setString(3, tfTitle.getText());
            stmt.setString(4, taDescription.getText());
            stmt.setString(5, String.valueOf(dpStartDate.getValue()));
            stmt.setString(6, tfPosition.getText());
            stmt.setString(7, tfStatus.getText());
            stmt.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void delete() throws SQLException {
        String delete = "UPDATE work SET status = HEXP WHERE REF=" + lRef.getText() + ";";
        System.out.println(delete);
        System.out.println("l ref get text" + lRef.getText());
        System.out.println("l ref " + lRef);
        PreparedStatement stmt = cnx.prepareStatement(delete);
//            stmt.setString(1, "HEXP");
        stmt.executeUpdate();
    }

    void sendData(Work t) {
//  TO DO      lRef.setText(t.getREF());
 //  TO DO    System.out.println("t.get ref : " + t.getREF());
        tfCompany.setText(t.getCompany());
        tfField.setText(t.getField());
        tfTitle.setText(t.getTitle());
        taDescription.setText(t.getDescription());
        dpStartDate.setValue(null);
        tfPosition.setText(t.getPosition());
        tfStatus.setText(t.getStatus());
        // todo 
        // create a constructor on internships that contains those attributes

    }

}
