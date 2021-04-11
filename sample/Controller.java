package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private TextField email;
    @FXML
    private TextField streetNumber;
    @FXML
    private TextField streetName;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;
    @FXML
    private Button submit;





    public void buttonClicked(){
        boolean valid = true;
        Helper.CreateTable(Helper.ConnectToDatabase());

        String user = username.getText();


        if(Helper.validateUsername(user) == false){

            System.out.println("The username is not valid");
            valid = false;
        }else{

            System.out.println("The username is valid");
        }

        String pass = password.getText();
        if(Helper.validatePassword(pass) == false){

            System.out.println("The password is not valid");
            valid = false;
        }else{

            System.out.println("The password is valid");
        }

        String confirmPassword = confirm.getText();


        if(Helper.validateConfirm(pass, confirmPassword)== false){

            System.out.println("The passwords do not match");
            valid = false;
        }else{

            System.out.println("The passwords do indeed match");
        }

        String mail = email.getText();
        if(Helper.validateEmail(mail) == false){

            System.out.println("The email is not valid");
            valid = false;
        }else{

            System.out.println("The email is valid");
        }
        String number = streetNumber.getText();
        if(Helper.validateStreetNumber(number) == false){

            System.out.println("The street number is not valid");
            valid = false;
        }else{

            System.out.println("The street number is valid");
        }
        String street_name = streetName.getText();
        if(Helper.validateStreetName(street_name) == false){

            System.out.println("The street name is not valid");
            valid = false;
        }else{

            System.out.println("The street name is valid");
        }
        String postal_code = postalCode.getText();
        if(Helper.validatePostalCode(postal_code) == false){

            System.out.println("The postal code is not valid");
            valid = false;
        }else{

            System.out.println("The postal code is valid");
        }
        String City = city.getText();
        if(Helper.validateCity(City) == false){

            System.out.println("The city is not valid");
            valid = false;
        }else{

            System.out.println("The city is valid");
        }
        if(valid == true){

            submit.setDisable(true);
            Helper.InsertRecord(Helper.ConnectToDatabase(), user, mail, pass, number, street_name,postal_code,City);
            System.out.println("Your input was saved into the database.");

        }else{
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("Register Failed");
            dialog.setContentText("Invalid user input");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(480, 320);

            dialog.showAndWait();
        }

    }
    public void newButtonClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("test.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 400);
            Stage stage = new Stage();
            stage.setTitle("User Information");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

}


