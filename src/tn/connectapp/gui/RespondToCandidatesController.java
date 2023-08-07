/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui;

import tn.connectapp.entities.Offer.InternshipsCandidats;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.C;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.connectapp.utils.DBConnect;
import javax.mail.Authenticator;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class RespondToCandidatesController extends Authenticator implements Initializable {

    @FXML
    private Label lName;
    @FXML
    private Label lFamilyName;
    @FXML
    private Label lEmail;
    @FXML
    private Button downloadResume;
    @FXML
    private Button sendMail;
    @FXML
    private TextArea emailContent;
    Connection cnx;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    public String getEmail() throws SQLException {
//        String email = null;
//        cnx = DBConnect.getConnect();
//        Statement statement = cnx.createStatement();
////        String sql = "select * from table where column like '%" + textfield1.getText() + "%'";
//
//        String sql = "SELECT `Email` from user where FirstName like '%" + lName.getText() + "%'";
//
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()) {
//
//            email = rs.getString("email");
//            System.out.println(email);
////            return lastName;
//
//        }
//
//        return email;
//    }
    
    @FXML
    public void sendEmailToRecepient() throws SQLException, Exception {
        String email = lEmail.getText();
        sendMail(email);
        

    }

    public void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "connectevent.noreply@gmail.com";
        //Your gmail password
        String password = "Azerty123,";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Réponse Candidature");
            String htmlCode = "<h1> Candidature Retenue </h1> <br/> <h2><b> </b></h2>";
            message.setContent(htmlCode, "text/html");
            String finalContent = htmlCode + emailContent.getText();
            message.setContent(C, finalContent);
            return message;

        } catch (Exception ex) {
            Logger.getLogger(RespondToCandidatesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void downloadFile(URL url, String outputFileName) throws IOException {
        try (InputStream in = url.openStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }
    boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

    @FXML
    public void downloadResume() throws SQLException, MalformedURLException, IOException, InterruptedException {
        try {

//            }
//            else {
//                System.out.println("this is not a windows machine");
//            }
//            File cv = new File("C:\\Users\\Thinkpad\\Downloads\\CV_Noussayr_DERBEL.pdf");
//
//            FilePermission permission = new FilePermission("C:\\\\Users\\\\Thinkpad\\\\Downloads", "write");
//
            Connection connection = DBConnect.getConnect();
//
            String sql = "SELECT Cv FROM candidats_enternship where name like '%" + lName.getText() + "%'";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Process process;

//                ReadableByteChannel readChannel = Channels.newChannel(new URL("http://127.0.0.1/CV_Noussayr_DERBE.pdf").openStream());
//
//                FileOutputStream fileOS = new FileOutputStream("C:\\\\Users\\\\Thinkpad\\\\Downloads");
//                FileChannel writeChannel = fileOS.getChannel();
//
//                writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
                String path = rs.getString("cv");
//                URL t = new URL("http://127.0.0.1/"+path);
//                URL t = new URL("http://127.0.0.1/CV_Noussayr_DERBEL.pdf");
                System.out.println("the path is" + path);
                String url = "http://127.0.0.1/" + path;
                System.out.println("the URL is " + url);
                String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

                process = Runtime.getRuntime().exec(chromePath + " " + url);
                StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
                Executors.newSingleThreadExecutor().submit(streamGobbler);
                int exitCode = process.waitFor();
                assert exitCode == 0;

//                downloadFile(t, "C:\\Users\\Thinkpad\\Downloads");
            }
//            String homeDirectory = System.getProperty("user.home");
//            if (isWindows) {

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    void sendData(InternshipsCandidats t) {
        lName.setText(t.getName());
        lFamilyName.setText(t.getFamilyName());
        lEmail.setText(t.getEmail());
    }

}
