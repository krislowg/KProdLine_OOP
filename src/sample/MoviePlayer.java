package sample;

/**
 * MoviePlayer class with Product as a parent and MultimediaControl as an Interface. Class that
 * captures all the details of a MoviePlayer
 *
 * @author Kristy Low
 */
public class MoviePlayer extends Product implements MultimediaControl {
  private Screen screen; //screen field of data type Screen(Class)
  private MonitorType monitorType; //monitorType field of data type MonitorType(Class)

  /**
   * Constructor of the MoviePlayer class
   * @param name of the product intended to produced
   * @param manufacturer of the product
   * @param screen screen specification
   * @param monitorType monitor specification
   */
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Method that prints in console when the play() method is called.
   */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * Method that prints in console when the stop() method is called.
   */
  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * Method that prints in console when the previous() method is called.
   */
  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  /**
   * Method that prints in console when the next() method is called.
   */
  @Override
  public void next() {
    System.out.println("Next movie");
  }

  /**
   * Method that returns the string representation of the object
   * @return a String with the information of all the specification of the Movie Player
   */
  public String toString() {
    return super.toString() + "\n" + screen.toString() + "\nMonitor Type: " + monitorType;
  }
}
