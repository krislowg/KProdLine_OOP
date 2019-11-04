package sample;


import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * Semester: Fall 2019.
 * 9/28/2019
 * ProductionLine
 * Program that helps a media player production facility keep track of their produced products.
 * @author Kristy Low
 *
 */

/**
 * The controller class defines the UI elements and determines the functions to user actions
 */
public class ProductionTabsController {
  //ComboBox values from 1-10
  ObservableList<String> produceNum = FXCollections
      .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

  @FXML
  private Tab tab_pline;//Production Line Tab

  @FXML
  private GridPane label_manufacturer; //Production Line GridPane

  @FXML
  private Label label_pname;//Product name label

  @FXML
  private Label label_manuf;//Manufacturer label

  @FXML
  private Label label_IType;//Item type label

  @FXML
  private TextField textfield_pname;//Product name textfield

  @FXML
  private TextField textfield_manuf;//Manufacturer text field

  @FXML
  private ChoiceBox<?> choicebox_IType;//Item Type choice box

  @FXML
  private Button btn_addproduct;//Add Product Button

  @FXML
  private Label label_confaddprod;//Add product confirmation

  @FXML
  private Label lbl_ExistingP;//Existing Product label

  @FXML
  private TableView<Product> tbview_ExistingP;//Existing Product table view

  @FXML
  private TableColumn<?, ?> col_PName;

  @FXML
  private TableColumn<?, ?> col_Manufact;

  @FXML
  private TableColumn<?, ?> col_IType;

  @FXML
  private Tab tab_produce;//Produce tab pane

  @FXML
  private Label lbl_ChooseP;//Choose Product label

  @FXML
  private ListView<?> lstvw_ChooseP;//Choose product list View

  @FXML
  private Label lbl_ChQuantity;//Choose Quantity label

  @FXML
  private ComboBox<String> cboxChQuantity;//Choose Quantity ComboBox

  @FXML
  private Button btn_RecProduction;//Record Production Button

  @FXML
  private Tab tab_productionlog;//Production Log tab

  @FXML
  private TextArea txtarea_PLog;//Production Log Text area

  /**
   * The method display inserts a new product when the user clicks on the Add Product button
   * @param actionEvent
   */
  @FXML
  public void display(ActionEvent actionEvent) {

    //Initializing the DataBase
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/KPL";

    //Database credentials

    Connection conn = null;
    Statement stmt = null;

    try {
      Class.forName(JDBC_DRIVER);
      //Open a connection
      conn = DriverManager.getConnection(DB_URL); //bugfound

      //Execute a query
      stmt = conn.createStatement();

      //Inserts the given values into the DataBase ProdDB. (Product Table)
      String sql = "INSERT INTO PRODUCT(type,manufacturer,name)" + "VALUES ('AUDIO','APPLE','IPOD')";
      //"SELECT * FROM PRODUCT";
      stmt.executeUpdate(sql);//Updates the values in the Product table
      /*ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }*/

      // STEP 4: Clean-up environment
      stmt.close();//Closes the statements and the connections
      conn.close();

    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    setupProductLineTable();
    System.out.println("Product Added");
  }

  /**
   * Method that prints into console when the user clicks the Production record button
   * @param actionEvent
   */
  @FXML
  public void recorded(ActionEvent actionEvent){
    System.out.println("Production Recorded");
  }

  @FXML
  /**
   * Method to initialize and populate the ComboBox with 1-10 values
   */
  public void initialize(){
    cboxChQuantity.setItems(produceNum);//sets the items in the ComboBox
    cboxChQuantity.setEditable(true);//Allows the user edit
    cboxChQuantity.getSelectionModel().selectFirst();//Sets a default value in the ComboBox

  }

  public void setupProductLineTable(){
    ObservableList<Product> productLine = populateTable();
    col_PName.setCellValueFactory(new PropertyValueFactory("name"));
    col_Manufact.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    col_IType.setCellValueFactory(new PropertyValueFactory("type"));
    tbview_ExistingP.setItems(productLine);
  }

  public static ObservableList<Product> populateTable(){
    return FXCollections.observableArrayList(
        new Widget("IPOD", "APPLE", ItemType.AUDIO)
    );
  }
}

/*
  //First display method that prints a phrase into console
  @FXML
  public void display(ActionEvent actionEvent){
    System.out.println("Product Added");
  }
*/
