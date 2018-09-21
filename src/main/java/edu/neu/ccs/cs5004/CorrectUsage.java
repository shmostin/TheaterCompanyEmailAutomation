package edu.neu.ccs.cs5004;

/**
 * Represents the error message the user will receive if the input was incorrect.
 */
public class CorrectUsage {

  /**
   * Generates the message that the user will receive if the input was incorrect.
   */
  protected static final String USAGE = "Usage:\n"
      + "\n" + "        --email                        only generate email messages\n"
      + "        --email-template <file>  accepts a filename that holds the email template.\n"
      + "          Required if --email is used\n" + "\n"
      + "        --letter                        only generate letters\n"
      + "        --letter-template <file> accepts a filename that holds the email template.\n"
      + "          Required if --letter is used\n" + "\n"
      + "        --output-dir <path> accepts the name of a folder, all output is placed in "
      + "this folder\n" + "\n"
      + "        --csv-file <path> accepts the name of the csv file to process\n" + "\n"
      + "Examples:\n" + "\n"
      + "       --email --email-template email-template.txt --output-dir emails --csv-file "
      + "customer.csv\n"
      + "       --letter --letter-template letter-template.txt --output-dir letters --csv-file "
      + "customer.csv\n";

}
