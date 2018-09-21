package edu.neu.ccs.cs5004;

import com.sun.tools.corba.se.idl.constExpr.Plus;

import java.util.Objects;

/**
 * Represents the managments of the arguments that the user can input into the command line.
 */
public class ArgReader implements ArgReaderInterface {

  private static final String EMAIL = "--email";
  private static final String EMAILTEMP = "--email-template";
  private static final String LETTER = "--letter";
  private static final String LETTERTEMP = "--letter-template";
  private static final String OUTPUTDIR = "--output-dir";
  private static final String EMAILS = "emails";
  private static final String LETTERS = "letters";
  private static final String CSVFILEFLAG = "--csv-file";

  private static final String ILLEGALTEMPLATE = "Error: --email-template provided but no "
      + "email template file was given.";
  private static final String ILLEGALDIRECTORY = "Error: --output-dir provided but no "
      + "directory was given";
  private static final String ILLEGALINPUT = "Error: --csv-file provided but not "
      + "customer file was given";

  private static final String NOTENOUGHARGUMENTS = "Not enough arguments.";

  private Boolean isFileError = true;
  private String inputFile;
  private String outputDir;
  private String templateFile;
  private String templateType;

  /**
   * reads in the arguments from the user looking for flag "--email" or "--letter".
   *
   * @param args the arguments from the user
   */
  public ArgReader(String[] args) {
    if (args.length < 7 ) {
      throw new IllegalArgumentException(NOTENOUGHARGUMENTS + "\n\n" + CorrectUsage.USAGE);
    }
    int argIndex = 1;
    while (argIndex < args.length) {
      if (args[argIndex].contains(EMAIL)) {
        emailArgChecker(args);
        this.templateType = "email";
      }
      if (args[argIndex].contains(LETTER)) {
        letterArgChecker(args);
        this.templateType = "letter";
      }
      argIndex++;
    }
    if (isError()) {
      throw new IllegalArgumentException(ILLEGALINPUT + "\n\n" + CorrectUsage.USAGE);
    }
  }

//  /**
//   * checks the arguments for all of the necessary email args. If found, the fields are set with
//   * these arguments.
//   *
//   * @param args the input arguments from system.in
//   */
//  public void argChecker(String[] args) throws IllegalArgumentException {
//    int argIndex = 0;
//    while (argIndex < args.length) {
//      if (EMAILTEMP.equals(args[argIndex]) || LETTERTEMP.equals(args[argIndex])) {
//        if (args[argIndex + 1].contains(".txt")) {
//          this.templateFile = args[argIndex + 1];
//          argIndex += 2;
//        } else {
//          throw new IllegalArgumentException(ILLEGALTEMPLATE + "\n\n" + CorrectUsage.USAGE);
//        }
//      } else if (OUTPUTDIR.equals(args[argIndex])) {
//        if (args[argIndex + 1].equals(EMAILS) || args[argIndex + 1].equals(LETTERS)) {
//          this.outputDir = args[argIndex + 1];
//          argIndex += 2;
//        } else {
//          throw new IllegalArgumentException(ILLEGALDIRECTORY + "\n\n" + CorrectUsage.USAGE);
//        }
//      } else if (CSVFILEFLAG.equals(args[argIndex])) {
//        if (args[argIndex + 1].contains(".csv")) {
//          this.inputFile = args[argIndex + 1];
//          argIndex += 2;
//        } else {
//          throw new IllegalArgumentException(ILLEGALINPUT + "\n\n" + CorrectUsage.USAGE);
//        }
//      } else {
//        argIndex += 1;
//      }
//    }
//  }


  /**
   * checks the arguments for all of the necessary email args. If found, the fields are set with
   * these arguments.
   *
   * @param args the input arguments from system.in
   */
  public void emailArgChecker(String[] args) throws IllegalArgumentException{
    int argIndex = 0;
    while (argIndex < args.length) {
      if (EMAILTEMP.equals(args[argIndex])) {
        if(args[argIndex + 1].contains(".txt")) {
          this.templateFile = args[argIndex + 1];
          argIndex += 2;
        } else throw new IllegalArgumentException(ILLEGALTEMPLATE + "\n\n" + CorrectUsage.USAGE);
      } else if (OUTPUTDIR.equals(args[argIndex])) {
        this.outputDir = args[argIndex + 1];
        argIndex += 2;
      } else if (CSVFILEFLAG.equals(args[argIndex]) && args[argIndex + 1].contains(".csv")) {
        this.inputFile = args[argIndex + 1];
        argIndex += 2;
      } else {
        argIndex += 1;
      }
    }
  }


  /**
   * checks the arguments for all of the necessary letter args. If found, the fields are set with
   * these arguments.
   *
   * @param args the input arguments from system.in
   */
  public void letterArgChecker(String[] args) {
    int argIndex = 0;
    while (argIndex < args.length) {
      if (LETTERTEMP.equals(args[argIndex]) && args[argIndex + 1].contains(".txt")) {
        this.templateFile = args[argIndex + 1];
        argIndex += 2;
      } else if (OUTPUTDIR.equals(args[argIndex])) {
        this.outputDir = args[argIndex + 1];
        argIndex += 2;
      } else if (CSVFILEFLAG.equals(args[argIndex]) && args[argIndex + 1].contains(".csv")) {
        this.inputFile = args[argIndex + 1];
        argIndex += 2;
      } else {
        argIndex += 1;
      }
    }
  }

  /**
   * checks the argument fields. If they are all null,
   * then there was an error with the given arguments.
   *
   * @return true if ALL arg fields are still null and false otherwise.
   */
  public Boolean isError() {
    if (!(this.outputDir == null) && !(this.templateFile == null)
        && !(this.inputFile == null) && !(this.templateType == null)) {
      this.isFileError = false;
    } else {
      this.isFileError = true;
    }
    return isFileError;
  }

  /**
   * Gets the name of the template file that the user entered on the command line.
   *
   * @return name of the template file
   */
  public String getTemplateFile() {
    return templateFile;
  }

  /**
   * Gets the name of the input file
   *
   * @return String input file name.
   */
  public String getInputFile() {
    return inputFile;
  }

  /**
   * Gets the type of template that the user entered on the command line.
   *
   * @return type of the template
   */
  public String getTemplateType() {
    return templateType;
  }

  /**
   * Gets the path that the user would like the files to be written to.
   *
   * @return string containing the path.
   */
  public String getOutputDir() {
    return outputDir;
  }

  /**
   * @return Whether there is an error is configuration.
   */
  public Boolean getIsError() {
    return isFileError;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ArgReader argReader = (ArgReader) obj;
    return Objects.equals(isFileError, argReader.isFileError)
        && Objects.equals(inputFile, argReader.inputFile)
        && Objects.equals(outputDir, argReader.outputDir)
        && Objects.equals(templateFile, argReader.templateFile)
        && Objects.equals(templateType, argReader.templateType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(isFileError, inputFile, outputDir, templateFile, templateType);
  }

  @Override
  public String toString() {
    return "ArgReader{"
        + "isError=" + isFileError + ", inputFile='" + inputFile + '\'' + ", outputDir='"
        + outputDir + '\'' + ", templateFile='" + templateFile + '\'' + ", templateType='"
        + templateType + '\'' + '}';
  }
}
