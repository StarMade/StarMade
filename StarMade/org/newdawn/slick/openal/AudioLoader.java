/*  1:   */package org.newdawn.slick.openal;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.InputStream;
/*  5:   */import java.net.URL;
/*  6:   */
/* 19:   */public class AudioLoader
/* 20:   */{
/* 21:   */  private static final String AIF = "AIF";
/* 22:   */  private static final String WAV = "WAV";
/* 23:   */  private static final String OGG = "OGG";
/* 24:   */  private static final String MOD = "MOD";
/* 25:   */  private static final String XM = "XM";
/* 26:26 */  private static boolean inited = false;
/* 27:   */  
/* 30:   */  private static void init()
/* 31:   */  {
/* 32:32 */    if (!inited) {
/* 33:33 */      SoundStore.get().init();
/* 34:34 */      inited = true;
/* 35:   */    }
/* 36:   */  }
/* 37:   */  
/* 42:   */  public static Audio getAudio(String format, InputStream in)
/* 43:   */    throws IOException
/* 44:   */  {
/* 45:   */    
/* 46:   */    
/* 50:50 */    if (format.equals("AIF")) {
/* 51:51 */      return SoundStore.get().getAIF(in);
/* 52:   */    }
/* 53:53 */    if (format.equals("WAV")) {
/* 54:54 */      return SoundStore.get().getWAV(in);
/* 55:   */    }
/* 56:56 */    if (format.equals("OGG")) {
/* 57:57 */      return SoundStore.get().getOgg(in);
/* 58:   */    }
/* 59:   */    
/* 60:60 */    throw new IOException("Unsupported format for non-streaming Audio: " + format);
/* 61:   */  }
/* 62:   */  
/* 67:   */  public static Audio getStreamingAudio(String format, URL url)
/* 68:   */    throws IOException
/* 69:   */  {
/* 70:   */    
/* 71:   */    
/* 75:75 */    if (format.equals("OGG")) {
/* 76:76 */      return SoundStore.get().getOggStream(url);
/* 77:   */    }
/* 78:78 */    if (format.equals("MOD")) {
/* 79:79 */      return SoundStore.get().getMOD(url.openStream());
/* 80:   */    }
/* 81:81 */    if (format.equals("XM")) {
/* 82:82 */      return SoundStore.get().getMOD(url.openStream());
/* 83:   */    }
/* 84:   */    
/* 85:85 */    throw new IOException("Unsupported format for streaming Audio: " + format);
/* 86:   */  }
/* 87:   */  
/* 90:   */  public static void update()
/* 91:   */  {
/* 92:92 */    init();
/* 93:   */    
/* 94:94 */    SoundStore.get().poll(0);
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.AudioLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */