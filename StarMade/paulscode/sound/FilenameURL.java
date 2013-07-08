package paulscode.sound;

import java.net.URL;

public class FilenameURL
{
  private SoundSystemLogger logger = SoundSystemConfig.getLogger();
  private String filename = null;
  private URL url = null;
  
  public FilenameURL(URL url, String identifier)
  {
    this.filename = identifier;
    this.url = url;
  }
  
  public FilenameURL(String filename)
  {
    this.filename = filename;
    this.url = null;
  }
  
  public String getFilename()
  {
    return this.filename;
  }
  
  public URL getURL()
  {
    if (this.url == null) {
      if (this.filename.matches(SoundSystemConfig.PREFIX_URL)) {
        try
        {
          this.url = new URL(this.filename);
        }
        catch (Exception local_e)
        {
          errorMessage("Unable to access online URL in method 'getURL'");
          printStackTrace(local_e);
          return null;
        }
      } else {
        this.url = getClass().getClassLoader().getResource(SoundSystemConfig.getSoundFilesPackage() + this.filename);
      }
    }
    return this.url;
  }
  
  private void errorMessage(String message)
  {
    this.logger.errorMessage("MidiChannel", message, 0);
  }
  
  private void printStackTrace(Exception local_e)
  {
    this.logger.printStackTrace(local_e, 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.FilenameURL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */