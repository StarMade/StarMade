/*     */ package paulscode.sound.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ 
/*     */ public class XMLParser
/*     */ {
/*     */   private static SoundSystemLogger logger;
/*     */ 
/*     */   public static XMLNode parseXML(URL xmlFile)
/*     */   {
/*  65 */     return new XMLNode(trimSpaces(readString(xmlFile)));
/*     */   }
/*     */ 
/*     */   public static String getRawXML(URL xmlFile)
/*     */   {
/*  77 */     return trimSpaces(readString(xmlFile));
/*     */   }
/*     */ 
/*     */   private static String readString(URL file)
/*     */   {
/*  88 */     InputStream in = null;
/*     */     try
/*     */     {
/*  91 */       in = file.openStream();
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/*  95 */       errorMessage("Unable to open inputstream in method 'readString'");
/*  96 */       return null;
/*     */     }
/*     */ 
/* 100 */     ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 101 */     ByteArrayInputStream bin = null;
/* 102 */     BufferedReader bufRead = null;
/*     */ 
/* 104 */     String fullString = "";
/*     */ 
/* 106 */     byte[] buffer = new byte[4096];
/* 107 */     int read = 0;
/*     */     try
/*     */     {
/*     */       do
/*     */       {
/* 113 */         read = in.read(buffer);
/* 114 */         if (read != -1)
/* 115 */           bout.write(buffer, 0, read); 
/*     */       }
/* 116 */       while (read != -1);
/*     */ 
/* 119 */       bin = new ByteArrayInputStream(bout.toByteArray());
/* 120 */       bufRead = new BufferedReader(new InputStreamReader(bin));
/*     */ 
/* 123 */       String line = "";
/*     */       do
/*     */       {
/* 126 */         line = bufRead.readLine();
/*     */ 
/* 129 */         if (line != null)
/* 130 */           fullString = fullString + "\n" + line; 
/*     */       }
/* 131 */       while (line != null);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 135 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 140 */     if (in != null)
/*     */     {
/*     */       try
/*     */       {
/* 144 */         in.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/*     */     }
/* 149 */     if (bout != null)
/*     */     {
/*     */       try
/*     */       {
/* 153 */         bout.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/*     */     }
/* 158 */     if (bin != null)
/*     */     {
/*     */       try
/*     */       {
/* 162 */         bin.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/*     */     }
/* 167 */     if (bufRead != null)
/*     */     {
/*     */       try
/*     */       {
/* 171 */         bufRead.close();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */     }
/* 177 */     in = null;
/* 178 */     bout = null;
/* 179 */     bin = null;
/* 180 */     bufRead = null;
/*     */ 
/* 183 */     return fullString;
/*     */   }
/*     */ 
/*     */   public static String trimSpaces(String text)
/*     */   {
/* 195 */     String[] splitText = seperateWords(text);
/*     */ 
/* 198 */     if ((splitText == null) || (splitText.length == 0)) {
/* 199 */       return "";
/*     */     }
/*     */ 
/* 202 */     String parsedText = splitText[0];
/* 203 */     for (int x = 1; x < splitText.length; x++)
/*     */     {
/* 205 */       parsedText = parsedText + " " + splitText[x];
/*     */     }
/*     */ 
/* 209 */     return parsedText;
/*     */   }
/*     */ 
/*     */   public static String[] seperateWords(String text)
/*     */   {
/* 220 */     if (text == null) {
/* 221 */       return null;
/*     */     }
/*     */ 
/* 224 */     while ((text.length() > 0) && (text.substring(0, 1).matches("\\s")))
/*     */     {
/* 226 */       text = text.substring(1);
/*     */     }
/*     */ 
/* 230 */     if (text.length() == 0) {
/* 231 */       return null;
/*     */     }
/*     */ 
/* 234 */     String[] splitText = text.split("\\s+");
/*     */ 
/* 237 */     return splitText;
/*     */   }
/*     */ 
/*     */   protected static void errorMessage(String message)
/*     */   {
/* 247 */     if (logger == null) {
/* 248 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 250 */     if (logger == null)
/*     */     {
/* 252 */       logger = new SoundSystemLogger();
/* 253 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 255 */     logger.errorMessage("XMLParser", message, 0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.utils.XMLParser
 * JD-Core Version:    0.6.2
 */