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

public class Employee {

  @FXML
  private Button btn_SignIn;

  @FXML
  void change_EmpToProduction(ActionEvent event) throws IOException {
    Parent employeeParent = FXMLLoader.load(getClass().getResource("ProductionTabs.fxml"));
    Scene productScene = new Scene(employeeParent);

    Stage productLineWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    productLineWindow.setScene(productScene);
    productLineWindow.show();
  }


}
