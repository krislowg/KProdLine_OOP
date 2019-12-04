package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Semester: Fall 2019. 9/28/2019 ProductionLine Program that helps a media player production
 * facility keep track of its produced products.
 *
 * @author Kristy Low
 */

/** Main class which has the Application class as a parent. Inheritance. */
public class Main extends Application {

  /**
   * Start method. Main entry for all JavaFX applications
   *
   * @param primaryStage main page
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ProductionTabs.fxml"));
    primaryStage.setTitle("Kristy's Production Line");
    primaryStage.setScene(new Scene(root, 1036, 615));
    primaryStage.show();
  }

  /**
   * This is the main method
   *
   * @param args Array
   */
  public static void main(String[] args) {
    launch(args);
  }
}

/*Acknowledgements
Background Photo by James Owen on Unsplash
https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
http://tutorials.jenkov.com/jdbc/preparedstatement.html
http://tutorials.jenkov.com/jdbc/query.html
https://stackoverflow.com/questions/42211063/how-to-populate-combobox
https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html#controllers
Classmates: Jackson Turner and Austin Nolz.
*/
