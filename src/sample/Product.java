package sample;

/**
 * Semester: Fall 2019. 9/28/2019.
 * Product class with Item MultimediaControl as an Interface. Class that
 * captures all the details of the product.
 *
 * @author Kristy Low
 */
public abstract class Product implements Item {
  private String id;
  private ItemType type;
  private String manufacturer;
  private String name;

  /**
   *
   * @return the product type
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Set the value for the product type
   * @param type
   */
  public void setType(ItemType type) {
    this.type = type;
  }

  /**Constructor
   * Sets the values for all the fields od the Product class
   * @param name of the product
   * @param manufacturer of the product
   * @param type of the product
   */
  public Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Constructor overload, that also set the value of the product id
   * @param name of the product
   * @param manufacturer of the product
   * @param type of the product
   * @param id of the product
   */
  Product(String name, String manufacturer, ItemType type, String id){
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Method that returns the string representation of the object (instance of a class)
   * @return a String with the information of the values of all the fields of Product
   */
  public String toString(){
    return "Name: " + name
        + "\nManufacturer: " + manufacturer
        + "\nType: " + type;
    // + "\nId: " + id;
  }
}

/**
 * Representation of the product class
 */
class Widget extends Product{
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

}

