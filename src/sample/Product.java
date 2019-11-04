package sample;

public abstract class Product implements Item {
  private String id;
  private ItemType type;
  private String manufacturer;
  private String name;

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

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


  Product(String name, String manufacturer, ItemType type, String id){
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString(){
    return "Name: " + name
        + "\nManufacturer: " + manufacturer
        + "\nType: " + type;
    // + "\nId: " + id;
  }
}

class Widget extends Product{
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}

