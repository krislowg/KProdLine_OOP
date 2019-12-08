package sample;

/**
 * Product class with Item as an Interface. Class that captures all the details of the produced
 * product.
 *
 * @author Kristy Low
 */
public abstract class Product implements Item {
  private int id;
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
   * @param type of the product
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

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the id value of the product
   * @return id
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Sets the name of the product
   * @param name String
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the value of the product name
   * @return a String with the value name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Set the value of the manufacturer field of the produced product
   * @param manufacturer of the product
   */
  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Gets the value of the manufacturer of the produced product
   * @return String with the manufacturer value
   */
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
  Product( int id, String name, String manufacturer, ItemType type){
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
 *Widget constructor that represents the Product class
 */
class Widget extends Product{
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
  Widget(int id, String name, String manufacturer, ItemType type){
    super(id, name, manufacturer, type);

  }

}

