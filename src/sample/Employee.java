package sample;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Employee class takes the username and password of an employee to sign in
 * @author Kristy Low
 */

public class Employee {

  @FXML
  private Button btn_SignIn;

  /**
   * Changes scenes between employee sign in to production tabs
   * @param event sig in button pressed
   * @throws IOException exception
   */
  @FXML
  void change_EmpToProduction(ActionEvent event) throws IOException {
    Parent employeeParent = FXMLLoader.load(getClass().getResource("ProductionTabs.fxml"));
    Scene productScene = new Scene(employeeParent);

    Stage productLineWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    productLineWindow.setScene(productScene);
    productLineWindow.show();
  }


}
