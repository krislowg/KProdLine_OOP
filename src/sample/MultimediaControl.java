package sample;

/**
 * Semester: Fall 2019. 9/28/2019.
 * MultimediaControl interface. It is the blueprint of the class MoviePlayer and AudioPLayer.
 * Establish the functions that MoviePlayer and AudioPLayer must do.
 *
 * @author Kristy Low
 */
public interface MultimediaControl {

  /**
   * * Header of the method play. It is void because it will print a message in the console.
   */
  void play();

  /**
   * * Header of the method stop. It is void because it will print a message in the console.
   */
  void stop();

  /**
   * * Header of the method stop. It is void because it will print a message in the console.
   */
  void previous();

  /**
   * * Header of the method next. It is void because it will print a message in the console.
   */
  void next();
}
