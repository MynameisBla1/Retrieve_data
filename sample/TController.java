package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class TController implements Initializable {
    @FXML
    private TableView<MemberInfo> tableView;
    @FXML
    private TableColumn<MemberInfo,String> userNameCol;
    @FXML
    private TableColumn <MemberInfo,String>emailNameCol;
    @FXML
    private TableColumn <MemberInfo,String>passwordCol;
    @FXML
    private TableColumn <MemberInfo,String>streetNumCol;
    @FXML
    private TableColumn <MemberInfo,String>streetNameCol;
    @FXML
    private TableColumn <MemberInfo,String>postalCodeCol;
    @FXML
    private TableColumn <MemberInfo,String> cityCol;

    public static ObservableList<MemberInfo> observableList = FXCollections.observableArrayList();



    //NOTE: This needs to be overrriden.
    @Override
    public void initialize(URL url, ResourceBundle rb){

        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailNameCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        streetNumCol.setCellValueFactory(new PropertyValueFactory<>("streetNumber"));
        streetNameCol.setCellValueFactory(new PropertyValueFactory<>("streetName"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        if(Helper.retrieveData(Helper.ConnectToDatabase())){

            tableView.setItems(observableList);


        }





    }


}
