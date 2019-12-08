package sample;

import java.util.Date;

/**
 * Semester: Fall 2019. 9/28/2019
 * ProductionRecord class describes what product and when a product was produced.
 *
 * @author Kristy Low
 */

public class ProductionRecord {
  private int productionNumber;//unique num e/item produced (autoincremented by the database)
  private int productID;//numb to correspond the ProductId table/class
  private String serialNumber;//unique number that identifies the produced product
  private Date dateProduced;// production date

  /**
   * Sets the value for the productionnumber field
   * @param productionNumber of a product
   */
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * Gets the value from the production num
   * @return prodcution number of the product produced
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * Sets the value of the ProductID of the produced product
   * @param productID
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * Gets the ID of the produced product
   * @return
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Sets the serial Number value  of the produced product
   * @param serialNumber
   */
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * Gets the value of the Serial number of the product produced
   * @return
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Sets the Date when the product was produced
   * @param dateProduced takes a date
   */
  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced; //bug found
  }

  /**
   * Gets the date when the product was produced
   * @return
   */
  public Date getProdDate() {
    return dateProduced; //bug found
  }

  /**
   * Constructor that sets the productID value and sets the other fields to 0 and calls the default
   * constructor for the produced date
   * @param productID
   */
  ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * ProductionRecord constructor that sets the values of all the fields of the class
   * @param productionNumber of the produced product
   * @param productID of the produced product
   * @param serialNumber of the produced product
   * @param dateProduced of the produced product
   */
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /**
   * Sets the value of serial number with the combination of the manufacturer values, the product
   * type, and a autoincremented value
   * @param product
   * @param count
   */
  ProductionRecord(Product product, int count) {
    this.serialNumber =
        product.getManufacturer().substring(0, 3)
            + product.getType().getCode()
            + String.format("%05d", count);
    this.dateProduced = new Date();
  }

  /**
   * Method that returns the string representation of the Production record class
   * @return a String with the information of all the specification of the Product produced
   */
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productID
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced;
  }

}
