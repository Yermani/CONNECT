/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

//import com.jfoenix.controls.TextField;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.Button;
import tn.connectapp.entities.Offer.Internships;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.time.LocalDate;
//import java.util.Observable;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.effect.BlurType;
//import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.connectapp.utils.commun.MyConnection;
//import jdk.nashorn.internal.objects.annotations.Property;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class CMEntrepriseController implements Initializable {

    private ObservableList<Internships> IL;

    @FXML
    private TableView<Internships> InternshipsTable;
    @FXML
    private TableColumn<Internships, String> ref;
    @FXML
    private TableColumn<Internships, String> company;
    @FXML
    private TableColumn<Internships, String> field;
    @FXML
    private TableColumn<Internships, String> description;
    @FXML
    private TableColumn<Internships, String> title;
    @FXML
    private TableColumn<Internships, String> start_date;
    @FXML
    private TableColumn<Internships, String> end_date;
    @FXML
    private TableColumn<Internships, String> type;
    @FXML
    private TableColumn<Internships, String> supervisor;
    @FXML
    private TableColumn<Internships, String> status;
    public TextField filtredfield;

    @FXML
    private BorderPane bp;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships internship = null;
    //ObservableList<Internships> InternshipsList = null;
    @FXML
    private Button addJob;
    @FXML
    private Button internships;
    @FXML
    private Button jobs;
    @FXML
    private Button addInternship;
    @FXML
    private Button internshipsCandidates;
    @FXML
    private Button jobCandidates;
    private Connection cnx;

    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        getInternshipList();
//        showInternships();
//        loadData();

        loadData();
//        search();
//        getInternshipList();
    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleMouseEvent(MouseEvent event) {
        // handling click on items 
        Internships t = InternshipsTable.getSelectionModel().getSelectedItem();
        System.out.println(t);
//        TableViewSelectionModel selectionModel = InternshipsTable.getSelectionModel();
//        ObservableList selectedCells = selectionModel.getSelectedCells();
//        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//        Object val = tablePosition.getTableColumn().getCellData(0);
//        System.out.println("Selected value IS :" + val);

//        FXMLLoader loader = new FXMLLoader();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDeleteInternship.fxml"));
            Parent parent = loader.load();

//            Parent parent = FXMLLoader.load(getClass().getResource("UpdateDeleteInternship.fxml"));
            UpdateDeleteInternshipController controller = (UpdateDeleteInternshipController) loader.getController();
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
//    String REF = handleMouseEvent(event);

    @FXML
    private void getAddView(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddInternship.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CMEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void getAddJobView(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddJob.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CMEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void getInternshipsCandidates(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        try {
            Pane view = object.load(getClass().getResource("InternshipCandidats.fxml"));
            bp.setBottom(view);
        } catch (IOException ex) {
            
            Logger.getLogger(CMEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void getJobsCandidates(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        try {
            Pane view = object.load(getClass().getResource("JobCandidats.fxml"));
            bp.setBottom(view);
        } catch (IOException ex) {
            
            Logger.getLogger(CMEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    private void laodTable() {
        ArrayList<Internships> list = new ArrayList<>();

         cnx = MyConnection.getInstance().getCnx();

//        list.add(new Internships("ref", "aa", "ff", "zzz", "ee", "ff", "qqq", "gg", "zz", "zz"));
        try {
            String sql = "SELECT ref, company, field, title, description, start_date, end_date, type, supervisor, status FROM Internship";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String r = rs.getString("ref");
                String c = rs.getString("company");
                String f = rs.getString("field");
                String t = rs.getString("title");
                String d = rs.getString("description");
                String sD = rs.getString("start_date");
                String eD = rs.getString("end_date");
                String ty = rs.getString("type");
                String s = rs.getString("supervisor");
                String st = rs.getString("status");
                // TO DO
              //  list.add(new Internships(r, c, f, t, d, sD, eD, ty, s, st));
                System.out.println("datee ===" + sD);
                start_date.setCellValueFactory(z -> new SimpleStringProperty(sD));
                ref.setCellValueFactory(z -> new SimpleStringProperty(r));
                end_date.setCellValueFactory(z -> new SimpleStringProperty(eD));

            }

            try {
                IL = FXCollections.observableArrayList(list);

                InternshipsTable.setItems(IL);

            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);

    }

    private void loadData() {
        ref.setCellValueFactory(new PropertyValueFactory<Internships, String>("ref"));
        company.setCellValueFactory(new PropertyValueFactory<Internships, String>("company"));
        field.setCellValueFactory(new PropertyValueFactory<Internships, String>("field"));
        title.setCellValueFactory(new PropertyValueFactory<Internships, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<Internships, String>("description"));
        start_date.setCellValueFactory(new PropertyValueFactory<Internships, String>("start_date"));
        end_date.setCellValueFactory(new PropertyValueFactory<Internships, String>("end_date"));
        type.setCellValueFactory(new PropertyValueFactory<Internships, String>("type"));
        supervisor.setCellValueFactory(new PropertyValueFactory<Internships, String>("supervisor"));
        status.setCellValueFactory(new PropertyValueFactory<Internships, String>("status"));
        laodTable();

    }

    @FXML
    private void getJobsList(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        try {
            Pane view = object.load(getClass().getResource("JobOffers.fxml"));
            bp.setBottom(view);
        } catch (IOException ex) {
            
            Logger.getLogger(CMEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getInternshipsList(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        try {
            Pane view = object.load(getClass().getResource("CMEntreprise.fxml"));
            bp.setBottom(view);
        } catch (IOException ex) {
//            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
