/*   1:    */package paulscode.sound.utils;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.ByteArrayInputStream;
/*   5:    */import java.io.ByteArrayOutputStream;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.InputStreamReader;
/*   9:    */import java.net.URL;
/*  10:    */import paulscode.sound.SoundSystemConfig;
/*  11:    */import paulscode.sound.SoundSystemLogger;
/*  12:    */
/*  59:    */public class XMLParser
/*  60:    */{
/*  61:    */  private static SoundSystemLogger logger;
/*  62:    */  
/*  63:    */  public static XMLNode parseXML(URL xmlFile)
/*  64:    */  {
/*  65: 65 */    return new XMLNode(trimSpaces(readString(xmlFile)));
/*  66:    */  }
/*  67:    */  
/*  75:    */  public static String getRawXML(URL xmlFile)
/*  76:    */  {
/*  77: 77 */    return trimSpaces(readString(xmlFile));
/*  78:    */  }
/*  79:    */  
/*  86:    */  private static String readString(URL file)
/*  87:    */  {
/*  88: 88 */    InputStream in = null;
/*  89:    */    try
/*  90:    */    {
/*  91: 91 */      in = file.openStream();
/*  92:    */    }
/*  93:    */    catch (IOException ioe)
/*  94:    */    {
/*  95: 95 */      errorMessage("Unable to open inputstream in method 'readString'");
/*  96: 96 */      return null;
/*  97:    */    }
/*  98:    */    
/* 100:100 */    ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 101:101 */    ByteArrayInputStream bin = null;
/* 102:102 */    BufferedReader bufRead = null;
/* 103:    */    
/* 104:104 */    String fullString = "";
/* 105:    */    
/* 106:106 */    byte[] buffer = new byte[4096];
/* 107:107 */    int read = 0;
/* 108:    */    
/* 109:    */    try
/* 110:    */    {
/* 111:    */      do
/* 112:    */      {
/* 113:113 */        read = in.read(buffer);
/* 114:114 */        if (read != -1)
/* 115:115 */          bout.write(buffer, 0, read);
/* 116:116 */      } while (read != -1);
/* 117:    */      
/* 119:119 */      bin = new ByteArrayInputStream(bout.toByteArray());
/* 120:120 */      bufRead = new BufferedReader(new InputStreamReader(bin));
/* 121:    */      
/* 123:123 */      String line = "";
/* 124:    */      do
/* 125:    */      {
/* 126:126 */        line = bufRead.readLine();
/* 127:    */        
/* 129:129 */        if (line != null)
/* 130:130 */          fullString = fullString + "\n" + line;
/* 131:131 */      } while (line != null);
/* 132:    */    }
/* 133:    */    catch (IOException e)
/* 134:    */    {
/* 135:135 */      e.printStackTrace();
/* 136:    */    }
/* 137:    */    
/* 140:140 */    if (in != null)
/* 141:    */    {
/* 142:    */      try
/* 143:    */      {
/* 144:144 */        in.close();
/* 145:    */      }
/* 146:    */      catch (Exception e) {}
/* 147:    */    }
/* 148:    */    
/* 149:149 */    if (bout != null)
/* 150:    */    {
/* 151:    */      try
/* 152:    */      {
/* 153:153 */        bout.close();
/* 154:    */      }
/* 155:    */      catch (Exception e) {}
/* 156:    */    }
/* 157:    */    
/* 158:158 */    if (bin != null)
/* 159:    */    {
/* 160:    */      try
/* 161:    */      {
/* 162:162 */        bin.close();
/* 163:    */      }
/* 164:    */      catch (Exception e) {}
/* 165:    */    }
/* 166:    */    
/* 167:167 */    if (bufRead != null)
/* 168:    */    {
/* 169:    */      try
/* 170:    */      {
/* 171:171 */        bufRead.close();
/* 172:    */      }
/* 173:    */      catch (Exception e) {}
/* 174:    */    }
/* 175:    */    
/* 177:177 */    in = null;
/* 178:178 */    bout = null;
/* 179:179 */    bin = null;
/* 180:180 */    bufRead = null;
/* 181:    */    
/* 183:183 */    return fullString;
/* 184:    */  }
/* 185:    */  
/* 193:    */  public static String trimSpaces(String text)
/* 194:    */  {
/* 195:195 */    String[] splitText = seperateWords(text);
/* 196:    */    
/* 198:198 */    if ((splitText == null) || (splitText.length == 0)) {
/* 199:199 */      return "";
/* 200:    */    }
/* 201:    */    
/* 202:202 */    String parsedText = splitText[0];
/* 203:203 */    for (int x = 1; x < splitText.length; x++)
/* 204:    */    {
/* 205:205 */      parsedText = parsedText + " " + splitText[x];
/* 206:    */    }
/* 207:    */    
/* 209:209 */    return parsedText;
/* 210:    */  }
/* 211:    */  
/* 218:    */  public static String[] seperateWords(String text)
/* 219:    */  {
/* 220:220 */    if (text == null) {
/* 221:221 */      return null;
/* 222:    */    }
/* 223:    */    
/* 224:224 */    while ((text.length() > 0) && (text.substring(0, 1).matches("\\s")))
/* 225:    */    {
/* 226:226 */      text = text.substring(1);
/* 227:    */    }
/* 228:    */    
/* 230:230 */    if (text.length() == 0) {
/* 231:231 */      return null;
/* 232:    */    }
/* 233:    */    
/* 234:234 */    String[] splitText = text.split("\\s+");
/* 235:    */    
/* 237:237 */    return splitText;
/* 238:    */  }
/* 239:    */  
/* 245:    */  protected static void errorMessage(String message)
/* 246:    */  {
/* 247:247 */    if (logger == null) {
/* 248:248 */      logger = SoundSystemConfig.getLogger();
/* 249:    */    }
/* 250:250 */    if (logger == null)
/* 251:    */    {
/* 252:252 */      logger = new SoundSystemLogger();
/* 253:253 */      SoundSystemConfig.setLogger(logger);
/* 254:    */    }
/* 255:255 */    logger.errorMessage("XMLParser", message, 0);
/* 256:    */  }
/* 257:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.utils.XMLParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */