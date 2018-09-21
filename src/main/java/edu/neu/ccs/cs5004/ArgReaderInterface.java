package edu.neu.ccs.cs5004;

public interface ArgReaderInterface {
  /**
   * checks the arguments for all of the necessary email args. If found, the fields are set with
   * these arguments.
   *
   * @param args the input arguments from system.in
   */
  void emailArgChecker(String[] args) throws IllegalArgumentException ;

  /**
   * check the arguments for all the necessary letter args. If foudn, the fields are set with these
   * arguments.
   * @param args the input arguments from system.in
   * @throws IllegalArgumentException
   */
  void letterArgChecker(String[] args) throws IllegalArgumentException;

  /**
   * checks the argument fields. If they are all null,
   * then there was an error with the given arguments.
   *
   * @return true if ALL arg fields are still null and false otherwise.
   */
  Boolean isError();

  /**
   * Gets the name of the template file that the user entered on the command line.
   *
   * @return name of the template file
   */
  String getTemplateFile();

  /**
   * Gets the name of the input file
   *
   * @return String input file name.
   */
  String getInputFile();

  /**
   * Gets the type of template that the user entered on the command line.
   *
   * @return type of the template
   */
  String getTemplateType();
  /**
   * Gets the path that the user would like the files to be written to.
   *
   * @return string containing the path.
   */
  String getOutputDir();

  /**
   * @return Whether there is an error is configuration.
   */
  Boolean getIsError();
}
