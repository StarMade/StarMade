/*   1:    */package paulscode.sound;
/*   2:    */
/*   3:    */import java.util.LinkedList;
/*   4:    */import javax.sound.sampled.AudioFormat;
/*   5:    */
/*  49:    */public class Channel
/*  50:    */{
/*  51: 51 */  protected Class libraryType = Library.class;
/*  52:    */  
/*  57:    */  public int channelType;
/*  58:    */  
/*  63:    */  private SoundSystemLogger logger;
/*  64:    */  
/*  68: 68 */  public Source attachedSource = null;
/*  69:    */  
/*  73: 73 */  public int buffersUnqueued = 0;
/*  74:    */  
/*  82:    */  public Channel(int type)
/*  83:    */  {
/*  84: 84 */    this.logger = SoundSystemConfig.getLogger();
/*  85:    */    
/*  86: 86 */    this.channelType = type;
/*  87:    */  }
/*  88:    */  
/*  92:    */  public void cleanup()
/*  93:    */  {
/*  94: 94 */    this.logger = null;
/*  95:    */  }
/*  96:    */  
/* 102:    */  public boolean preLoadBuffers(LinkedList<byte[]> bufferList)
/* 103:    */  {
/* 104:104 */    return true;
/* 105:    */  }
/* 106:    */  
/* 112:    */  public boolean queueBuffer(byte[] buffer)
/* 113:    */  {
/* 114:114 */    return false;
/* 115:    */  }
/* 116:    */  
/* 122:    */  public int feedRawAudioData(byte[] buffer)
/* 123:    */  {
/* 124:124 */    return 1;
/* 125:    */  }
/* 126:    */  
/* 131:    */  public int buffersProcessed()
/* 132:    */  {
/* 133:133 */    return 0;
/* 134:    */  }
/* 135:    */  
/* 140:    */  public float millisecondsPlayed()
/* 141:    */  {
/* 142:142 */    return -1.0F;
/* 143:    */  }
/* 144:    */  
/* 149:    */  public boolean processBuffer()
/* 150:    */  {
/* 151:151 */    return false;
/* 152:    */  }
/* 153:    */  
/* 159:    */  public void setAudioFormat(AudioFormat audioFormat) {}
/* 160:    */  
/* 166:    */  public void flush() {}
/* 167:    */  
/* 173:    */  public void close() {}
/* 174:    */  
/* 180:    */  public void play() {}
/* 181:    */  
/* 187:    */  public void pause() {}
/* 188:    */  
/* 193:    */  public void stop() {}
/* 194:    */  
/* 199:    */  public void rewind() {}
/* 200:    */  
/* 205:    */  public boolean playing()
/* 206:    */  {
/* 207:207 */    return false;
/* 208:    */  }
/* 209:    */  
/* 214:    */  public String getClassName()
/* 215:    */  {
/* 216:216 */    String libTitle = SoundSystemConfig.getLibraryTitle(this.libraryType);
/* 217:    */    
/* 218:218 */    if (libTitle.equals("No Sound")) {
/* 219:219 */      return "Channel";
/* 220:    */    }
/* 221:221 */    return "Channel" + libTitle;
/* 222:    */  }
/* 223:    */  
/* 228:    */  protected void message(String message)
/* 229:    */  {
/* 230:230 */    this.logger.message(message, 0);
/* 231:    */  }
/* 232:    */  
/* 237:    */  protected void importantMessage(String message)
/* 238:    */  {
/* 239:239 */    this.logger.importantMessage(message, 0);
/* 240:    */  }
/* 241:    */  
/* 248:    */  protected boolean errorCheck(boolean error, String message)
/* 249:    */  {
/* 250:250 */    return this.logger.errorCheck(error, getClassName(), message, 0);
/* 251:    */  }
/* 252:    */  
/* 257:    */  protected void errorMessage(String message)
/* 258:    */  {
/* 259:259 */    this.logger.errorMessage(getClassName(), message, 0);
/* 260:    */  }
/* 261:    */  
/* 266:    */  protected void printStackTrace(Exception e)
/* 267:    */  {
/* 268:268 */    this.logger.printStackTrace(e, 1);
/* 269:    */  }
/* 270:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Channel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */