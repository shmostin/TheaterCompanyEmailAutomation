package edu.neu.ccs.cs5004;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class TextReaderTest {

  String[] emailArgs = {"--email", "--email-template", "email-template.txt", "--output-dir",
      "emails", "--csv-file", "theater-members.csv"};

  String[] letterArgs = {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
      "letters", "--csv-file", "theater-members.csv"};

  String[] errorArgs = {"-el", "--email-template", "email-template.txt", "--output-dir",
      "emails", "--csv-file", "theater-members.csv"};

  String[] fnfeArgs = {"--email", "--email-template", "letter-template.txt", "--output-dir",
      "emails", "--csv-file", "theater-company-members-test-only.csv"};

  ArgReader email;
  ArgReader letter;
  ArgReader error;
  ArgReader fnfe;

  Template template;

  Member member;

  @Before
  public void setUp() throws Exception {

    email = new ArgReader(emailArgs);
    letter = new ArgReader(letterArgs);
    error = new ArgReader(errorArgs);
    fnfe = new ArgReader(fnfeArgs);
    error.getIsError();
    template = new Template();

    member  = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
  }

  @Test
  public void testMain() throws IOException {
    String[] argsArrayEmail = new String[7];
    argsArrayEmail[0] = "--email";
    argsArrayEmail[1] = "--email-template";
    argsArrayEmail[2] = "email-template.txt";
    argsArrayEmail[3] = "--output-dir";
    argsArrayEmail[4] = "emails";
    argsArrayEmail[5] = "--csv-file";
    argsArrayEmail[6] = "theater-company-members.csv";


    String[] argsArrayLetter = new String[7];
    argsArrayLetter[0] = "--letter";
    argsArrayLetter[1] = "--letter-template";
    argsArrayLetter[2] = "letter-template.txt";
    argsArrayLetter[3] = "--output-dir";
    argsArrayLetter[4] = "letters";
    argsArrayLetter[5] = "--csv-file";
    argsArrayLetter[6] = "theater-company-members.csv";

    TextReader.main(argsArrayLetter);
    TextReader.main(argsArrayEmail);

  }

  @Test
  public void mainE() throws IOException {
    //TextReader.main(fnfeArgs);
  }
}