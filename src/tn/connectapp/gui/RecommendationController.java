/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.connectapp.entities.claim.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import tn.connectapp.utils.commun.MyConnection;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class RecommendationController implements Initializable {

      
    @FXML
    private TableView<Reclamation> ClaimsTable;
   @FXML
    private TableColumn<Reclamation, String> id= new TableColumn<>("id");
    @FXML
    private TableColumn<Reclamation, String> nom= new TableColumn<>("nom");
    @FXML
    private TableColumn<Reclamation, String> prenom= new TableColumn<>("prenom");
    @FXML
    private TableColumn<Reclamation, String> titre= new TableColumn<>("titre") ;
    @FXML
    private TableColumn<Reclamation, String> desc =new TableColumn<>("desc");
    @FXML
    private TableColumn<Reclamation, String> answer =new TableColumn<>("answer");
    @FXML
    private TableColumn<Reclamation, Date> date =new TableColumn<>("date");
    @FXML
    private TableColumn<Reclamation, String> status = new TableColumn<>("status");
    @FXML
    private TableColumn<Reclamation, String> actions =new TableColumn<>("actions");
    
    
    
  
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation reclamation = null ;
    
     ObservableList<Reclamation>  ReclamationList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    
   
    
    private void refreshTable() {
        try {
            ReclamationList.clear();
            
            query = "SELECT * FROM Reclamations WHERE type='recommendation' AND status!= 'done'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
           while(resultSet.next()){
                ReclamationList.add(new Reclamation (
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getString("titre"),
                resultSet.getString("date_declaration"),
                resultSet.getString("description"),
                resultSet.getString("answer"),
                resultSet.getString("status")));
                
                 ClaimsTable.setItems(ReclamationList);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RecommendationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDate() {
        
        connection = MyConnection.getInstance().getCnx();
        refreshTable();
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_declaration"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        //add cell of button edit 
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    FontAwesomeIconView EyeIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        

                        EyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#B0E0E6;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#FDF1B8;"
                        );
                        EyeIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                          
                                reclamation = ClaimsTable.getSelectionModel().getSelectedItem();
                                 reclamation = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("readOnly.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ReadOnlyController readonlycontroller = loader.getController();
                            //repondrecontroller.setUpdate(true);
                             LocalDate localDate = LocalDate.parse(reclamation.getDate_declaration());
                            readonlycontroller.ReadTextField(reclamation.getId(), "recommendation",reclamation.getNom(), reclamation.getPrenom(), reclamation.getTitre(), localDate,reclamation.getDescription(), reclamation.getAnswer(), reclamation.getStatus());   
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                              
                          

                        });}
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             reclamation = ClaimsTable.getSelectionModel().getSelectedItem();
                                 reclamation = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("repondre.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            RepondreController repondrecontroller = loader.getController();
                            repondrecontroller.setUpdate(true);
                            LocalDate localDate = LocalDate.parse(reclamation.getDate_declaration());
                            repondrecontroller.setTextField(reclamation.getId(), "opinion",reclamation.getNom(), reclamation.getPrenom(), reclamation.getTitre(), localDate, reclamation.getDescription(), reclamation.getAnswer());   
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, EyeIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(EyeIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                

            };

            return cell;
        };
         actions.setCellFactory(cellFoctory);
         ClaimsTable.setItems(ReclamationList);
         
         
    }
}
