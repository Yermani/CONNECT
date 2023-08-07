/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.connectapp.utils.DBConnect;
import tn.connectapp.entities.Offer.InternshipsCandidats;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class InternshipCandidatsController implements Initializable {

    private ObservableList<InternshipsCandidats> IL;

    @FXML
    private TableView<InternshipsCandidats> InternshipsCandidatesTable;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    InternshipsCandidats internship = null;

    @FXML
    private BorderPane bp;
    @FXML
    private TableColumn<InternshipsCandidats, String> tcName;
    @FXML
    private TableColumn<InternshipsCandidats, String> tcFamilyName;
    @FXML
    private TableColumn<InternshipsCandidats, String> tcEmail;
    @FXML
    private TableColumn<InternshipsCandidats, String> tcCv;

        private Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        laodTable();
    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void laodTable() {
        ArrayList<InternshipsCandidats> list = new ArrayList<>();

        connection = DBConnect.getConnect();

//        list.add(new Internships("ref", "aa", "ff", "zzz", "ee", "ff", "qqq", "gg", "zz", "zz"));
        try {
            String sql = "SELECT name, family_name, email, Cv FROM candidats_enternship;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String familyName = rs.getString("family_name");
                String email = rs.getString("email");
                String CV = rs.getString("CV");
                list.add(new InternshipsCandidats(name, familyName, email, CV));

                tcName.setCellValueFactory(z -> new SimpleStringProperty(name));
                tcFamilyName.setCellValueFactory(z -> new SimpleStringProperty(familyName));
                tcEmail.setCellValueFactory(z -> new SimpleStringProperty(email));
                tcCv.setCellValueFactory(z -> new SimpleStringProperty(CV));

            }

            try {
                IL = FXCollections.observableArrayList(list);

                InternshipsCandidatesTable.setItems(IL);

            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);

    }
    
    
    @FXML
    public void handleMouseEvent(MouseEvent event) {
        // handling click on items
        InternshipsCandidats t = InternshipsCandidatesTable.getSelectionModel().getSelectedItem();
        System.out.println(t);
//        TableViewSelectionModel selectionModel = InternshipsTable.getSelectionModel();
//        ObservableList selectedCells = selectionModel.getSelectedCells();
//        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//        Object val = tablePosition.getTableColumn().getCellData(0);
//        System.out.println("Selected value IS :" + val);

//        FXMLLoader loader = new FXMLLoader();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RespondToCandidates.fxml"));
            Parent parent = loader.load();

//            Parent parent = FXMLLoader.load(getClass().getResource("UpdateDeleteInternship.fxml"));
            RespondToCandidatesController controller = (RespondToCandidatesController) loader.getController();
            try {
                controller.sendData(t);
                System.out.println(t);

            } catch (Exception e) {
                System.out.println(e);
            }

            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
//            System.out.println("this is the second title " + REF);

        } catch (IOException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        };

    }
//    @FXML
//    public void handleMouseEvent(MouseEvent event) {
//        // handling click on items 
//        InternshipsCandidats t = JobsTable.getSelectionModel().getSelectedItem();
//        System.out.println(t);
////        TableViewSelectionModel selectionModel = InternshipsTable.getSelectionModel();
////        ObservableList selectedCells = selectionModel.getSelectedCells();
////        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
////        Object val = tablePosition.getTableColumn().getCellData(0);
////        System.out.println("Selected value IS :" + val);
//
////        FXMLLoader loader = new FXMLLoader();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDeleteJob.fxml"));
//            Parent parent = loader.load();
//
////            Parent parent = FXMLLoader.load(getClass().getResource("UpdateDeleteInternship.fxml"));
//            UpdateDeleteJobController controller = (UpdateDeleteJobController) loader.getController();
//            try {
//                controller.sendData(t);
//                System.out.println(t);
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            
//
//            Scene scene = new Scene(parent);
//
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
////            System.out.println("this is the second title " + REF);
//
//        } catch (IOException ex) {
//            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
//        };
//
//    }
}
