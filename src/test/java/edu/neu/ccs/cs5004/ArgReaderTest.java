package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArgReaderTest {
  String[] argsOrigEmail = {"--email", "--email-template", "email-template.txt", "--output-dir",
      "emails", "--csv-file", "theater-company-members.csv"};
  String[] sameStateEmail = {"--email", "--email-template", "email-template.txt", "--output-dir",
      "emails", "--csv-file", "theater-company-members.csv"};
  String[] differentEmail = {"--email-template", "email-template.txt", "--email", "--output-dir",
      "emails", "--csv-file", "theater-company-members.csv"};
  String[] incorrectEmail = {"--email", "--letter-template", "letter-template.txt",
      "--output-dir letters"};
  String[] argsOrigLetter = {"--letter", "--letter-template", "letter-template.txt",
      "--output-dir", "letters", "--csv-file", "theater-company-members.csv"};
  String[] noTemplate = {"--letter", "--letter-template",
      "--output-dir", "letters", "--csv-file", "theater-company-members.csv"};
  String[] noTemplateTwo = {"--email", "--email-template", "email-template.txt",
      "--output-dir", "letters", "--csv-file", "theater-company-members."};
  String[] differetLetter = {"--letter-template", "letter-template.txt", "--letter", "--output-dir",
      "letters", "--csv-file", "theater-company-members.csv"};
  String[] noTypeFlag = {"--email", "--email-template", "email-template.txt", "--output-dir",
      "emails", "--csv-file", "theater-company-members.csv"};
  String[] absoluteGarbage = {"skjhfsjd", "skjhfskjhd", "kshfskdjhfj", "skjdhfsdjh",
      "ksjhdfksdhf", "dsjfghsd", "ksjdhfsk"};
  String[] nothing = {};


  ArgReader original;
  ArgReader sameAsOrig;
  ArgReader sameStateAsOrig;
  ArgReader another;
  ArgReader different;

  ArgReader letterOrig;
  ArgReader letterDiff;
  ArgReader wrongArgs;
  ArgReader noTypeFlags;
  ArgReader noTemplateArg;

  @Before
  public void setUp() throws Exception {
    original = new ArgReader(argsOrigEmail);
    sameAsOrig = original;
    sameStateAsOrig = new ArgReader(sameStateEmail);
    another = new ArgReader(argsOrigEmail);
    different = new ArgReader(differentEmail);
    letterOrig = new ArgReader(argsOrigLetter);
    letterDiff = new ArgReader(differetLetter);
    noTypeFlags = new ArgReader(noTypeFlag);
  }

  @Test
  public void argChecker() {
    Assert.assertEquals("theater-company-members.csv", original.getInputFile());
    System.out.println(original.getInputFile());
  }

  @Test(expected = IllegalArgumentException.class)
  public void exceptions() {
    noTemplateArg = new ArgReader(noTemplate);
    noTemplateArg = new ArgReader(noTemplateTwo);
    noTemplateArg = new ArgReader(incorrectEmail);
    noTemplateArg = new ArgReader(absoluteGarbage);
    noTemplateArg = new ArgReader(nothing);
  }

  @Test
  public void isError() {
    Assert.assertFalse(original.isError());
    Assert.assertFalse(noTypeFlags.isError());
  }

  @Test
  public void getTemplateFile() {
    System.out.println(letterOrig.getTemplateFile());
    System.out.println(original.getTemplateFile());
    System.out.println(different.getTemplateFile());
    Assert.assertEquals("email-template.txt", different.getTemplateFile());
  }

  @Test
  public void getInputFile() {
    System.out.println(letterOrig.getInputFile());
    System.out.println(different.getInputFile());
    Assert.assertEquals("theater-company-members.csv", different.getInputFile());
  }

  @Test
  public void getTemplateType() {
    System.out.println(different.getTemplateType());
    Assert.assertEquals("email", different.getTemplateType());
  }

  @Test
  public void getOutputDir() {
    System.out.println(different.getOutputDir());
    Assert.assertEquals("emails", different.getOutputDir());
    Assert.assertEquals("letters", letterDiff.getOutputDir());
  }

  @Test
  public void equals() {
    Assert.assertEquals(original, sameAsOrig); //reflexive
    Assert.assertEquals(original, sameStateAsOrig); //symmetrical
    Assert.assertEquals(original.equals(sameAsOrig) && sameAsOrig.equals(another),
        another.equals(original));//Transitive
    Assert.assertFalse(original.equals(null));
    Assert.assertFalse(original.equals(wrongArgs));
    Assert.assertFalse(original.equals("a string"));
    Assert.assertFalse(original.equals(incorrectEmail));
  }

  @Test
  public void hashCodeTest() {
    Assert.assertEquals(original.equals(sameAsOrig),
        original.hashCode() == sameAsOrig.hashCode()); //same objs have same hash
    Assert.assertEquals((original.equals(sameStateAsOrig)),
        original.hashCode() == sameStateAsOrig.hashCode());
  }

  @Test
  public void toStringTest() {
    String testString = "ArgReader{" + "isError=" + original.getIsError()
        + ", inputFile='" + original.getInputFile() + '\'' + ", outputDir='"
        + original.getOutputDir() + '\'' + ", templateFile='" + original.getTemplateFile() + '\''
        + ", templateType='" + original.getTemplateType() + '\'' + '}';

    Assert.assertEquals(testString, original.toString());
  }
}