/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.io.BufferedOutputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileInputStream;
/*   6:    */import java.io.FileOutputStream;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.InputStream;
/*   9:    */import java.io.InputStreamReader;
/*  10:    */import java.io.LineNumberReader;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.util.HashMap;
/*  13:    */import java.util.StringTokenizer;
/*  14:    */
/*  62:    */public class XPMFile
/*  63:    */{
/*  64:    */  private byte[] bytes;
/*  65:    */  private static final int WIDTH = 0;
/*  66:    */  private static final int HEIGHT = 1;
/*  67:    */  private static final int NUMBER_OF_COLORS = 2;
/*  68:    */  private static final int CHARACTERS_PER_PIXEL = 3;
/*  69: 69 */  private static int[] format = new int[4];
/*  70:    */  
/*  84:    */  public static XPMFile load(String file)
/*  85:    */    throws IOException
/*  86:    */  {
/*  87: 87 */    return load(new FileInputStream(new File(file)));
/*  88:    */  }
/*  89:    */  
/*  96:    */  public static XPMFile load(InputStream is)
/*  97:    */  {
/*  98: 98 */    XPMFile xFile = new XPMFile();
/*  99: 99 */    xFile.readImage(is);
/* 100:100 */    return xFile;
/* 101:    */  }
/* 102:    */  
/* 105:    */  public int getHeight()
/* 106:    */  {
/* 107:107 */    return format[1];
/* 108:    */  }
/* 109:    */  
/* 112:    */  public int getWidth()
/* 113:    */  {
/* 114:114 */    return format[0];
/* 115:    */  }
/* 116:    */  
/* 119:    */  public byte[] getBytes()
/* 120:    */  {
/* 121:121 */    return this.bytes;
/* 122:    */  }
/* 123:    */  
/* 125:    */  private void readImage(InputStream is)
/* 126:    */  {
/* 127:    */    try
/* 128:    */    {
/* 129:129 */      LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));
/* 130:    */      
/* 131:131 */      HashMap<String, Integer> colors = new HashMap();
/* 132:    */      
/* 133:133 */      format = parseFormat(nextLineOfInterest(reader));
/* 134:    */      
/* 136:136 */      for (int i = 0; i < format[2]; i++) {
/* 137:137 */        Object[] colorDefinition = parseColor(nextLineOfInterest(reader));
/* 138:138 */        colors.put((String)colorDefinition[0], (Integer)colorDefinition[1]);
/* 139:    */      }
/* 140:    */      
/* 142:142 */      this.bytes = new byte[format[0] * format[1] * 4];
/* 143:143 */      for (int i = 0; i < format[1]; i++) {
/* 144:144 */        parseImageLine(nextLineOfInterest(reader), format, colors, i);
/* 145:    */      }
/* 146:    */    } catch (Exception e) {
/* 147:147 */      e.printStackTrace();
/* 148:148 */      throw new IllegalArgumentException("Unable to parse XPM File");
/* 149:    */    }
/* 150:    */  }
/* 151:    */  
/* 156:    */  private static String nextLineOfInterest(LineNumberReader reader)
/* 157:    */    throws IOException
/* 158:    */  {
/* 159:    */    String ret;
/* 160:    */    
/* 163:    */    do
/* 164:    */    {
/* 165:165 */      ret = reader.readLine();
/* 166:166 */    } while (!ret.startsWith("\""));
/* 167:    */    
/* 168:168 */    return ret.substring(1, ret.lastIndexOf('"'));
/* 169:    */  }
/* 170:    */  
/* 181:    */  private static int[] parseFormat(String format)
/* 182:    */  {
/* 183:183 */    StringTokenizer st = new StringTokenizer(format);
/* 184:    */    
/* 185:185 */    return new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
/* 186:    */  }
/* 187:    */  
/* 205:    */  private static Object[] parseColor(String line)
/* 206:    */  {
/* 207:207 */    String key = line.substring(0, format[3]);
/* 208:    */    
/* 211:211 */    String color = line.substring(format[3] + 4);
/* 212:    */    
/* 214:214 */    return new Object[] { key, Integer.valueOf(Integer.parseInt(color, 16)) };
/* 215:    */  }
/* 216:    */  
/* 230:    */  private void parseImageLine(String line, int[] format, HashMap<String, Integer> colors, int index)
/* 231:    */  {
/* 232:232 */    int offset = index * 4 * format[0];
/* 233:    */    
/* 236:236 */    for (int i = 0; i < format[0]; i++) {
/* 237:237 */      String key = line.substring(i * format[3], i * format[3] + format[3]);
/* 238:    */      
/* 241:241 */      int color = ((Integer)colors.get(key)).intValue();
/* 242:242 */      this.bytes[(offset + i * 4)] = ((byte)((color & 0xFF0000) >> 16));
/* 243:243 */      this.bytes[(offset + (i * 4 + 1))] = ((byte)((color & 0xFF00) >> 8));
/* 244:244 */      this.bytes[(offset + (i * 4 + 2))] = ((byte)((color & 0xFF) >> 0));
/* 245:    */      
/* 247:247 */      this.bytes[(offset + (i * 4 + 3))] = -1;
/* 248:    */    }
/* 249:    */  }
/* 250:    */  
/* 253:    */  public static void main(String[] args)
/* 254:    */  {
/* 255:255 */    if (args.length != 1) {
/* 256:256 */      System.out.println("usage:\nXPMFile <file>");
/* 257:    */    }
/* 258:    */    try
/* 259:    */    {
/* 260:260 */      String out = args[0].substring(0, args[0].indexOf(".")) + ".raw";
/* 261:261 */      XPMFile file = load(args[0]);
/* 262:262 */      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(out)));
/* 263:    */      
/* 264:264 */      bos.write(file.getBytes());
/* 265:265 */      bos.close();
/* 266:    */    }
/* 267:    */    catch (Exception e)
/* 268:    */    {
/* 269:269 */      e.printStackTrace();
/* 270:    */    }
/* 271:    */  }
/* 272:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.XPMFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */