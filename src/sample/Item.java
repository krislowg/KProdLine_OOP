package sample;

/**
 * Semester: Fall 2019. 9/28/2019.
 * Item interface. It is the blueprint of the class Product. Establish the functions that Product
 * should implement for the Product class
 *
 * @author Kristy Low
 */
public interface Item {

  /**
   * Header of the method that gets the Product Id
   */
  String getId();

  /**
   * Header of the method that sets the Product name
   */
  void setName(String name);

  /**
   * Header of the method that gets the Product name
   */
  String getName();

  /**
   * Header of the method that sets the Product manufacturer
   */
  void setManufacturer(String manufacturer);

  /**
   * Header of the method that gets the Product manufacturer
   */
  String getManufacturer();
}
