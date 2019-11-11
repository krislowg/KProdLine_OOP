package sample;

/**
 * Semester: Fall 2019. 9/28/2019.
 * Enum ItemType. Represents the group of named constants for the Item type
 *
 * @author Kristy Low
 */
public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  private String code;

  /**
   * ItemType constructor that sets the code variable a value
   * @param code of the product
   */
  ItemType(String code) {
    this.code = code;
  }

  /**
   * Method that returns the value of the code
   * @return code, value of the differents variable of
   */
  public String getCode() {
    return code;
  }
}
