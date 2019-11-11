package sample;

/**
 * Semester: Fall 2019. 9/28/2019
 *
 * @author Kristy Low
 */

/**
 * * Screen spec interface. It is the blueprint of the Screen class. Establish the functions that
 * the Screen class should implement
 */
public interface ScreenSpec {
  String getResolution();

  int getRefreshRate();

  int getResponseTime();
}
