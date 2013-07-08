/*   1:    */package paulscode.sound;
/*   2:    */
/*   3:    */import java.net.URL;
/*   4:    */
/*  49:    */public class FilenameURL
/*  50:    */{
/*  51:    */  private SoundSystemLogger logger;
/*  52: 52 */  private String filename = null;
/*  53:    */  
/*  57: 57 */  private URL url = null;
/*  58:    */  
/*  67:    */  public FilenameURL(URL url, String identifier)
/*  68:    */  {
/*  69: 69 */    this.logger = SoundSystemConfig.getLogger();
/*  70:    */    
/*  71: 71 */    this.filename = identifier;
/*  72: 72 */    this.url = url;
/*  73:    */  }
/*  74:    */  
/*  83:    */  public FilenameURL(String filename)
/*  84:    */  {
/*  85: 85 */    this.logger = SoundSystemConfig.getLogger();
/*  86:    */    
/*  87: 87 */    this.filename = filename;
/*  88: 88 */    this.url = null;
/*  89:    */  }
/*  90:    */  
/*  95:    */  public String getFilename()
/*  96:    */  {
/*  97: 97 */    return this.filename;
/*  98:    */  }
/*  99:    */  
/* 106:    */  public URL getURL()
/* 107:    */  {
/* 108:108 */    if (this.url == null)
/* 109:    */    {
/* 111:111 */      if (this.filename.matches(SoundSystemConfig.PREFIX_URL))
/* 112:    */      {
/* 113:    */        try
/* 114:    */        {
/* 116:116 */          this.url = new URL(this.filename);
/* 117:    */        }
/* 118:    */        catch (Exception e)
/* 119:    */        {
/* 120:120 */          errorMessage("Unable to access online URL in method 'getURL'");
/* 121:    */          
/* 122:122 */          printStackTrace(e);
/* 123:123 */          return null;
/* 124:    */        }
/* 125:    */        
/* 126:    */      }
/* 127:    */      else
/* 128:    */      {
/* 129:129 */        this.url = getClass().getClassLoader().getResource(SoundSystemConfig.getSoundFilesPackage() + this.filename);
/* 130:    */      }
/* 131:    */    }
/* 132:    */    
/* 133:133 */    return this.url;
/* 134:    */  }
/* 135:    */  
/* 140:    */  private void errorMessage(String message)
/* 141:    */  {
/* 142:142 */    this.logger.errorMessage("MidiChannel", message, 0);
/* 143:    */  }
/* 144:    */  
/* 149:    */  private void printStackTrace(Exception e)
/* 150:    */  {
/* 151:151 */    this.logger.printStackTrace(e, 1);
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.FilenameURL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */