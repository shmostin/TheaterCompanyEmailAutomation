package edu.neu.ccs.cs5004;

import java.io.IOException;
import java.util.List;

/**
 * Represents the interface for the template methods.
 */
public interface ITemplate {

  /**
   * Gets the message from the template.
   *
   * @return the list of strings that make up the template.
   */
  List<String> getMessage();

  /**
   * Reads the template the user identified from the command line.
   *
   * @param args the arguments that the user entered on the command line.
   * @throws IOException when there is an invalid input or output argument.
   */
  void readTemplate(ArgReader args) throws IOException;

  /**
   * Creates a file at the specified location the user imputed on the command line.
   *
   * @param member the member that the template will be written to.
   * @param args   the arguments that the user entered on the command line.
   * @throws IOException when there is an invalid input or output argument.
   */
  void writeTemplate(Member member, ArgReader args) throws IOException;
}
