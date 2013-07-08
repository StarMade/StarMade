package paulscode.sound.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class XMLParser
{
  private static SoundSystemLogger logger;
  
  public static XMLNode parseXML(URL xmlFile)
  {
    return new XMLNode(trimSpaces(readString(xmlFile)));
  }
  
  public static String getRawXML(URL xmlFile)
  {
    return trimSpaces(readString(xmlFile));
  }
  
  private static String readString(URL file)
  {
    InputStream local_in = null;
    try
    {
      local_in = file.openStream();
    }
    catch (IOException ioe)
    {
      errorMessage("Unable to open inputstream in method 'readString'");
      return null;
    }
    ByteArrayOutputStream ioe = new ByteArrayOutputStream();
    ByteArrayInputStream bin = null;
    BufferedReader bufRead = null;
    String fullString = "";
    byte[] buffer = new byte[4096];
    int read = 0;
    try
    {
      do
      {
        read = local_in.read(buffer);
        if (read != -1) {
          ioe.write(buffer, 0, read);
        }
      } while (read != -1);
      bin = new ByteArrayInputStream(ioe.toByteArray());
      bufRead = new BufferedReader(new InputStreamReader(bin));
      String line = "";
      do
      {
        line = bufRead.readLine();
        if (line != null) {
          fullString = fullString + "\n" + line;
        }
      } while (line != null);
    }
    catch (IOException line)
    {
      line.printStackTrace();
    }
    if (local_in != null) {
      try
      {
        local_in.close();
      }
      catch (Exception line) {}
    }
    if (ioe != null) {
      try
      {
        ioe.close();
      }
      catch (Exception line) {}
    }
    if (bin != null) {
      try
      {
        bin.close();
      }
      catch (Exception line) {}
    }
    if (bufRead != null) {
      try
      {
        bufRead.close();
      }
      catch (Exception line) {}
    }
    local_in = null;
    ioe = null;
    bin = null;
    bufRead = null;
    return fullString;
  }
  
  public static String trimSpaces(String text)
  {
    String[] splitText = seperateWords(text);
    if ((splitText == null) || (splitText.length == 0)) {
      return "";
    }
    String parsedText = splitText[0];
    for (int local_x = 1; local_x < splitText.length; local_x++) {
      parsedText = parsedText + " " + splitText[local_x];
    }
    return parsedText;
  }
  
  public static String[] seperateWords(String text)
  {
    if (text == null) {
      return null;
    }
    while ((text.length() > 0) && (text.substring(0, 1).matches("\\s"))) {
      text = text.substring(1);
    }
    if (text.length() == 0) {
      return null;
    }
    String[] splitText = text.split("\\s+");
    return splitText;
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
    logger.errorMessage("XMLParser", message, 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.utils.XMLParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */