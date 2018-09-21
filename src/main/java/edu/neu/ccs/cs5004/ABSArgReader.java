//package edu.neu.ccs.cs5004;
//
//public abstract class ABSArgReader implements ArgReaderInterface {
//
//  private static final String EMAIL = "--email";
//  static final String EMAILTEMP = "--email-template";
//  private static final String LETTER = "--letter";
//  static final String LETTERTEMP = "--letter-template";
//  static final String OUTPUTDIR = "--output-dir";
//  protected static final String EMAILS = "emails";
//  protected static final String LETTERS = "letters";
//  static final String CSVFILEFLAG = "--csv-file";
//
//  static final String ILLEGALTEMPLATE = "Error: --email-template provided but no "
//      + "email template file was given.";
//  protected static final String ILLEGALDIRECTORY = "Error: --output-dir provided but no "
//      + "directory was given";
//  private static final String ILLEGALINPUT = "Error: Did not specify --email or --letter ";
//
//  private static final String WRONGNUMBEROFARGS = "Wrong number of arguments.";
//
//  private Boolean isFileError = true;
//  String inputFile;
//  String outputDir;
//  String templateFile;
//  private String templateType;
//
//  public ABSArgReader(Boolean isFileError, String inputFile, String outputDir, String templateFile, String templateType) {
//    this.isFileError = isFileError;
//    this.inputFile = inputFile;
//    this.outputDir = outputDir;
//    this.templateFile = templateFile;
//    this.templateType = templateType;
//  }
//
//
//  /**
//   * reads in the arguments from the user looking for flag "--email" or "--letter".
//   *
//   * @param args the arguments from the user
//   */
//  public void ArgReader(String[] args) {
//    Email email = new Email(this.isFileError, this.inputFile, this.outputDir, this.templateFile, this.templateType);
//    Letter letter = new Letter(this.isFileError, this.inputFile, this.outputDir, this.templateFile, this.templateType);
//
//    if (args.length != 7 ) {
//      throw new IllegalArgumentException(WRONGNUMBEROFARGS + "\n\n" + CorrectUsage.USAGE);
//    }
//    int argIndex = 1;
//    while (argIndex < args.length) {
//      if (args[argIndex].contains(EMAIL)) {
//        email.argChecker(args);
//        this.templateType = "email";
//      }
//      if (args[argIndex].contains(LETTER)) {
//        letter.argChecker(args);
//        this.templateType = "letter";
//      }
//      argIndex++;
//    }
//    if (isError()) {
//      throw new IllegalArgumentException(ILLEGALINPUT + "\n\n" + CorrectUsage.USAGE);
//    }
//  }
//
//
//
//  /**
//   * checks the argument fields. If they are all null,
//   * then there was an error with the given arguments.
//   *
//   * @return true if ALL arg fields are still null and false otherwise.
//   */
//  public Boolean isError() {
//    if (!(this.outputDir == null) && !(this.templateFile == null)
//        && !(this.inputFile == null) && !(this.templateType == null)) {
//      this.isFileError = false;
//    } else {
//      this.isFileError = true;
//    }
//    return isFileError;
//  }
//
//  /**
//   * Gets the name of the template file that the user entered on the command line.
//   *
//   * @return name of the template file
//   */
//  public String getTemplateFile() {
//    return templateFile;
//  }
//
//  /**
//   * Gets the name of the input file
//   *
//   * @return String input file name.
//   */
//  public String getInputFile() {
//    return inputFile;
//  }
//
//  /**
//   * Gets the type of template that the user entered on the command line.
//   *
//   * @return type of the template
//   */
//  public String getTemplateType() {
//    return templateType;
//  }
//
//  /**
//   * Gets the path that the user would like the files to be written to.
//   *
//   * @return string containing the path.
//   */
//  public String getOutputDir() {
//    return outputDir;
//  }
//
//  /**
//   * @return Whether there is an error is configuration.
//   */
//  public Boolean getIsError() {
//    return isFileError;
//  }
//
//}
