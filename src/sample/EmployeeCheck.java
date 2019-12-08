package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Employee class creates the account information for an employee.
 * @author Kristy Low
 */
public class EmployeeCheck {
  private String name;
  private String password;
  private String username;
  private String email;

  /**
   * Method that gets the username
   * @return the String content of the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Method that get the email
   * @return sTring email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Method that gets the name
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * Method that gets the employee name
   * @param name of the employee
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method that get the password
   * @return String password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Method that sets the password
   * @param password set valid password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Constructor to set name, username and password
   * @param name of the employee
   * @param password of employee
   */
  public EmployeeCheck(String name, String password) {
    this.name = name;
    if (isValidPassword(password))
      this.password = password;
    else
      this.password = "pw";
    checkName(name);
  }

  /**
   * Method that checks if the full name contains an empty space
   * @param name insert it by employee
   */
  private void checkName(String name){
    if (name.contains(" ")){ //check if name contains a space
      setUsername(name);
      setEmail(name);
    }
    else{
      username = "default";
      email = "user" + "@oracleacademy.Test";}
    //set username to default
    //set email to user@oracleacademy.Test

  }

  /**
   * Method that set the username of the employee
   * @param name name of the employee
   */
  private void setUsername(String name){
    String newName = name.substring(0,1) + name.substring(name.lastIndexOf(" "), name.length());//set username field to the first initial of the first name and then the last name, all lowercase.
    username = newName.toLowerCase().replaceAll("\\s", "");
  }

  /**
   * Method that sets the company email for the employee
   * @param name of employee
   */
  private void setEmail(String name){
    String first = name.substring(0,name.indexOf(" "))+"."+name.substring(name.lastIndexOf(" ")); //first name, then a period, then the last name (all lowercase) followed by @oracleacademy.Test
    email = first.toLowerCase().replaceAll("\\s", "")+"@oracleacademy.Test";
  }

  /**
   * Method that verifies if the password fills all the requirements
   * @param password enter by the employee
   * @return true if the password contains all the requirements
   */
  private Boolean isValidPassword(String password){
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$"; //valid password:containing a lowercase letter, uppercase letter, and a special character
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(password);

    return matcher.matches();
  }


  /**
   * String representation of the EmployeeCheck class
   * @return String of the
   */
  public String toString(){
    return "Employee Details\n" + "Name : " + name + "\nUsername : " + username + "\nEmail : " + email +
        "\nInitial Password : " + password;
  }


}
