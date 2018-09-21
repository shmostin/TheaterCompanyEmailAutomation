package edu.neu.ccs.cs5004;

import java.util.Objects;

/**
 * Member is a placeholder for the strings read out of the input file and used to overwrite the tags
 * in the template file before it is printed to file.
 */
public class Member implements MemberInterrface{

  private String firstName;
  private String lastName;
  private String company;
  private String address;
  private String city;
  private String county;
  private String state;
  private String zip;
  private String phoneOne;
  private String phoneTwo;
  private String email;
  private String website;


  /**
   * Constructor for Member
   * @param firstName String self documenting.
   * @param lastName String self documenting.
   * @param company String self documenting.
   * @param address String self documenting.
   * @param city String self documenting.
   * @param county String self documenting.
   * @param state String self documenting.
   * @param zip String self documenting.
   * @param phoneOne String self documenting.
   * @param phoneTwo String self documenting.
   * @param email String self documenting.
   * @param website String self documenting.
   */
  public Member(String firstName, String lastName, String company, String address, String city,
                String county, String state, String zip, String phoneOne, String phoneTwo,
                String email, String website) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.city = city;
    this.county = county;
    this.state = state;
    this.zip = zip;
    this.phoneOne = phoneOne;
    this.phoneTwo = phoneTwo;
    this.email = email;
    this.website = website;
  }

  /**
   * @return String firstName.
   */
  public String getFirstName() {
    return firstName;
  }
  /**
   * @return String lastName.
   */
  public String getLastName() {
    return lastName;
  }
  /**
   * @return String company.
   */
  public String getCompany() {
    return company;
  }
  /**
   * @return String address.
   */
  public String getAddress() {
    return address;
  }
  /**
   * @return String city.
   */
  public String getCity() {
    return city;
  }
  /**
   * @return String county.
   */
  public String getCounty() {
    return county;
  }
  /**
   * @return String state.
   */
  public String getState() {
    return state;
  }
  /**
   * @return String zip.
   */
  public String getZip() {
    return zip;
  }
  /**
   * @return String phone1.
   */
  public String getPhoneOne() {
    return phoneOne;
  }
  /**
   * @return String phone1.
   */
  public String getPhoneTwo() {
    return phoneTwo;
  }
  /**
   * @return String email.
   */
  public String getEmail() {
    return email;
  }
  /**
   * @return String website.
   */
  public String getWebsite() {
    return website;
  }

  /**
   * Finds the correct value for the given token
   * @param token a string that contains the information being requested.
   * @return members information for the given token.
   */
  String findValue(String token) {
    if (token.contains("first_name")) {
      return firstName;
    } else if (token.contains("last_name")) {
      return lastName;
    } else if (token.contains("company_name")) {
      return company;
    } else if (token.contains("address")) {
      return address;
    } else if (token.contains("city")) {
      return city;
    } else if (token.contains("county")) {
      return county;
    } else if (token.contains("state")) {
      return state;
    } else if (token.contains("zip")) {
      return zip;
    } else if (token.contains("phone1")) {
      return phoneOne;
    } else if (token.contains("phone2")) {
      return phoneTwo;
    } else if (token.contains("email")) {
      return email;
    } else if (token.contains("web")) {
      return website;
    }

    return "Value Not Found";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Member member = (Member) obj;
    return Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName)
        && Objects.equals(company, member.company) && Objects.equals(address, member.address)
        && Objects.equals(city, member.city) && Objects.equals(county, member.county)
        && Objects.equals(state, member.state) && Objects.equals(zip, member.zip)
        && Objects.equals(phoneOne, member.phoneOne) && Objects.equals(phoneTwo, member.phoneTwo)
        && Objects.equals(email, member.email) && Objects.equals(website, member.website);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, company, address, city, county, state, zip, phoneOne,
        phoneTwo, email, website);
  }

  @Override
  public String toString() {
    return "Member{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
        + ", company='" + company + '\'' + ", address='" + address + '\'' + ", city='" + city + '\''
        + ", county='" + county + '\'' + ", state='" + state + '\'' + ", zip='" + zip + '\''
        + ", phoneOne='" + phoneOne + '\'' + ", phoneTwo='" + phoneTwo + '\'' + ", email='" + email
        + '\'' + ", website='" + website + '\'' + '}';
  }

}
