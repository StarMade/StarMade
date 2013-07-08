package paulscode.sound.utils;

import java.net.URL;
import paulscode.sound.CommandObject;
import paulscode.sound.FilenameURL;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.SoundSystemLogger;

public class SoundSystemLoader
{
  private static SoundSystemLogger logger;
  private static boolean verbose = false;
  
  public static SoundSystem loadXML(URL xmlFile, SoundSystem local_s)
  {
    if (xmlFile == null)
    {
      errorMessage("Parameter 'xmlFile' null in method 'loadXML'");
      return null;
    }
    XMLNode commands = XMLParser.parseXML(xmlFile);
    if (commands == null)
    {
      warningMessage("No commands found in XML file");
      return null;
    }
    Class local_c = null;
    while (commands != null)
    {
      String command = commands.name().toUpperCase();
      if ((command != null) && (!command.substring(0, 1).equals("/"))) {
        if (command.equals("ADDLIBRARY"))
        {
          if (verbose)
          {
            message("SoundSystemLoader.loadXML:  addLibrary", 0);
            message("Class name:  " + commands.get("CLASSNAME"), 1);
          }
          try
          {
            local_c = Class.forName(commands.get("CLASSNAME"));
            if (verbose) {
              message("Command:  SoundSystemConfig.addLibrary( " + commands.get("CLASSNAME") + ".class );", 1);
            }
            SoundSystemConfig.addLibrary(local_c);
          }
          catch (ClassNotFoundException cnfe)
          {
            errorMessage("Unable to add library plug-in in method 'loadXML':  Class name '" + commands.get("CLASSNAME") + "' not found.");
          }
          catch (SoundSystemException cnfe)
          {
            printStackTrace(cnfe);
          }
        }
        else if (command.equals("SETCODEC"))
        {
          if (verbose)
          {
            message("SoundSystemLoader.loadXML:  setCodec", 0);
            message("Extension: " + commands.get("EXTENSION"), 1);
            message("Class name: " + commands.get("CLASSNAME"), 1);
          }
          try
          {
            local_c = Class.forName(commands.get("CLASSNAME"));
            if (verbose) {
              message("Command:  SoundSystemConfig.setCodec( \"" + commands.get("EXTENSION") + "\", " + commands.get("CLASSNAME") + ".class );", 1);
            }
            SoundSystemConfig.setCodec(commands.get("EXTENSION"), local_c);
          }
          catch (ClassNotFoundException cnfe)
          {
            errorMessage("Unable to set codec plug-in for extension '" + commands.get("EXTENSION") + "' in method 'loadXML':  Class name '" + commands.get("CLASSNAME") + "' not found.");
          }
          catch (SoundSystemException cnfe)
          {
            printStackTrace(cnfe);
          }
        }
        else if (command.equals("CREATE"))
        {
          if (verbose) {
            message("SoundSystemLoader.loadXML:  create", 0);
          }
          if (local_s != null) {
            local_s.cleanup();
          }
          local_s = null;
          String parameter = commands.get("CLASSNAME");
          if ((parameter != null) && (!parameter.equals("")))
          {
            try
            {
              local_c = Class.forName(parameter);
              if (verbose)
              {
                message("Command:  s = (SoundSystem) new " + parameter + "();", 1);
                local_s = (SoundSystem)local_c.newInstance();
              }
            }
            catch (ClassNotFoundException cnfe)
            {
              printStackTrace(cnfe);
            }
            catch (InstantiationException cnfe)
            {
              printStackTrace(cnfe);
            }
            catch (IllegalAccessException cnfe)
            {
              printStackTrace(cnfe);
            }
            if (verbose)
            {
              message("Unable to instantiate the Sound System in method 'loadXML'  Returning null.", 1);
              if (local_s != null) {
                local_s.cleanup();
              }
              local_s = null;
              return null;
            }
          }
          else
          {
            if (verbose) {
              message("Command:  s = new SoundSystem();", 1);
            }
            local_s = new SoundSystem();
          }
        }
        else if (command.equals("LOADSOUND"))
        {
          if (verbose)
          {
            message("SoundSystemLoader.loadXML:  loadSound", 0);
            message("Filename: " + commands.get("FILENAME"), 1);
          }
          if (local_s == null)
          {
            errorMessage("Encountered 'loadSound' command before SoundSystem was instantiated in method 'loadXML'.  returning null.");
            return null;
          }
          if (verbose) {
            message("Command:  s.loadSound( \"" + commands.get("FILENAME") + "\" );", 1);
          }
          local_s.loadSound(commands.get("FILENAME"));
        }
        else if (command.equals("NEWSOURCE"))
        {
          if (verbose) {
            message("SoundSystemLoader.loadXML:  newSource", 0);
          }
          if (local_s == null)
          {
            errorMessage("Encountered 'newSource' command before SoundSystem was instantiated in method 'loadXML'.  returning null.");
            return null;
          }
          boolean cnfe = false;
          boolean toStream = false;
          boolean toLoop = false;
          String sourcename = "";
          String filename = "";
          float local_x = 0.0F;
          float local_y = 0.0F;
          float local_z = 0.0F;
          int attModel = SoundSystemConfig.getDefaultAttenuation();
          float distOrRoll = 0.0F;
          String parameter = commands.get("PRIORITY");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("PRIORITY: " + parameter, 1);
            }
            if (parameter.toUpperCase().equals("TRUE")) {
              cnfe = true;
            }
          }
          parameter = commands.get("TOSTREAM");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("TOSTREAM: " + parameter, 1);
            }
            if (parameter.toUpperCase().equals("TRUE")) {
              toStream = true;
            }
          }
          parameter = commands.get("TOLOOP");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("TOLOOP: " + parameter, 1);
            }
            if (parameter.toUpperCase().equals("TRUE")) {
              toLoop = true;
            }
          }
          parameter = commands.get("SOURCENAME");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("SOURCENAME: " + parameter, 1);
            }
            sourcename = parameter;
          }
          parameter = commands.get("FILENAME");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("FILENAME: " + parameter, 1);
            }
            filename = parameter;
          }
          parameter = commands.get("X");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("X: " + parameter, 1);
            }
            try
            {
              local_x = Float.parseFloat(parameter);
            }
            catch (NumberFormatException nfe)
            {
              errorMessage("Error parsing float 'X' from String '" + parameter + "' in " + "method 'loadXML'.  Using x=0");
              local_x = 0.0F;
            }
          }
          parameter = commands.get("Y");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("Y: " + parameter, 1);
            }
            try
            {
              local_y = Float.parseFloat(parameter);
            }
            catch (NumberFormatException nfe)
            {
              errorMessage("Error parsing float 'Y' from String '" + parameter + "' in " + "method 'loadXML'.  Using y=0");
              local_y = 0.0F;
            }
          }
          parameter = commands.get("Z");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("Z: " + parameter, 1);
            }
            try
            {
              local_x = Float.parseFloat(parameter);
            }
            catch (NumberFormatException nfe)
            {
              errorMessage("Error parsing float 'Z' from String '" + parameter + "' in " + "method 'loadXML'.  Using z=0");
              local_z = 0.0F;
            }
          }
          parameter = commands.get("ATTMODEL");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("ATTMODEL: " + parameter, 1);
            }
            if (parameter.toUpperCase().contains("NONE")) {
              attModel = 0;
            } else if (parameter.toUpperCase().contains("LINEAR")) {
              attModel = 2;
            } else if (parameter.toUpperCase().contains("ROLLOFF")) {
              attModel = 1;
            }
          }
          parameter = commands.get("DISTORROLL");
          if ((parameter != null) && (!parameter.equals("")))
          {
            if (verbose) {
              message("DISTORROLL: " + parameter, 1);
            }
            try
            {
              distOrRoll = Float.parseFloat(parameter);
            }
            catch (NumberFormatException nfe)
            {
              errorMessage("Error parsing float 'DISTORROLL' from String '" + parameter + "' in method " + "'loadXML'.  Using default value.");
              distOrRoll = 0.0F;
              if (attModel == 2) {
                distOrRoll = SoundSystemConfig.getDefaultFadeDistance();
              } else if (attModel == 1) {
                distOrRoll = SoundSystemConfig.getDefaultAttenuation();
              }
            }
          }
          else if (attModel == 2)
          {
            distOrRoll = SoundSystemConfig.getDefaultFadeDistance();
          }
          else if (attModel == 1)
          {
            distOrRoll = SoundSystemConfig.getDefaultAttenuation();
          }
          if (sourcename.equals(""))
          {
            errorMessage("Parameter 'SOURCENAME' not specified for 'NEWSOURCE' tag in method 'loadXML.  Unable to create new source.");
          }
          else if (filename.equals(""))
          {
            errorMessage("Parameter 'FILENAME' not specified for 'NEWSOURCE' tag in method 'loadXML.  Unable to create new source.");
          }
          else
          {
            if (verbose) {
              message("Command:  s.CommandQueue( new paulscode.sound.CommandObject( paulscode.sound.CommandObject.NEW_SOURCE, " + cnfe + ", " + toStream + ", " + toLoop + ", \"" + sourcename + "\", " + "new paulscode.sound.FilenameURL( \"" + filename + "\" ), " + local_x + ", " + local_y + ", " + local_z + ", " + attModel + ", " + distOrRoll + " ) );", 1);
            }
            local_s.CommandQueue(new CommandObject(10, cnfe, toStream, toLoop, sourcename, new FilenameURL(filename), local_x, local_y, local_z, attModel, distOrRoll));
            if (verbose) {
              message("Command:  s.interruptCommandThread();", 1);
            }
            local_s.interruptCommandThread();
          }
        }
        else if ((command.length() >= 3) && (command.substring(0, 3).equals("!--")))
        {
          if (verbose)
          {
            message("SoundSystemLoader.loadXML:  comment", 0);
            if (commands.contents().length() > 6) {
              message(commands.contents().substring(3, commands.contents().length() - 2), 1);
            }
          }
        }
        else if (verbose)
        {
          message("SoundSystemLoader.loadXML:  " + command, 0);
          message("Unrecognized tag.", 1);
        }
        else
        {
          warningMessage("Command '" + command + "' not " + "recognized in method 'loadXML'");
        }
      }
      commands = commands.next();
    }
    return local_s;
  }
  
  public static void setVerbose(boolean val)
  {
    verbose = val;
  }
  
  protected static void message(String message, int indent)
  {
    if (logger == null) {
      logger = SoundSystemConfig.getLogger();
    }
    if (logger == null)
    {
      logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(logger);
    }
    logger.message(message, indent);
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
    logger.errorMessage("SoundSystemLoader", message, 0);
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
    logger.importantMessage("Warning in class 'SoundSystemLoader': " + message, 0);
  }
  
  protected static void printStackTrace(Exception local_e)
  {
    if (logger == null) {
      logger = SoundSystemConfig.getLogger();
    }
    if (logger == null)
    {
      logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(logger);
    }
    logger.printStackTrace(local_e, 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.utils.SoundSystemLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */