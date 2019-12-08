package sample;

/**
 * Screen spec interface. It is the blueprint of the Screen class. Establish the functions that *
 * the Screen class should implement.
 * @author Kristy Low
 */
public interface ScreenSpec {
  String getResolution();

  int getRefreshRate();

  int getResponseTime();
}
