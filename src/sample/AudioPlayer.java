package sample;

/**
 * Semester: Fall 2019. 9/28/2019.
 * AudioPlayer class with Product as a parent and MultimediaControl as an Interface. Class that
 * captures all the details of an AudioPlayer
 *
 * @author Kristy Low
 */
public class AudioPlayer extends Product implements MultimediaControl {
  private String supportedAudioFormats; //
  private String supportedPlaylistFormats;

  /**
   *
   * @param name of the product
   * @param manufacturer of the product intended to produce
   * @param supportedAudioFormats the supported audio format of the product
   * @param supportedPlaylistFormats the supported playlist format of the product
   */
  AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * Method that prints in console when the play() method is called.
   */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /**
   * Method that prints in console when the stop() method is called.
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * Method that prints in console when the previous() method is called.
   */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * Method that prints in console when the next() method is called.
   */
  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * Method that returns the string representation of the object
   * @return a String with the information of the Supported Audio Formats and Playlist Formats
   */
  @Override
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormats;
  }
}

