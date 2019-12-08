package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

  //Employee Account
  @FXML
  private TextField txt_EmpName;

  @FXML
  private TextField txt_Password;

  @FXML
  private TextArea textArea_EmployeeAccount;

  @FXML
  private Button btn_CreateAccount;

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

    setupProductLineTable();
    loadProductList();
    loadProductionLog();
  }

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

    addProductToDb();
    // adds the product description to the listview in the Produce Tab
    //lstvw_ChooseP.getItems().add(myProduct);

    //Sets the description of the produce product inside the text area Production Log
    //txtarea_PLog.setText(productLine.toString());

    System.out.println("Product Added");
  }

  public void addProductToDb(){
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
        //stmt.close(); // Closes the statements and the connections
        //conn.close();

      } catch (SQLException e) {
        e.printStackTrace();
      }

    //Adding a product to the Table view (Product Line tab)
    productLine.add(myProduct);

  }


  /**
   * Method that prints into console when the user clicks the Production record button
   *
   * @param actionEvent the click of the button is the action
   */
  @FXML
  public void recorded(ActionEvent actionEvent) {
    // getting the values from the quantity cbox and setting the text into Product log in

    Widget record = new Widget (lstvw_ChooseP.getSelectionModel().getSelectedItem().getName(), lstvw_ChooseP.getSelectionModel().getSelectedItem().getManufacturer(),
        lstvw_ChooseP.getSelectionModel().getSelectedItem().getType());

    int quantity = Integer.parseInt(String.valueOf(cboxChQuantity.getSelectionModel().getSelectedItem()));

    ProductionRecord pr;

    final  ArrayList<ProductionRecord> productRun = new ArrayList<>();
    // for loop to register the quantity of products in the Product Log In
    for (int i = 0; i < quantity; i++) {
      pr = new ProductionRecord(record, i);
      productRun.add(pr);
    }
    addToProductionDB(productRun);
    loadProductionLog();
    System.out.println("Production Recorded");
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

  /**
   * Method that Initializes the connection with Database
   */
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

  /**
   * Create Product objects from the Product database table and add them to the productLine
   * ObservableList (which will automatically update the Product Line ListView).
   */
  private void loadProductList(){
    try{
    String sql = "SELECT * FROM PRODUCT";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()){

      int id = rs.getInt(1);
      String name = rs.getString(2);
      String myType = rs.getString(3);
      String manufacturer = rs.getString(4);

      Product dB =new Product(id,name,manufacturer,ItemType.valueOf((myType))){};
      productLine.add(dB);}
    }catch (SQLException e){
      e.printStackTrace();
    }
  }

  /**
   * Methods that adds specific products to be recorded in the production
   * @param productRun ArrayList of Production Records
   */
  public void addToProductionDB(ArrayList<ProductionRecord> productRun){
    try{
      String sql = "INSERT INTO PRODUCTIONRECORD(product_id, serial_num, "
          + "date_produced)" + "VALUES (?,?,?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      for(int i = 0; i<productRun.size(); i++){

    //Integer productionNum = productRun.get(i).getProductionNum();
    Integer productId = productRun.get(i).getProductID();
    String serialNum = productRun.get(i).getSerialNum();
    java.util.Date date = new java.util.Date();
    java.sql.Timestamp dateProduced = new java.sql.Timestamp(date.getTime());

    //ps.setInt(1,productionNum);
    ps.setInt(1,productId);
    ps.setString(2, serialNum);
    ps.setTimestamp(3, dateProduced);
    ps.executeUpdate();

    showProduction(productRun);
    System.out.println(productId + serialNum + dateProduced);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

  public void showProduction(ArrayList<ProductionRecord> productRun){
    String str = productRun.toString();
    txtarea_PLog.appendText(str.replaceAll("[\\[\\],]", "")+"\n");
  }

  public void loadProductionLog(){

    try{
      String sql = "SELECT * FROM PRODUCTIONRECORD";
      ResultSet rs = stmt.executeQuery(sql);
      txtarea_PLog.clear();
      while (rs.next()){

        int productionNumber = rs.getInt(1);
        int productionId = rs.getInt(2);
        String serialNum = rs.getString(3);
        Date dateProduced = rs.getTimestamp(4);

        ProductionRecord recordDb = new ProductionRecord(productionNumber, productionId, serialNum, dateProduced){};
        ArrayList<ProductionRecord> productionLog = new ArrayList<>();
        productionLog.add(recordDb);
        showProduction(productionLog);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
  }

  //////////////////////Create Employee Account//////////////////////////////////


  public void getFieldsContent(){
    String fullName = txt_EmpName.getText();
    String password =txt_Password.getText();
    if (txt_EmpName.getText().trim().isEmpty() || txt_Password.getText().trim().isEmpty()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("You must fill all the fields");
      alert.setContentText(null);
      Optional<ButtonType> action = alert.showAndWait();
    } else {
      EmployeeCheck newEmployee = new EmployeeCheck(fullName, password);
      textArea_EmployeeAccount.setText(newEmployee.toString());
    }
  }

  @FXML
  void checkCredentials(ActionEvent event) {
    getFieldsContent();
  }



  ///////////////////////////////////////////////////////////////////////////////
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
