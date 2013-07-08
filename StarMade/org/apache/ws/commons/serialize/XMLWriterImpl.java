/*   1:    */package org.apache.ws.commons.serialize;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.Map;
/*   8:    */import java.util.Map.Entry;
/*   9:    */import java.util.Set;
/*  10:    */import org.xml.sax.Attributes;
/*  11:    */import org.xml.sax.Locator;
/*  12:    */import org.xml.sax.SAXException;
/*  13:    */
/*  28:    */public class XMLWriterImpl
/*  29:    */  implements XMLWriter
/*  30:    */{
/*  31:    */  private static final int STATE_OUTSIDE = 0;
/*  32:    */  private static final int STATE_IN_START_ELEMENT = 1;
/*  33:    */  private static final int STATE_IN_ELEMENT = 2;
/*  34:    */  private String encoding;
/*  35:    */  private String indentString;
/*  36:    */  private String lineFeed;
/*  37:    */  private Writer w;
/*  38:    */  private Locator l;
/*  39:    */  private Map delayedPrefixes;
/*  40: 40 */  int curIndent = 0;
/*  41:    */  
/*  42:    */  private int state;
/*  43:    */  private boolean declarating;
/*  44:    */  
/*  45: 45 */  public void setEncoding(String pEncoding) { this.encoding = pEncoding; }
/*  46: 46 */  public String getEncoding() { return this.encoding; }
/*  47: 47 */  public void setDeclarating(boolean pDeclarating) { this.declarating = pDeclarating; }
/*  48: 48 */  public boolean isDeclarating() { return this.declarating; }
/*  49: 49 */  public void setIndenting(boolean pIndenting) { this.indenting = pIndenting; }
/*  50: 50 */  public boolean isIndenting() { return this.indenting; }
/*  51: 51 */  public void setIndentString(String pIndentString) { this.indentString = pIndentString; }
/*  52: 52 */  public String getIndentString() { return this.indentString; }
/*  53: 53 */  public void setLineFeed(String pLineFeed) { this.lineFeed = pLineFeed; }
/*  54: 54 */  public String getLineFeed() { return this.lineFeed; }
/*  55: 55 */  public void setFlushing(boolean pFlushing) { this.flushing = pFlushing; }
/*  56: 56 */  public boolean isFlushing() { return this.flushing; }
/*  57:    */  
/*  59:    */  public void setWriter(Writer pWriter)
/*  60:    */  {
/*  61: 61 */    this.w = pWriter;
/*  62:    */  }
/*  63:    */  
/*  65:    */  public Writer getWriter()
/*  66:    */  {
/*  67: 67 */    return this.w;
/*  68:    */  }
/*  69:    */  
/*  73:    */  public void setDocumentLocator(Locator pLocator)
/*  74:    */  {
/*  75: 75 */    this.l = pLocator;
/*  76:    */  }
/*  77:    */  
/*  79:    */  public Locator getDocumentLocator()
/*  80:    */  {
/*  81: 81 */    return this.l;
/*  82:    */  }
/*  83:    */  
/*  89:    */  public void startPrefixMapping(String prefix, String namespaceURI)
/*  90:    */    throws SAXException
/*  91:    */  {
/*  92: 92 */    if (this.delayedPrefixes == null) {
/*  93: 93 */      this.delayedPrefixes = new HashMap();
/*  94:    */    }
/*  95: 95 */    if ("".equals(prefix)) {
/*  96: 96 */      if (namespaceURI.equals(prefix)) {
/*  97: 97 */        return;
/*  98:    */      }
/*  99: 99 */      prefix = "xmlns";
/* 100:    */    } else {
/* 101:101 */      prefix = "xmlns:" + prefix;
/* 102:    */    }
/* 103:103 */    this.delayedPrefixes.put(prefix, namespaceURI);
/* 104:    */  }
/* 105:    */  
/* 109:    */  public void endPrefixMapping(String prefix)
/* 110:    */    throws SAXException
/* 111:    */  {
/* 112:112 */    if (this.delayedPrefixes != null) {
/* 113:113 */      if ("".equals(prefix)) {
/* 114:114 */        prefix = "xmlns";
/* 115:    */      } else {
/* 116:116 */        prefix = "xmlns:" + prefix;
/* 117:    */      }
/* 118:118 */      this.delayedPrefixes.remove(prefix);
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 123:    */  public void startDocument()
/* 124:    */    throws SAXException
/* 125:    */  {
/* 126:126 */    if (this.delayedPrefixes != null) {
/* 127:127 */      this.delayedPrefixes.clear();
/* 128:    */    }
/* 129:129 */    this.state = 0;
/* 130:130 */    this.curIndent = 0;
/* 131:131 */    if ((isDeclarating()) && (this.w != null)) {
/* 132:    */      try {
/* 133:133 */        this.w.write("<?xml version=\"1.0\"");
/* 134:134 */        String enc = getEncoding();
/* 135:135 */        if (enc != null) {
/* 136:136 */          this.w.write(" encoding=\"");
/* 137:137 */          this.w.write(enc);
/* 138:138 */          this.w.write("\"");
/* 139:    */        }
/* 140:140 */        this.w.write("?>");
/* 141:141 */        if (isIndenting()) {
/* 142:142 */          String lf = getLineFeed();
/* 143:143 */          if (lf != null) {
/* 144:144 */            this.w.write(lf);
/* 145:    */          }
/* 146:    */        }
/* 147:    */      } catch (IOException e) {
/* 148:148 */        throw new SAXException("Failed to write XML declaration: " + e.getMessage(), e);
/* 149:    */      }
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 157:    */  public void endDocument()
/* 158:    */    throws SAXException
/* 159:    */  {
/* 160:160 */    if ((isFlushing()) && (this.w != null)) {
/* 161:    */      try {
/* 162:162 */        this.w.flush();
/* 163:    */      } catch (IOException e) {
/* 164:164 */        throw new SAXException("Failed to flush target writer: " + e.getMessage(), e);
/* 165:    */      }
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 174:    */  public void ignorableWhitespace(char[] ch, int start, int length)
/* 175:    */    throws SAXException
/* 176:    */  {
/* 177:177 */    characters(ch, start, length);
/* 178:    */  }
/* 179:    */  
/* 180:    */  private void stopTerminator() throws IOException {
/* 181:181 */    if (this.state == 1) {
/* 182:182 */      if (this.w != null) {
/* 183:183 */        this.w.write(62);
/* 184:    */      }
/* 185:185 */      this.state = 2;
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 192:    */  public void characters(char[] ch, int start, int length)
/* 193:    */    throws SAXException
/* 194:    */  {
/* 195:    */    try
/* 196:    */    {
/* 197:197 */      stopTerminator();
/* 198:198 */      if (this.w == null) return;
/* 199:199 */      int end = start + length;
/* 200:200 */      for (int i = start; i < end; i++) {
/* 201:201 */        char c = ch[i];
/* 202:202 */        switch (c) {
/* 203:203 */        case '&':  this.w.write("&amp;");break;
/* 204:204 */        case '<':  this.w.write("&lt;");break;
/* 205:205 */        case '>':  this.w.write("&gt;");break;
/* 206:    */        case '\t': 
/* 207:    */        case '\n': 
/* 208:    */        case '\r': 
/* 209:209 */          this.w.write(c);break;
/* 210:    */        default: 
/* 211:211 */          if (canEncode(c)) {
/* 212:212 */            this.w.write(c);
/* 213:    */          } else {
/* 214:214 */            this.w.write("&#");
/* 215:215 */            this.w.write(Integer.toString(c));
/* 216:216 */            this.w.write(";");
/* 217:    */          }
/* 218:    */          break;
/* 219:    */        }
/* 220:    */      }
/* 221:    */    } catch (IOException e) {
/* 222:222 */      throw new SAXException(e);
/* 223:    */    }
/* 224:    */  }
/* 225:    */  
/* 226:    */  public boolean canEncode(char c) {
/* 227:227 */    return (c == '\n') || ((c >= ' ') && (c < ''));
/* 228:    */  }
/* 229:    */  
/* 232:    */  private boolean indenting;
/* 233:    */  
/* 235:    */  private boolean flushing;
/* 236:    */  
/* 237:    */  public void endElement(String namespaceURI, String localName, String qName)
/* 238:    */    throws SAXException
/* 239:    */  {
/* 240:240 */    if (isIndenting()) {
/* 241:241 */      this.curIndent -= 1;
/* 242:    */    }
/* 243:243 */    if (this.w != null) {
/* 244:    */      try {
/* 245:245 */        if (this.state == 1) {
/* 246:246 */          this.w.write("/>");
/* 247:247 */          this.state = 0;
/* 248:    */        } else {
/* 249:249 */          if (this.state == 0) {
/* 250:250 */            indentMe();
/* 251:    */          }
/* 252:252 */          this.w.write("</");
/* 253:253 */          this.w.write(qName);
/* 254:254 */          this.w.write(62);
/* 255:    */        }
/* 256:256 */        this.state = 0;
/* 257:    */      } catch (IOException e) {
/* 258:258 */        throw new SAXException(e);
/* 259:    */      }
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 263:    */  private void indentMe() throws IOException {
/* 264:264 */    if ((this.w != null) && 
/* 265:265 */      (isIndenting())) {
/* 266:266 */      String s = getLineFeed();
/* 267:267 */      if (s != null) {
/* 268:268 */        this.w.write(s);
/* 269:    */      }
/* 270:270 */      s = getIndentString();
/* 271:271 */      if (s != null) {
/* 272:272 */        for (int i = 0; i < this.curIndent; i++) {
/* 273:273 */          this.w.write(s);
/* 274:    */        }
/* 275:    */      }
/* 276:    */    }
/* 277:    */  }
/* 278:    */  
/* 279:    */  private void writeCData(String v) throws IOException
/* 280:    */  {
/* 281:281 */    int len = v.length();
/* 282:282 */    for (int j = 0; j < len; j++) {
/* 283:283 */      char c = v.charAt(j);
/* 284:284 */      switch (c) {
/* 285:285 */      case '&':  this.w.write("&amp;");break;
/* 286:286 */      case '<':  this.w.write("&lt;");break;
/* 287:287 */      case '>':  this.w.write("&gt;");break;
/* 288:288 */      case '\'':  this.w.write("&apos;");break;
/* 289:289 */      case '"':  this.w.write("&quot;");break;
/* 290:    */      default: 
/* 291:291 */        if (canEncode(c)) {
/* 292:292 */          this.w.write(c);
/* 293:    */        } else {
/* 294:294 */          this.w.write("&#");
/* 295:295 */          this.w.write(Integer.toString(c));
/* 296:296 */          this.w.write(59);
/* 297:    */        }
/* 298:    */        
/* 301:    */        break;
/* 302:    */      }
/* 303:    */      
/* 304:    */    }
/* 305:    */  }
/* 306:    */  
/* 309:    */  public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
/* 310:    */    throws SAXException
/* 311:    */  {
/* 312:    */    try
/* 313:    */    {
/* 314:314 */      stopTerminator();
/* 315:315 */      if (isIndenting()) {
/* 316:316 */        if (this.curIndent > 0) {
/* 317:317 */          indentMe();
/* 318:    */        }
/* 319:319 */        this.curIndent += 1;
/* 320:    */      }
/* 321:    */      
/* 322:322 */      if (this.w != null) {
/* 323:323 */        this.w.write(60);
/* 324:324 */        this.w.write(qName);
/* 325:325 */        int i; if (attr != null) {
/* 326:326 */          for (i = attr.getLength(); i > 0;) {
/* 327:327 */            this.w.write(32);
/* 328:328 */            String name = attr.getQName(--i);
/* 329:329 */            this.w.write(name);
/* 330:330 */            if (this.delayedPrefixes != null) {
/* 331:331 */              this.delayedPrefixes.remove(name);
/* 332:    */            }
/* 333:333 */            this.w.write("=\"");
/* 334:334 */            writeCData(attr.getValue(i));
/* 335:335 */            this.w.write(34);
/* 336:    */          }
/* 337:    */        }
/* 338:338 */        if ((this.delayedPrefixes != null) && (this.delayedPrefixes.size() > 0)) {
/* 339:339 */          Iterator iter = this.delayedPrefixes.entrySet().iterator();
/* 340:340 */          while (iter.hasNext()) {
/* 341:341 */            Map.Entry entry = (Map.Entry)iter.next();
/* 342:342 */            this.w.write(32);
/* 343:343 */            this.w.write((String)entry.getKey());
/* 344:344 */            this.w.write("=\"");
/* 345:345 */            this.w.write((String)entry.getValue());
/* 346:346 */            this.w.write(34);
/* 347:    */          }
/* 348:348 */          this.delayedPrefixes.clear();
/* 349:    */        }
/* 350:    */      }
/* 351:351 */      this.state = 1;
/* 352:    */    } catch (IOException e) {
/* 353:353 */      throw new SAXException(e);
/* 354:    */    }
/* 355:    */  }
/* 356:    */  
/* 360:    */  public void skippedEntity(String ent)
/* 361:    */    throws SAXException
/* 362:    */  {
/* 363:363 */    throw new SAXException("Don't know how to skip entities");
/* 364:    */  }
/* 365:    */  
/* 370:    */  public void processingInstruction(String target, String data)
/* 371:    */    throws SAXException
/* 372:    */  {
/* 373:    */    try
/* 374:    */    {
/* 375:375 */      stopTerminator();
/* 376:376 */      if (this.w != null) {
/* 377:377 */        this.w.write("<?");
/* 378:378 */        this.w.write(target);
/* 379:379 */        this.w.write(32);
/* 380:380 */        this.w.write(data);
/* 381:381 */        this.w.write("?>");
/* 382:    */      }
/* 383:    */    } catch (IOException e) {
/* 384:384 */      throw new SAXException(e);
/* 385:    */    }
/* 386:    */  }
/* 387:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.XMLWriterImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */