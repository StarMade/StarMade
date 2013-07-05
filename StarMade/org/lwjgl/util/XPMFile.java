/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.LineNumberReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class XPMFile
/*     */ {
/*     */   private byte[] bytes;
/*     */   private static final int WIDTH = 0;
/*     */   private static final int HEIGHT = 1;
/*     */   private static final int NUMBER_OF_COLORS = 2;
/*     */   private static final int CHARACTERS_PER_PIXEL = 3;
/*  69 */   private static int[] format = new int[4];
/*     */ 
/*     */   public static XPMFile load(String file)
/*     */     throws IOException
/*     */   {
/*  87 */     return load(new FileInputStream(new File(file)));
/*     */   }
/*     */ 
/*     */   public static XPMFile load(InputStream is)
/*     */   {
/*  98 */     XPMFile xFile = new XPMFile();
/*  99 */     xFile.readImage(is);
/* 100 */     return xFile;
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 107 */     return format[1];
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/* 114 */     return format[0];
/*     */   }
/*     */ 
/*     */   public byte[] getBytes()
/*     */   {
/* 121 */     return this.bytes;
/*     */   }
/*     */ 
/*     */   private void readImage(InputStream is)
/*     */   {
/*     */     try
/*     */     {
/* 129 */       LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));
/*     */ 
/* 131 */       HashMap colors = new HashMap();
/*     */ 
/* 133 */       format = parseFormat(nextLineOfInterest(reader));
/*     */ 
/* 136 */       for (int i = 0; i < format[2]; i++) {
/* 137 */         Object[] colorDefinition = parseColor(nextLineOfInterest(reader));
/* 138 */         colors.put((String)colorDefinition[0], (Integer)colorDefinition[1]);
/*     */       }
/*     */ 
/* 142 */       this.bytes = new byte[format[0] * format[1] * 4];
/* 143 */       for (int i = 0; i < format[1]; i++)
/* 144 */         parseImageLine(nextLineOfInterest(reader), format, colors, i);
/*     */     }
/*     */     catch (Exception e) {
/* 147 */       e.printStackTrace();
/* 148 */       throw new IllegalArgumentException("Unable to parse XPM File");
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String nextLineOfInterest(LineNumberReader reader)
/*     */     throws IOException
/*     */   {
/*     */     String ret;
/*     */     do
/* 165 */       ret = reader.readLine();
/* 166 */     while (!ret.startsWith("\""));
/*     */ 
/* 168 */     return ret.substring(1, ret.lastIndexOf('"'));
/*     */   }
/*     */ 
/*     */   private static int[] parseFormat(String format)
/*     */   {
/* 183 */     StringTokenizer st = new StringTokenizer(format);
/*     */ 
/* 185 */     return new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
/*     */   }
/*     */ 
/*     */   private static Object[] parseColor(String line)
/*     */   {
/* 207 */     String key = line.substring(0, format[3]);
/*     */ 
/* 211 */     String color = line.substring(format[3] + 4);
/*     */ 
/* 214 */     return new Object[] { key, Integer.valueOf(Integer.parseInt(color, 16)) };
/*     */   }
/*     */ 
/*     */   private void parseImageLine(String line, int[] format, HashMap<String, Integer> colors, int index)
/*     */   {
/* 232 */     int offset = index * 4 * format[0];
/*     */ 
/* 236 */     for (int i = 0; i < format[0]; i++) {
/* 237 */       String key = line.substring(i * format[3], i * format[3] + format[3]);
/*     */ 
/* 241 */       int color = ((Integer)colors.get(key)).intValue();
/* 242 */       this.bytes[(offset + i * 4)] = ((byte)((color & 0xFF0000) >> 16));
/* 243 */       this.bytes[(offset + (i * 4 + 1))] = ((byte)((color & 0xFF00) >> 8));
/* 244 */       this.bytes[(offset + (i * 4 + 2))] = ((byte)((color & 0xFF) >> 0));
/*     */ 
/* 247 */       this.bytes[(offset + (i * 4 + 3))] = -1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 255 */     if (args.length != 1) {
/* 256 */       System.out.println("usage:\nXPMFile <file>");
/*     */     }
/*     */     try
/*     */     {
/* 260 */       String out = args[0].substring(0, args[0].indexOf(".")) + ".raw";
/* 261 */       XPMFile file = load(args[0]);
/* 262 */       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(out)));
/*     */ 
/* 264 */       bos.write(file.getBytes());
/* 265 */       bos.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 269 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.XPMFile
 * JD-Core Version:    0.6.2
 */