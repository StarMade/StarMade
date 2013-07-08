package paulscode.sound;

import java.io.PrintStream;

public class SoundSystemLogger
{
  public void message(String message, int indent)
  {
    String spacer = "";
    for (int local_x = 0; local_x < indent; local_x++) {
      spacer = spacer + "    ";
    }
    String messageText = spacer + message;
    System.out.println(messageText);
  }
  
  public void importantMessage(String message, int indent)
  {
    String spacer = "";
    for (int local_x = 0; local_x < indent; local_x++) {
      spacer = spacer + "    ";
    }
    String messageText = spacer + message;
    System.out.println(messageText);
  }
  
  public boolean errorCheck(boolean error, String classname, String message, int indent)
  {
    if (error) {
      errorMessage(classname, message, indent);
    }
    return error;
  }
  
  public void errorMessage(String classname, String message, int indent)
  {
    String spacer = "";
    for (int local_x = 0; local_x < indent; local_x++) {
      spacer = spacer + "    ";
    }
    String headerLine = spacer + "Error in class '" + classname + "'";
    String messageText = "    " + spacer + message;
    System.out.println(headerLine);
    System.out.println(messageText);
  }
  
  public void printStackTrace(Exception local_e, int indent)
  {
    printExceptionMessage(local_e, indent);
    importantMessage("STACK TRACE:", indent);
    if (local_e == null) {
      return;
    }
    StackTraceElement[] stack = local_e.getStackTrace();
    if (stack == null) {
      return;
    }
    for (int local_x = 0; local_x < stack.length; local_x++)
    {
      StackTraceElement line = stack[local_x];
      if (line != null) {
        message(line.toString(), indent + 1);
      }
    }
  }
  
  public void printExceptionMessage(Exception local_e, int indent)
  {
    importantMessage("ERROR MESSAGE:", indent);
    if (local_e.getMessage() == null) {
      message("(none)", indent + 1);
    } else {
      message(local_e.getMessage(), indent + 1);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.SoundSystemLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */