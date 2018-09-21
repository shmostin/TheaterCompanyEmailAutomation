package edu.neu.ccs.cs5004;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent a template that will be written to an output file.
 */
public class Template implements ITemplate {
  private List<String> message;
  private List<String> tokens;


  /**
   * Create a new template given a list of strings.
   *
   * @param message a list of strings that make up the message.
   * @param tokens a list of string that will be used to identify placeholders.
   */
  public Template(List<String> message, List<String> tokens) {
    this.message = message;
    this.tokens = tokens;
  }

  public Template() {
    this.message = new ArrayList<>();
    this.tokens = new ArrayList<>();
  }

  /**
   * Gets the message from the template.
   *
   * @return the list of strings that make up the template.
   */
  public List<String> getMessage() {
    return message;
  }

  /**
   * Reads the template the user identified from the command line.
   *
   * @param args the arguments that the user entered on the command line.
   * @throws IOException when there is an invalid input or output argument.
   */
  public void readTemplate(ArgReader args) throws IOException {
    String line;

    try (BufferedReader templateFile = new BufferedReader(new FileReader(args.getTemplateFile()))) {

      while ((line = templateFile.readLine()) != null) {
        this.getMessage().add(line);
      }

    } catch (FileNotFoundException fnfe) {
      System.out.println("*** A template file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    }
  }

  /**
   * Creates a file at the specified location the user imputed on the command line.
   *
   * @param member the member that the template will be written to.
   * @param args   the arguments that the user entered on the command line.
   * @throws IOException when there is an invalid input or output argument.
   */
  public void writeTemplate(Member member, ArgReader args) throws IOException {
    Template templateCopy = new Template();
    String lineCopy;

    for (String line : this.getMessage()) {
      lineCopy = line;
      for (String token : this.tokens) {
        Pattern genericPattern = Pattern.compile(token);

        Matcher genericMatcher = genericPattern.matcher(lineCopy);
        if (genericMatcher.find()) {
          lineCopy = genericMatcher.replaceAll(member.findValue(token));
        }
      }
      templateCopy.message.add(lineCopy);
    }

    templateCopy.outputTemplate(member, args);
  }

  /**
   * Outputs a file for the template to the specified output file location provided by the user on
   * the command line.
   *
   * @param args the arguments that the user entered on the command line.
   */

  private void outputTemplate(Member member, ArgReader args) throws IOException {
    String fileName = String.format("%s, %s - %s.txt",
        member.getLastName(), member.getFirstName(), args.getTemplateType());
    File filePath = new File("." + File.separator + args.getOutputDir()
        + File.separator + fileName);

    filePath.getParentFile().mkdirs();
    try (BufferedWriter outputFile =
             new BufferedWriter(new FileWriter(filePath))) {
      for (String line : this.getMessage()) {
        outputFile.write(line);
        outputFile.write('\n');
      }

    } catch (FileNotFoundException fnfe) {
      System.out.println("*** A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    }
  }

  protected void addToken(String token) {
    token = String.format("\\[\\[%s]]", token);
    this.tokens.add(token);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Template template = (Template) obj;

    return message.equals(template.message);
  }

  @Override
  public int hashCode() {
    return message.hashCode();
  }

  @Override
  public String toString() {
    return "Template{" + "message=" + message + '}';
  }
}
