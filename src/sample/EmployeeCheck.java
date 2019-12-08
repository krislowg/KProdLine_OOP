package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeCheck {
  private String name;
  private String password;
  private String username;
  private String email;

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public EmployeeCheck(String name, String password) {
    this.name = name;
    if (isValidPassword(password)==true)
      this.password = password;
    else
      this.password = "pw";
    checkName(name);
  }


  public void checkName(String name){
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

  public void setUsername(String name){
    String newName = name.substring(0,1) + name.substring(name.lastIndexOf(" "), name.length());//set username field to the first initial of the first name and then the last name, all lowercase.
    username = newName.toLowerCase().replaceAll("\\s", "");
  }

  public void setEmail(String name){
    String first = name.substring(0,name.indexOf(" "))+"."+name.substring(name.lastIndexOf(" ")); //first name, then a period, then the last name (all lowercase) followed by @oracleacademy.Test
    email = first.toLowerCase().replaceAll("\\s", "")+"@oracleacademy.Test";
  }

  public Boolean isValidPassword(String password){
/*
    if(password.matches("[a-z][A-Z].")) //valid password:containing a lowercase letter,uppercase letter, and a special character
      return password;
    else
      return "pw";*/
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$"; //valid password:containing a lowercase letter, uppercase letter, and a special character

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();

  }

  public String toString(){
    return "Employee Details\n" + "Name : " + name + "\nUsername : " + username + "\nEmail : " + email +
        "\nInitial Password : " + password;
  }


}
