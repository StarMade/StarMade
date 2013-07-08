package paulscode.sound.utils;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class XMLNode
{
  private static SoundSystemLogger logger;
  private static boolean verbose = false;
  protected String name = "";
  protected String contents = "";
  protected HashMap<String, String> parameters = null;
  protected XMLNode next = null;
  
  public XMLNode(String XMLText)
  {
    int lBracket = XMLText.indexOf("<");
    int rBracket = XMLText.indexOf(">");
    if ((lBracket == -1) || (rBracket == -1)) {
      return;
    }
    if (rBracket <= lBracket)
    {
      errorMessage("Invalid XML syntax: '>' before '<'");
      return;
    }
    this.parameters = new HashMap();
    this.contents = XMLText.substring(lBracket + 1, rBracket);
    processTagContents(this.contents);
    if (verbose) {
      displayParameters();
    }
    if (rBracket + 1 >= XMLText.length()) {
      return;
    }
    String XMLRemainder = XMLText.substring(rBracket + 1);
    lBracket = XMLRemainder.indexOf("<");
    if (lBracket == -1) {
      return;
    }
    this.next = new XMLNode(XMLRemainder.substring(lBracket));
  }
  
  public String name()
  {
    if (this.name == null) {
      return "";
    }
    return this.name;
  }
  
  public String contents()
  {
    if (this.contents == null) {
      return "";
    }
    return this.contents;
  }
  
  public boolean hasNext()
  {
    return this.next != null;
  }
  
  public XMLNode next()
  {
    return this.next;
  }
  
  public String get(String parameter)
  {
    if (this.parameters == null) {
      return "";
    }
    return (String)this.parameters.get(parameter.toUpperCase());
  }
  
  public HashMap<String, String> parameters()
  {
    return this.parameters;
  }
  
  public void displayParameters()
  {
    System.out.println("Parameters for " + this.name + ":");
    Set<String> keys = this.parameters.keySet();
    Iterator<String> iter = keys.iterator();
    if (!iter.hasNext()) {
      System.out.println("    (none)");
    }
    while (iter.hasNext())
    {
      String par = (String)iter.next();
      String val = (String)this.parameters.get(par);
      System.out.println("    " + par + " = " + val);
    }
  }
  
  protected void processTagContents(String tagContents)
  {
    String[] splitTag = XMLParser.seperateWords(tagContents);
    if (splitTag.length > 0)
    {
      this.name = splitTag[0];
      if ((this.name.length() >= 3) && (this.name.substring(0, 3).equals("!--"))) {
        return;
      }
      int local_x = 1;
      while (local_x < splitTag.length)
      {
        String paramText = splitTag[local_x];
        if (paramText.equals("/")) {
          break;
        }
        if (paramText.contains("="))
        {
          String[] pair = paramText.split("=");
          if ((pair == null) || (pair.length == 0))
          {
            errorMessage("Invalid XML syntax: paramater null");
            return;
          }
          if (pair.length == 1)
          {
            if (local_x + 1 >= splitTag.length)
            {
              warningMessage("Value not specified for parameter '" + pair[0] + "'");
              this.parameters.put(pair[0].toUpperCase(), "");
              local_x++;
            }
            else
            {
              this.parameters.put(pair[0].toUpperCase(), splitTag[(local_x + 1)]);
              local_x += 2;
            }
          }
          else
          {
            this.parameters.put(pair[0].toUpperCase(), pair[1]);
            local_x++;
          }
        }
        else if (local_x + 1 >= splitTag.length)
        {
          warningMessage("Value not specified for parameter '" + splitTag[local_x] + "'");
          this.parameters.put(splitTag[local_x].toUpperCase(), "");
          local_x++;
        }
        else if (splitTag[(local_x + 1)].equals("="))
        {
          if (local_x + 2 >= splitTag.length)
          {
            warningMessage("Value not specified for parameter '" + splitTag[local_x] + "'");
            this.parameters.put(splitTag[local_x].toUpperCase(), "");
            local_x += 2;
          }
          else
          {
            this.parameters.put(splitTag[local_x].toUpperCase(), splitTag[(local_x + 2)]);
            local_x += 3;
          }
        }
        else if (splitTag[(local_x + 1)].contains("="))
        {
          String[] pair = splitTag[(local_x + 1)].split("=");
          if ((pair == null) || (pair.length < 1))
          {
            warningMessage("Value not specified for parameter '" + splitTag[local_x] + "'");
            this.parameters.put(splitTag[local_x].toUpperCase(), "");
            local_x += 2;
          }
          else
          {
            this.parameters.put(splitTag[local_x].toUpperCase(), pair[1]);
            local_x += 2;
          }
        }
        else
        {
          warningMessage("Value not specified for parameter '" + splitTag[local_x] + "'");
          this.parameters.put(splitTag[local_x].toUpperCase(), "");
          local_x++;
        }
      }
    }
  }
  
  public static void setVerbose(boolean val)
  {
    verbose = val;
  }
  
  protected static void errorMessage(String message)
  {
    if (logger == null) {
      logger = SoundSystemConfig.getLogger();
    }
    if (logger == null)
    {
      logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(logger);
    }
    logger.errorMessage("XMLNode", message, 0);
  }
  
  protected static void warningMessage(String message)
  {
    if (logger == null) {
      logger = SoundSystemConfig.getLogger();
    }
    if (logger == null)
    {
      logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(logger);
    }
    logger.importantMessage("Warning in class 'XMLNode': " + message, 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.utils.XMLNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */