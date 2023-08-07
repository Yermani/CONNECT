/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.connectapp.entities.offer.Work;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class JobOffersController implements Initializable {

    private ObservableList<Work> IL;

    @FXML
    private TableView<Work> JobsTable;

    @FXML
    private TableColumn<Work, String> tcRef;

    @FXML
    private TableColumn<Work, String> tcCompany;

    @FXML
    private TableColumn<Work, String> tcField;

    @FXML
    private TableColumn<Work, String> tcTitle;

    @FXML
    private TableColumn<Work, String> tcDescription;

    @FXML
    private TableColumn<Work, String> tcStartDate;
    @FXML
    private TableColumn<Work, String> tcAgreementType;

    @FXML
    private TableColumn<Work, String> tcPosition;

    @FXML
    private TableColumn<Work, String> tcStatus;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Work internship = null;

        private Connection cnx;

    ObservableList<Work> JobsList = FXCollections.observableArrayList();
    @FXML
    private BorderPane bp;

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
        ArrayList<Work> list = new ArrayList<>();

        connection = DBConnect.getConnect();

//        list.add(new Internships("ref", "aa", "ff", "zzz", "ee", "ff", "qqq", "gg", "zz", "zz"));
        try {
            String sql = "SELECT ref, company, field, title, description, start_date, agreementType, position, status FROM work;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String r = rs.getString("ref");
                String c = rs.getString("company");
                String f = rs.getString("field");
                String t = rs.getString("title");
                String d = rs.getString("description");
                String sD = rs.getString("start_date");
                String at = rs.getString("agreementType");
                String p = rs.getString("Position");
                String st = rs.getString("status");
// TO DO                list.add(new Work(r, c, f, t, d, sD, at, p, st));

                tcRef.setCellValueFactory(z -> new SimpleStringProperty(r));
                tcCompany.setCellValueFactory(z -> new SimpleStringProperty(c));
                tcField.setCellValueFactory(z -> new SimpleStringProperty(f));
                tcTitle.setCellValueFactory(z -> new SimpleStringProperty(t));
                tcDescription.setCellValueFactory(z -> new SimpleStringProperty(d));
                tcStartDate.setCellValueFactory(z -> new SimpleStringProperty(sD));
                tcAgreementType.setCellValueFactory(z -> new SimpleStringProperty(at));
                tcPosition.setCellValueFactory(z -> new SimpleStringProperty(p));
                tcStatus.setCellValueFactory(z -> new SimpleStringProperty(st));

            }

            try {
                IL = FXCollections.observableArrayList(list);

                JobsTable.setItems(IL);

            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);

    }

    private void loadData() {

//        tcRef.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("ref"));
//        tcCompany.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("company"));
//        tcField.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("field"));
//        tcTitle.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("title"));
//        tcDescription.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("description"));
//        tcStartDate.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("start_date"));
//        tcAgreementType.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("agreementType"));
//        tcPosition.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("position"));
//        tcStatus.setCellValueFactory(new PropertyValueFactory<tn.connectapp.entities.Offer.Work, String>("status"));
        laodTable();

    }

    @FXML
    public void handleMouseEvent(MouseEvent event) {
        // handling click on items 
        Work t = JobsTable.getSelectionModel().getSelectedItem();
        System.out.println(t);
//        TableViewSelectionModel selectionModel = InternshipsTable.getSelectionModel();
//        ObservableList selectedCells = selectionModel.getSelectedCells();
//        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//        Object val = tablePosition.getTableColumn().getCellData(0);
//        System.out.println("Selected value IS :" + val);

//        FXMLLoader loader = new FXMLLoader();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDeleteJob.fxml"));
            Parent parent = loader.load();

//            Parent parent = FXMLLoader.load(getClass().getResource("UpdateDeleteInternship.fxml"));
            UpdateDeleteJobController controller = (UpdateDeleteJobController) loader.getController();
            try {
// TO dO                controller.sendData(t);
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
}
