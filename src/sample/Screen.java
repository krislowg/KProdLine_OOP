package sample;

/**
 * Screen class. Establish the specifications of the product's screen
 */
public class Screen implements ScreenSpec {
  private String resolution; //produced product
  private int refreshRate;
  private int responseTime;

  /**
   * Constructor for the class Screen
   * @param resolution value of the product resolution
   * @param refreshRate value of the product's screen refresh rate
   * @param responseTime value of the product's screen reponse tme
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * Method that returns the string representation of the object (instance of a class)
   * @return a String with the information of the values of all the fields of Screen
   */
  public String toString() {
    return "Screen:\nResolution: "
        + resolution
        + "\nRefresh rate: "
        + refreshRate
        + "\nResponse time: "
        + responseTime;
  }
}
