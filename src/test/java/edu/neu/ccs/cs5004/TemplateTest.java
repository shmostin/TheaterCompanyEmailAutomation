package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateTest {
  ArgReader argsLetter;
  ArgReader argsEmail;
  ArgReader argsfnfe;
  Member member;
  Template template;
  Template templateEquals;
  List<String> equals;
  List<String> diff;

  private Template sameRefOpen;
  private Template sameStateAsOpen;
  private Template yetAnotherOpen;
  private Template diffOpen;
  private Template nullOpen = null;

  @Before
  public void setUp() throws Exception {

    String[] argsArrayEmail = new String[7];
    argsArrayEmail[0] = "--email";
    argsArrayEmail[1] = "--email-template";
    argsArrayEmail[2] = "email-template.txt";
    argsArrayEmail[3] = "--output-dir";
    argsArrayEmail[4] = "emails";
    argsArrayEmail[5] = "--csv-file";
    argsArrayEmail[6] = "theater-members.csv";

    String[] argsArrayLetter = new String[7];
    argsArrayLetter[0] = "--letter";
    argsArrayLetter[1] = "--letter-template";
    argsArrayLetter[2] = "letter-template-expanded.txt";
    argsArrayLetter[3] = "--output-dir";
    argsArrayLetter[4] = "letters";
    argsArrayLetter[5] = "--csv-file";
    argsArrayLetter[6] = "theater-company-members.csv";

    String[] argsArrayfnfe = new String[7];
    argsArrayfnfe[0] = "--letter";
    argsArrayfnfe[1] = "--letter-template";
    argsArrayfnfe[2] = "let.txt";
    argsArrayfnfe[3] = "--output-dir";
    argsArrayfnfe[4] = "letters";
    argsArrayfnfe[5] = "--csv-file";
    argsArrayfnfe[6] = "cust.csv";

    argsLetter = new ArgReader(argsArrayLetter);
    argsEmail = new ArgReader(argsArrayEmail);
    argsfnfe = new ArgReader(argsArrayfnfe);

    member = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    template = new Template();
    template.addToken("first_name");
    template.addToken("last_name");
    template.addToken("email");
    template.addToken("company_name");
    template.addToken("address");
    template.addToken("city");
    template.addToken("state");
    template.addToken("zip");
    template.addToken("phone1");
    template.addToken("phone2");
    template.addToken("web");

    equals = new ArrayList<>();
    equals.add("zero");
    equals.add("one");

    diff = new ArrayList<>();
    diff.add("I'm different!");

    templateEquals = new Template(equals, diff);
    sameRefOpen = templateEquals;
    sameStateAsOpen = new Template(equals, diff);
    yetAnotherOpen = new Template(equals, diff);
    diffOpen = new Template(diff, equals);
  }

  @Test
  public void readTemplate() throws IOException {
    List<String> expected = new ArrayList<>();
    expected.add("[[company_name]].");
    expected.add("[[first_name]] [[last_name]]");
    expected.add("[[address]], [[city]],");
    expected.add("[[county]], [[state]], [[zip]]");
    expected.add("([[email]])");
    expected.add("");
    expected.add("Dear [[first_name]] [[last_name]], ");
    expected.add("");
    expected.add("    Please find enclosed your complementary tickets to \"A Streetcar");
    expected.add("    Named Desire\" directed by John Jarmush and Susan Mae. We look");
    expected.add("    forward to seeing you at one of our showings at our New York");
    expected.add("    theater between March 1st and April 10th.");
    expected.add("");
    expected.add("[[phone1]] [[phone2]] [[web]]");
    expected.add("");
    expected.add("Sincerely, ");
    expected.add("");
    expected.add("");

    template.readTemplate(argsLetter);
    Assert.assertEquals(expected, template.getMessage());

    Template email = new Template();
    expected.clear();

    expected.add("From:thetheatercompany@tc.com");
    expected.add("To:[[email]]");
    expected.add("Subject:Information on this years members only show!");
    expected.add("");
    expected.add("Dear [[first_name]] [[last_name]], ");
    expected.add("");
    expected.add("   This year's members only theater show will showcase \"A Streetcar");
    expected.add("   Named Desire\" directed by John Jarmush and Susan Mae at our New");
    expected.add("   York location between March 1st and April 10th.  Your complementary");
    expected.add("   tickets for the show are on their way through mail and should");
    expected.add("   reach you within the next couple of days.");
    expected.add("");
    expected.add("   Sincerely, ");
    expected.add("");

    email.readTemplate(argsEmail);
    Assert.assertEquals(expected, email.getMessage());

  }

  @Test
  public void writeTemplate() throws IOException {
    template.readTemplate(argsLetter);
    template.writeTemplate(member, argsLetter);

    Template email = new Template();
    email.addToken("first_name");
    email.addToken("last_name");
    email.addToken("email");
    email.readTemplate(argsEmail);
    email.writeTemplate(member, argsEmail);
  }

  @Test
  public void FileNotFound() throws IOException {
    template.readTemplate(argsfnfe);
  }

  @Test
  public void equals() {
    Assert.assertTrue(templateEquals.equals(templateEquals)); // reflexivity
    Assert.assertTrue(templateEquals.equals(sameRefOpen)); // trivial condition both reference the same object
    Assert.assertTrue(templateEquals.equals(sameStateAsOpen)); // both objects should have the same state
    Assert.assertTrue(sameStateAsOpen.equals(templateEquals)); //symmetry
    Assert.assertEquals(templateEquals.equals(sameStateAsOpen) && sameStateAsOpen.equals(yetAnotherOpen), yetAnotherOpen.equals(templateEquals)); //transitivity
    Assert.assertFalse(templateEquals.equals(diffOpen)); //objects have different state
    Assert.assertFalse(templateEquals.equals(nullOpen)); //is NOT null
    Assert.assertFalse(templateEquals.equals("a string"));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(templateEquals.equals(sameRefOpen), templateEquals.hashCode() == sameRefOpen.hashCode()); //same objects have the same hashCode
    Assert.assertEquals(templateEquals.equals(sameStateAsOpen), templateEquals.hashCode() == sameStateAsOpen.hashCode()); //equal objects have the same hashCode
  }

  @Test
  public void testToString() {
    String tempRes = "Template{" + "message=" + templateEquals.getMessage() + '}';
    Assert.assertEquals(tempRes, templateEquals.toString());
  }
}