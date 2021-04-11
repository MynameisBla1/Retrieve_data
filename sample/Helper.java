package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    //create the database file
    private ObservableList<MemberInfo> data;
    public static Connection ConnectToDatabase(){
        try{

            Connection conn =
                    DriverManager.getConnection("JDBC:sqlite:testjava.db");
            return conn;

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
            return null;

        }
    }
    //create the table
    public static boolean CreateTable(Connection conn){
        try{

            Statement query = conn.createStatement();

            query.execute("CREATE TABLE IF NOT EXISTS users (userName TEXT, email TEXT,password TEXT,streetNumber TEXT, streetName TEXT,postalCode TEXT,city TEXT)");
            return true;

        }catch(SQLException e){
            System.out.println("Error: " +  e.getMessage());
            return false;
        }
    }
    //save valid user input into the database
    public static boolean InsertRecord(Connection conn, String userName, String email, String password, String streetNumber, String streetName,
                                       String postalCode, String city){

        try{

            Statement query = conn.createStatement();
            query.execute("INSERT INTO users VALUES('"+userName+"','"+email+"','"+password+"','"+streetNumber+"','"+streetName+"','"+postalCode+"','"+city+"')");
            return true;

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
/*
    public static void RetrieveData(Connection conn){
        try{
            ObservableList<MemberInfo>data = FXCollections.observableArrayList();
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                data.add(new MemberInfo(
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("streetNumber"),
                        rs.getString("streetName"),
                        rs.getString("postalCode"),
                        rs.getString("city")
                ));
                //System.out.println(rs.getString("userName"));
            }
            userNameCol.setCellValueFactory(new PropertyValueFactory("name"));
            emailNameCol.setCellValueFactory(new PropertyValueFactory("email"));
            passwordCol.setCellValueFactory(new PropertyValueFactory("password"));
            streetNumCol.setCellValueFactory(new PropertyValueFactory("streetNumber"));
            streetNameCol.setCellValueFactory(new PropertyValueFactory("streetName"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
            cityCol.setCellValueFactory(new PropertyValueFactory("city"));

            tableView.setItems(data);
        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }
        }

*/

    public static boolean retrieveData(Connection conn){
        try{
            ResultSet resultSet =
                    conn.createStatement().executeQuery("SELECT * FROM users");

            while(resultSet.next()){

               TController.observableList.add(new MemberInfo(resultSet.getString("userName"),
                       resultSet.getString("email"), resultSet.getString("password"),
                       resultSet.getString("streetNumber"), resultSet.getString("streetName"),
                       resultSet.getString("postalCode"), resultSet.getString("city")));
            }

            return true;

        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;

        }



    }

    public static boolean validateUsername(String username){

        String regex = "^[A-Za-z]\\w{5,29}$";


        Pattern p = Pattern.compile(regex);

        if(username == null){

            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();

    }
    public static boolean validatePassword(String password){


        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        Pattern p = Pattern.compile(regex);
        if(password == null){

            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();


    }

    public static boolean validateConfirm(String a, String b){

        if(a.equals(b)){

            return true;
        }else{
            return false;
        }

    }

    public static boolean validateEmail(String email){

        String regex = "^(.+)@(.+)$";

        Pattern p = Pattern.compile(regex);

        if(email == null){

            return false;
        }
        Matcher m = p.matcher(email);

        return m.matches();

    }
    public static boolean validateStreetNumber(String streetNumber){

        String regex = "^[\\d+]{3}+$";

        Pattern p = Pattern.compile(regex);

        if( streetNumber== null){

            return false;
        }
        Matcher m = p.matcher(streetNumber);

        return m.matches();

    }

    public static boolean validateStreetName(String streetName){

        String regex = "^[-a-zA-Z-]+$";

        Pattern p = Pattern.compile(regex);

        if( streetName== null){

            return false;
        }
        Matcher m = p.matcher(streetName);

        return m.matches();

    }
    public static boolean validatePostalCode(String postalCode){

        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

        Pattern p = Pattern.compile(regex);

        if( postalCode== null){

            return false;
        }
        Matcher m = p.matcher(postalCode);

        return m.matches();

    }
    public static boolean validateCity(String city){

        String regex = "^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$";

        Pattern p = Pattern.compile(regex);

        if( city== null){

            return false;
        }
        Matcher m = p.matcher(city);

        return m.matches();

    }

}
