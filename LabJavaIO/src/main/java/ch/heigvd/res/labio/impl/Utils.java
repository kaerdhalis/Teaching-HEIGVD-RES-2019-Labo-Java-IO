package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {

    String[] newLines = new String[2];

    newLines[0] = "";
    newLines[1] = lines;

        int val = lines.indexOf("\r\n");
     if(val!=-1) {
      newLines[0] = lines.substring(0, val + 2);
      newLines[1] = lines.substring(val + 2);
        return newLines;
      }

      val = lines.indexOf("\r");
    if(val!=-1) {
      newLines[0] = lines.substring(0, val + 1);
      newLines[1] = lines.substring(val + 1);
      return newLines;
    }

    val = lines.indexOf("\n");
    if(val!=-1) {
      newLines[0] = lines.substring(0, val + 1);
      newLines[1] = lines.substring(val + 1);
      return newLines;
    }
    
    return newLines;
  }

}
