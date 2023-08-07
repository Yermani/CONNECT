/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.connectapp.entities.Offer.Internships;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.connectapp.utils.DBConnect;
import tn.connectapp.entities.Offer.JobsCandidats;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class JobCandidatsController implements Initializable {

    private ObservableList<JobsCandidats> IL;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships internship = null;

    ObservableList<Internships> InternshipsList = FXCollections.observableArrayList();
    @FXML
    private BorderPane bp;
    @FXML
    private TableView<JobsCandidats> JobCandidatesTable;
    @FXML
    private TableColumn<JobsCandidats, String> tcName;
    @FXML
    private TableColumn<JobsCandidats, String> tcFamilyName;
    @FXML
    private TableColumn<JobsCandidats, String> tcEmail;
    @FXML
    private TableColumn<JobsCandidats, String> tcCv;

        private Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void laodTable() {
        ArrayList<JobsCandidats> list = new ArrayList<>();

        connection = DBConnect.getConnect();

//        list.add(new Internships("ref", "aa", "ff", "zzz", "ee", "ff", "qqq", "gg", "zz", "zz"));
        try {
            String sql = "SELECT name, family_name, email, Cv FROM candidats_job;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String familyName = rs.getString("family_name");
                String email = rs.getString("email");
                String CV = rs.getString("CV");
                list.add(new JobsCandidats(name, familyName, email, CV));

                tcName.setCellValueFactory(z -> new SimpleStringProperty(name));
                tcFamilyName.setCellValueFactory(z -> new SimpleStringProperty(familyName));
                tcEmail.setCellValueFactory(z -> new SimpleStringProperty(email));
                tcCv.setCellValueFactory(z -> new SimpleStringProperty(CV));

            }

            try {
                IL = FXCollections.observableArrayList(list);

                JobCandidatesTable.setItems(IL);

            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);

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
