package sample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * Semester: Fall 2019. 9/28/2019 ProductionLine Program that helps a media player production
 * facility to keep track of their produced products.
 *
 * @author Kristy Low
 */

/** The controller class defines the UI elements and determines the functions to user actions */
public class ProductionTabsController {
  ObservableList<ItemType> itemType =
      FXCollections.observableArrayList(
          ItemType.AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);

  // ComboBox values from 1-10
  ObservableList<String> produceNum =
      FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

  @FXML private Tab tab_pline; // Production Line Tab

  @FXML private GridPane label_manufacturer; // Production Line GridPane

  @FXML private Label label_pname; // Product name label

  @FXML private Label label_manuf; // Manufacturer label

  @FXML private Label label_IType; // Item type label

  @FXML private TextField textfield_pname; // Product name textfield

  @FXML private TextField textfield_manuf; // Manufacturer text field

  @FXML private Button btn_addproduct; // Add Product Button

  @FXML private Label label_confaddprod; // Add product confirmation

  @FXML private Label lbl_ExistingP; // Existing Product label

  @FXML private TableView<Product> tbview_ExistingP; // Existing Product table view

  @FXML private ChoiceBox<ItemType> choicebox_IType; // Item Type choice box

  @FXML private TableColumn<?, ?> col_PName;

  @FXML private TableColumn<?, ?> col_Manufact;

  @FXML private TableColumn<?, ?> col_IType;

  @FXML private Tab tab_produce; // Produce tab pane

  @FXML private Label lbl_ChooseP; // Choose Product label

  @FXML private ListView<Product> lstvw_ChooseP; // Choose product list View

  @FXML private Label lbl_ChQuantity; // Choose Quantity label

  @FXML private ComboBox<String> cboxChQuantity; // Choose Quantity ComboBox

  @FXML private Button btn_RecProduction; // Record Production Button

  @FXML private Tab tab_productionlog; // Production Log tab

  @FXML private TextArea txtarea_PLog; // Production Log Text area

  /**
   * The method display inserts a new product when the user clicks on the Add Product button
   *
   * @param actionEvent The action is the click of the button
   */
  @FXML
  public void display(ActionEvent actionEvent) {

    // Initializing the DataBase
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/KPL";

    // Database credentials

    Connection conn = null;
    Statement stmt = null;

    // Getting values from text field and combobox in Product Line tab and storing them in a
    // variable
    String pName1 = textfield_pname.getText();
    String manufacturer1 = textfield_manuf.getText();
    ItemType itemType1 = choicebox_IType.getValue();

    Product myProduct = new Widget(pName1, manufacturer1, itemType1);

    setupProductLineTable(myProduct);
    try {
      Class.forName(JDBC_DRIVER);
      // Open a connection
      conn = DriverManager.getConnection(DB_URL); // bugfound

      // Execute a query
      stmt = conn.createStatement();

      // Inserts the given values into the DataBase ProdDB. (Product Table)
      System.out.println("Attempting to INSERT");
      String sql =
          "INSERT INTO PRODUCT(type,manufacturer,name)"
              + "VALUES (?,?,?)"; // 'AUDIO','APPLE','IPOD'
      // "SELECT * FROM PRODUCT";
      PreparedStatement ps = conn.prepareStatement(sql); // bugfound
      ps.setString(1, String.valueOf(itemType1));
      ps.setString(2, manufacturer1);
      ps.setString(3, pName1);

      ps.executeUpdate(); // Updates the values in the Product table
      System.out.println("Inserted!");

      // STEP 4: Clean-up environment
      stmt.close(); // Closes the statements and the connections
      conn.close();

    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // adds the product description to the listview in the Produce Tab
    lstvw_ChooseP.getItems().add(myProduct);

    // getting the values from the quantity cbox and setting the text into Product log in
    String quantity1 = cboxChQuantity.getValue();

    String text = "";
    // for loop to register the quantity of products in the Log In
    for (int i = 0; i < Integer.parseInt(quantity1); i++) {
      text =
          text
              + "Name: "
              + pName1
              + " Manufacturer:"
              + manufacturer1
              + " Item Type"
              + itemType1
              + "\n"; // bugfound
    }

    txtarea_PLog.setText(text);

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

  @FXML
  /** Method to initialize and populate the ComboBox with 1-10 values */
  public void initialize() {
    choicebox_IType.setItems(itemType); // sets the items in the ComboBox
    // choicebox_IType.setEditable(true);//Allows the user edit
    choicebox_IType.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    // Quantity combobox in the Product log tab
    cboxChQuantity.setItems(produceNum); // sets the items in the ComboBox
    cboxChQuantity.setEditable(true); // Allows the user edit
    cboxChQuantity.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox
  }

  ObservableList<Product> productLine = FXCollections.observableArrayList();

  /** Method that sets the tableview columns */
  public void setupProductLineTable(Product myProduct) {

    col_PName.setCellValueFactory(new PropertyValueFactory("name"));
    col_Manufact.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    col_IType.setCellValueFactory(new PropertyValueFactory("type"));
    tbview_ExistingP.setItems(productLine);

    productLine.add(myProduct);
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
