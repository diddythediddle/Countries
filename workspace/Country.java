//This class represents a country with details about its name, capital, primary language, and an image.
public class Country {
 
  private String name;
  private String cap;
  private String lang;
  private String img;

  //Precondition: Input strings (name, cap, lang, img) must not be null.
//Postcondition: Creates a Country object with the provided attributes.
  public Country(String name, String cap, String lang, String img) {
      this.name = name;
      this.cap = cap;
      this.lang = lang;
      this.img = img;
  }

  //Precondition: None.
//Postcondition: Returns the corresponding attribute value of the Country object.
  public String getName() {
      return name;
  }

  public String getCap() {
      return cap;
  }

  public String getLang() {
      return lang;
  }

  public String getImg() {
      return img;
  }

  //Precondition: The Country object must be fully initialized.
  //Postcondition: Returns a formatted string representation of the Country object.
  @Override
  public String toString() {
      return name + "'s capital is " + cap + " and its primary language is " + lang + ".";
  }
}