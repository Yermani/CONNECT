/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

//import com.sun.deploy.util.StringUtils;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.connectapp.entities.Offer.Internships;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
//import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.event.Event;
import javafx.scene.control.TextField;

//import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
//import pfa.PFA;
import tn.connectapp.utils.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class StudentController implements Initializable {

    Connection cnx = DBConnect.getConnect();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships internship = null;

    ObservableList<String> InternshipsList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> lvInternships;
    @FXML
    private TextField filtredfield;
    @FXML
    private Pane btnRefresh;
    @FXML
    private Button internships;
    @FXML
    private Button jobs;

    public static String title;
    @FXML
    private BorderPane bp;
    @FXML
    private Button refresh;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleMouseEvent(MouseEvent event) {
        // handling click on items 
        String t = lvInternships.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InternshipInfos.fxml"));
            Parent parent = loader.load();

//            Parent parent = FXMLLoader.load(getClass().getResource("UpdateDeleteInternship.fxml"));
            InternshipInfosController controller = (InternshipInfosController) loader.getController();
            try {
                controller.sendData(t);
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

    /**
     *
     * @param cnx
     * @param InternshipsList
     */
    @FXML
    public void loadData() throws SQLException {
        ArrayList list = new ArrayList<>();
        Connection connection = DBConnect.getConnect();
        String sql = "select title from internship";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String t = rs.getString("title");
            list.add(t);

        }
        InternshipsList = FXCollections.observableArrayList(list);
        lvInternships.setItems(InternshipsList);

    }

    @FXML
    private void getJobsList(ActionEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("StudentJobOffers.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CMEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
