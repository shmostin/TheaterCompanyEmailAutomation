package edu.neu.ccs.cs5004;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads in text from the csv file and stores the info in Member.
 */
public class TextReader {

  private static final String SUCCESS = "\nThe files have been created successfully, "
      + "they are now in the supplied output file location: ";

  /**
   * main method that reads the input file and writes to the templates.
   * @param args the arguments from the user
   * @throws IOException throws exception for incorrect inputs from user
   */
  public static void main(String[] args) throws IOException {

    String line = "";
    ArgReader argReader = new ArgReader(args);
    Template newTemplate = new Template();
    String splitter = "\",\"";
    String[] wholeString;

    newTemplate.readTemplate(argReader);

    try (BufferedReader buffRead = new BufferedReader(new FileReader(argReader.getInputFile()))) {
      int lineNumber = 0;

      while ((line = buffRead.readLine()) != null) {
        wholeString = line.split(splitter);

        if (lineNumber >= 1) {
          Member member = extractMember(wholeString);

          newTemplate.writeTemplate(member, argReader);
        } else {
          wholeString = line.split(splitter);
          for (String columnHeader : wholeString) {
            columnHeader = removeQuote(columnHeader);
            newTemplate.addToken(columnHeader);
          }
        }
        lineNumber++;
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }

    System.out.println(SUCCESS + "." + File.separator + argReader.getOutputDir() + File.separator);
  }

  /**
   * Assigns strings values based on their location within the array.
   * @param wholeString an array of strings to be used to get each individual value.
   * @return a new member consisting of the data from wholeString.
   */
  private static Member extractMember(String[] wholeString) {
    String firstName = "";
    firstName = wholeString[0];

    String lastName = "";
    lastName = wholeString[1];

    String company = "";
    company = wholeString[2];

    String street = "";
    street = wholeString[3];

    String city = "";
    city = wholeString[4];

    String county = "";
    county = wholeString[5];

    String state = "";
    state = wholeString[6];

    String phoneTwo = "";
    phoneTwo = wholeString[7];

    String email = "";
    email = wholeString[8];

    String web = "";
    web = wholeString[9];

    String[] stateZipPhoneOne;
    String splitterTwo = ",";

    String zip = "";
    String phoneOne = "";

    if (state.length() > 2) {
      stateZipPhoneOne = state.split(splitterTwo);
      state = stateZipPhoneOne[0];
      zip = stateZipPhoneOne[1];
      phoneOne = stateZipPhoneOne[2];
    }

    firstName = removeQuote(firstName);
    lastName = removeQuote(lastName);
    company = removeQuote(company);
    street = removeQuote(street);
    city = removeQuote(city);
    county = removeQuote(county);
    state = removeQuote(state);
    zip = removeQuote(zip);
    phoneOne = removeQuote(phoneOne);
    phoneTwo = removeQuote(phoneTwo);
    email = removeQuote(email);
    web = removeQuote(web);

    return new Member(firstName, lastName, company, street, city, county, state, zip,
        phoneOne, phoneTwo, email, web);
  }

  /**
   * Removes any quote marks from the string.
   * @param value the string that will have the quote marks removed.
   * @return value without any quote marks.
   */
  private static String removeQuote(String value) {
    return value.replaceAll("\"", "");
  }
}
