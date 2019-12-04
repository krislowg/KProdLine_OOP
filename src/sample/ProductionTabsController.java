package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Semester: Fall 2019. 9/28/2019 ProductionLine Program that helps a media player production
 * facility to keep track of their produced products.
 *
 * @author Kristy Low
 */

/** The controller class defines the UI elements and determines the functions to user actions. */
public class ProductionTabsController {
  ObservableList<ItemType> itemType =
      FXCollections.observableArrayList(
          ItemType.AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);

  // ComboBox values from 1-10
  ObservableList<Integer> produceNum =
      FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

  @FXML private TextField textfield_pname; // Product name textfield

  @FXML private TextField textfield_manuf; // Manufacturer text field

  @FXML private Button btn_addproduct; // Add Product Button

  @FXML private TableView<Product> tbview_ExistingP; // Existing Product table view

  @FXML private ChoiceBox<ItemType> choicebox_IType; // Item Type choice box

  @FXML private TableColumn<Product, String> col_PName;

  @FXML private TableColumn<Product, String> col_Manufact;

  @FXML private TableColumn<Product, ItemType> col_IType;

  @FXML private ListView<Product> lstvw_ChooseP; // Choose product list View

  @FXML private ComboBox<Integer> cboxChQuantity; // Choose Quantity ComboBox

  @FXML private Button btn_RecProduction; // Record Production Button

  @FXML private TextArea txtarea_PLog; // Production Log Text area

  /**
   * The method display inserts a new product when the user clicks on the Add Product button
   *
   * @param actionEvent The action is the click of the button
   */
  @FXML
  public void display(ActionEvent actionEvent) {
    /*
    //Code to show a warning message when the user presses the add button with empty fields
    if (textfield_pname.getText().trim().isEmpty() || textfield_manuf.getText().trim().isEmpty()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("You must fill all the fields");
      alert.setContentText(null);
      Optional<ButtonType> action = alert.showAndWait();
      }*/
    // Getting values from text field and combobox in Product Line tab and storing them in a
    // variable
    String pName1 = textfield_pname.getText();
    String manufacturer1 = textfield_manuf.getText();
    String itemType1 = choicebox_IType.getValue().toString();

    Product myProduct = new Widget(pName1, manufacturer1, ItemType.valueOf(itemType1));


    try {
      // Inserts the given values into the DataBase ProdDB. (Product Table)
      System.out.println("Attempting to INSERT");
      String sql =
          "INSERT INTO PRODUCT(name, type, manufacturer)"
              + "VALUES (?,?,?)"; // 'AUDIO','APPLE','IPOD'
      // "SELECT * FROM PRODUCT";
      PreparedStatement ps = conn.prepareStatement(sql); // bugfound
      ps.setString(2, itemType1);
      ps.setString(3, manufacturer1);
      ps.setString(1, pName1);

      ps.executeUpdate(); // Updates the values in the Product table
      System.out.println("Inserted!");

      // STEP 4: Clean-up environment
      stmt.close(); // Closes the statements and the connections
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // adds the product description to the listview in the Produce Tab
    //lstvw_ChooseP.getItems().add(myProduct);

    // getting the values from the quantity cbox and setting the text into Product log in

    Product record = lstvw_ChooseP.getSelectionModel().getSelectedItem();

    int quantity = Integer.parseInt(String.valueOf(cboxChQuantity.getSelectionModel().getSelectedItem()));

    ProductionRecord pr;

    final  ArrayList<ProductionRecord> productRun = new ArrayList<>();
    // for loop to register the quantity of products in the Product Log In
    /*for (int i = 0; i < quantity; i++) {
      pr = new ProductionRecord(record, i);
      productRun.add(pr);
    }*/

    //Adding a product to the Table view (Product Line tab)
    productLine.add(myProduct);

    //Sets the description of the produce product inside the text area Production Log
    txtarea_PLog.setText(productLine.toString());

    System.out.println("Product Added");
  }

  /**
   * Method that prints into console when the user clicks the Production record button
   *
   * @param actionEvent the click of the button is the action
   */
  @FXML
  public void recorded(ActionEvent actionEvent) {

    System.out.println("Production Recorded");
  }

  private Connection conn = null;
  private Statement stmt = null;

  @FXML
  /** Method to initialize and populate the ComboBox with 1-10 values */
  public void initialize() {
    initializeDB();
    choicebox_IType.setItems(itemType); // sets the items in the ComboBox
    // choicebox_IType.setEditable(true);//Allows the user edit
    choicebox_IType.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    // Quantity combobox in the Product log tab
    cboxChQuantity.setItems(produceNum); // sets the items in the ComboBox
    cboxChQuantity.setEditable(true); // Allows the user edit
    cboxChQuantity.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox
    System.out.println(ItemType.AUDIO.getCode());

    setupProductLineTable();
    loadProductList();
  }

  ObservableList<Product> productLine = FXCollections.observableArrayList();//Table view related

  /** Method that sets the tableview columns */
  public void setupProductLineTable() {
    col_PName.setCellValueFactory(new PropertyValueFactory("name"));
    col_Manufact.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    col_IType.setCellValueFactory(new PropertyValueFactory("type"));
    tbview_ExistingP.setItems(productLine);
    lstvw_ChooseP.setItems(productLine);
  }

  private void initializeDB() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/KPL";
    final String USER = "";
    final String PASS = "pass";
    try{
      Properties prop = new Properties();
      prop.load((new FileInputStream("res/properties")));
    }
    catch(Exception e){
      System.out.println(e);
    }

    System.out.println("Attempting to connect to database");
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      System.out.println("Successfully connected to database!");
    } catch (Exception e) {
      e.printStackTrace();
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.show();
    }
  }

  private void loadProductList(){
    try{
    String sql = "SELECT * FROM PRODUCT";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()){

      int id = rs.getInt(1);
      String name = rs.getString(2);
      String myType = rs.getString(3);
      String manufacturer = rs.getString(4);
      /*if(type.equals("AUDIO"))
        mytype = ItemType.AUDIO;*/
      Product dB =new Product(id,name,manufacturer,ItemType.valueOf((myType))){};
      productLine.add(dB);}
    }catch (SQLException e){
      e.printStackTrace();
    }
    //tbview_ExistingP.setItems(productLine);
  }

  /**
   * Method that tests the functionality of the multimedia player classes: AudioPlayer and
   * MoviePlayer
   */
  public static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }
}
