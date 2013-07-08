/*   1:    */package paulscode.sound.utils;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.Set;
/*   7:    */import paulscode.sound.SoundSystemConfig;
/*   8:    */import paulscode.sound.SoundSystemLogger;
/*   9:    */
/*  53:    */public class XMLNode
/*  54:    */{
/*  55:    */  private static SoundSystemLogger logger;
/*  56: 56 */  private static boolean verbose = false;
/*  57:    */  
/*  61: 61 */  protected String name = "";
/*  62:    */  
/*  65: 65 */  protected String contents = "";
/*  66:    */  
/*  69: 69 */  protected HashMap<String, String> parameters = null;
/*  70:    */  
/*  73: 73 */  protected XMLNode next = null;
/*  74:    */  
/*  83:    */  public XMLNode(String XMLText)
/*  84:    */  {
/*  85: 85 */    int lBracket = XMLText.indexOf("<");
/*  86: 86 */    int rBracket = XMLText.indexOf(">");
/*  87:    */    
/*  89: 89 */    if ((lBracket == -1) || (rBracket == -1)) {
/*  90: 90 */      return;
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    if (rBracket <= lBracket)
/*  94:    */    {
/*  95: 95 */      errorMessage("Invalid XML syntax: '>' before '<'");
/*  96: 96 */      return;
/*  97:    */    }
/*  98:    */    
/* 100:100 */    this.parameters = new HashMap();
/* 101:    */    
/* 103:103 */    this.contents = XMLText.substring(lBracket + 1, rBracket);
/* 104:104 */    processTagContents(this.contents);
/* 105:    */    
/* 106:106 */    if (verbose) {
/* 107:107 */      displayParameters();
/* 108:    */    }
/* 109:    */    
/* 110:110 */    if (rBracket + 1 >= XMLText.length())
/* 111:111 */      return;
/* 112:112 */    String XMLRemainder = XMLText.substring(rBracket + 1);
/* 113:    */    
/* 115:115 */    lBracket = XMLRemainder.indexOf("<");
/* 116:116 */    if (lBracket == -1) {
/* 117:117 */      return;
/* 118:    */    }
/* 119:    */    
/* 120:120 */    this.next = new XMLNode(XMLRemainder.substring(lBracket));
/* 121:    */  }
/* 122:    */  
/* 127:    */  public String name()
/* 128:    */  {
/* 129:129 */    if (this.name == null) {
/* 130:130 */      return "";
/* 131:    */    }
/* 132:132 */    return this.name;
/* 133:    */  }
/* 134:    */  
/* 139:    */  public String contents()
/* 140:    */  {
/* 141:141 */    if (this.contents == null) {
/* 142:142 */      return "";
/* 143:    */    }
/* 144:144 */    return this.contents;
/* 145:    */  }
/* 146:    */  
/* 151:    */  public boolean hasNext()
/* 152:    */  {
/* 153:153 */    return this.next != null;
/* 154:    */  }
/* 155:    */  
/* 160:    */  public XMLNode next()
/* 161:    */  {
/* 162:162 */    return this.next;
/* 163:    */  }
/* 164:    */  
/* 169:    */  public String get(String parameter)
/* 170:    */  {
/* 171:171 */    if (this.parameters == null) {
/* 172:172 */      return "";
/* 173:    */    }
/* 174:174 */    return (String)this.parameters.get(parameter.toUpperCase());
/* 175:    */  }
/* 176:    */  
/* 181:    */  public HashMap<String, String> parameters()
/* 182:    */  {
/* 183:183 */    return this.parameters;
/* 184:    */  }
/* 185:    */  
/* 190:    */  public void displayParameters()
/* 191:    */  {
/* 192:192 */    System.out.println("Parameters for " + this.name + ":");
/* 193:    */    
/* 195:195 */    Set<String> keys = this.parameters.keySet();
/* 196:196 */    Iterator<String> iter = keys.iterator();
/* 197:    */    
/* 201:201 */    if (!iter.hasNext()) {
/* 202:202 */      System.out.println("    (none)");
/* 203:    */    }
/* 204:    */    
/* 205:205 */    while (iter.hasNext())
/* 206:    */    {
/* 207:207 */      String par = (String)iter.next();
/* 208:208 */      String val = (String)this.parameters.get(par);
/* 209:209 */      System.out.println("    " + par + " = " + val);
/* 210:    */    }
/* 211:    */  }
/* 212:    */  
/* 218:    */  protected void processTagContents(String tagContents)
/* 219:    */  {
/* 220:220 */    String[] splitTag = XMLParser.seperateWords(tagContents);
/* 221:221 */    int x; if (splitTag.length > 0)
/* 222:    */    {
/* 224:224 */      this.name = splitTag[0];
/* 225:    */      
/* 227:227 */      if ((this.name.length() >= 3) && (this.name.substring(0, 3).equals("!--"))) {
/* 228:228 */        return;
/* 229:    */      }
/* 230:    */      
/* 231:231 */      for (x = 1; x < splitTag.length;)
/* 232:    */      {
/* 233:233 */        String paramText = splitTag[x];
/* 234:234 */        if (paramText.equals("/"))
/* 235:    */          break;
/* 236:236 */        if (paramText.contains("="))
/* 237:    */        {
/* 238:238 */          String[] pair = paramText.split("=");
/* 239:239 */          if ((pair == null) || (pair.length == 0))
/* 240:    */          {
/* 241:241 */            errorMessage("Invalid XML syntax: paramater null");
/* 242:242 */            return;
/* 243:    */          }
/* 244:244 */          if (pair.length == 1)
/* 245:    */          {
/* 246:246 */            if (x + 1 >= splitTag.length)
/* 247:    */            {
/* 248:248 */              warningMessage("Value not specified for parameter '" + pair[0] + "'");
/* 249:    */              
/* 250:250 */              this.parameters.put(pair[0].toUpperCase(), "");
/* 251:251 */              x++;
/* 252:    */            }
/* 253:    */            else
/* 254:    */            {
/* 255:255 */              this.parameters.put(pair[0].toUpperCase(), splitTag[(x + 1)]);
/* 256:    */              
/* 257:257 */              x += 2;
/* 258:    */            }
/* 259:    */          }
/* 260:    */          else
/* 261:    */          {
/* 262:262 */            this.parameters.put(pair[0].toUpperCase(), pair[1]);
/* 263:263 */            x++;
/* 264:    */          }
/* 265:    */          
/* 267:    */        }
/* 268:268 */        else if (x + 1 >= splitTag.length)
/* 269:    */        {
/* 270:270 */          warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/* 271:    */          
/* 272:272 */          this.parameters.put(splitTag[x].toUpperCase(), "");
/* 273:273 */          x++;
/* 276:    */        }
/* 277:277 */        else if (splitTag[(x + 1)].equals("="))
/* 278:    */        {
/* 279:279 */          if (x + 2 >= splitTag.length)
/* 280:    */          {
/* 281:281 */            warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/* 282:    */            
/* 284:284 */            this.parameters.put(splitTag[x].toUpperCase(), "");
/* 285:285 */            x += 2;
/* 286:    */          }
/* 287:    */          else
/* 288:    */          {
/* 289:289 */            this.parameters.put(splitTag[x].toUpperCase(), splitTag[(x + 2)]);
/* 290:    */            
/* 291:291 */            x += 3;
/* 292:    */          }
/* 293:    */        }
/* 294:294 */        else if (splitTag[(x + 1)].contains("="))
/* 295:    */        {
/* 296:296 */          String[] eq = splitTag[(x + 1)].split("=");
/* 297:297 */          if ((eq == null) || (eq.length < 1))
/* 298:    */          {
/* 299:299 */            warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/* 300:    */            
/* 302:302 */            this.parameters.put(splitTag[x].toUpperCase(), "");
/* 303:303 */            x += 2;
/* 304:    */          }
/* 305:    */          else
/* 306:    */          {
/* 307:307 */            this.parameters.put(splitTag[x].toUpperCase(), eq[1]);
/* 308:    */            
/* 309:309 */            x += 2;
/* 310:    */          }
/* 311:    */        }
/* 312:    */        else
/* 313:    */        {
/* 314:314 */          warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/* 315:    */          
/* 316:316 */          this.parameters.put(splitTag[x].toUpperCase(), "");
/* 317:317 */          x++;
/* 318:    */        }
/* 319:    */      }
/* 320:    */    }
/* 321:    */  }
/* 322:    */  
/* 329:    */  public static void setVerbose(boolean val)
/* 330:    */  {
/* 331:331 */    verbose = val;
/* 332:    */  }
/* 333:    */  
/* 339:    */  protected static void errorMessage(String message)
/* 340:    */  {
/* 341:341 */    if (logger == null) {
/* 342:342 */      logger = SoundSystemConfig.getLogger();
/* 343:    */    }
/* 344:344 */    if (logger == null)
/* 345:    */    {
/* 346:346 */      logger = new SoundSystemLogger();
/* 347:347 */      SoundSystemConfig.setLogger(logger);
/* 348:    */    }
/* 349:349 */    logger.errorMessage("XMLNode", message, 0);
/* 350:    */  }
/* 351:    */  
/* 357:    */  protected static void warningMessage(String message)
/* 358:    */  {
/* 359:359 */    if (logger == null) {
/* 360:360 */      logger = SoundSystemConfig.getLogger();
/* 361:    */    }
/* 362:362 */    if (logger == null)
/* 363:    */    {
/* 364:364 */      logger = new SoundSystemLogger();
/* 365:365 */      SoundSystemConfig.setLogger(logger);
/* 366:    */    }
/* 367:367 */    logger.importantMessage("Warning in class 'XMLNode': " + message, 0);
/* 368:    */  }
/* 369:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.utils.XMLNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */