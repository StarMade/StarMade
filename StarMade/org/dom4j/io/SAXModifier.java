/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.Reader;
/*   6:    */import java.net.URL;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.Map.Entry;
/*  10:    */import java.util.Set;
/*  11:    */import org.dom4j.Document;
/*  12:    */import org.dom4j.DocumentException;
/*  13:    */import org.dom4j.DocumentFactory;
/*  14:    */import org.xml.sax.InputSource;
/*  15:    */import org.xml.sax.SAXException;
/*  16:    */import org.xml.sax.XMLReader;
/*  17:    */
/*  47:    */public class SAXModifier
/*  48:    */{
/*  49:    */  private XMLWriter xmlWriter;
/*  50:    */  private XMLReader xmlReader;
/*  51:    */  private boolean pruneElements;
/*  52:    */  private SAXModifyReader modifyReader;
/*  53: 53 */  private HashMap modifiers = new HashMap();
/*  54:    */  
/*  63:    */  public SAXModifier() {}
/*  64:    */  
/*  73:    */  public SAXModifier(boolean pruneElements)
/*  74:    */  {
/*  75: 75 */    this.pruneElements = pruneElements;
/*  76:    */  }
/*  77:    */  
/*  84:    */  public SAXModifier(XMLReader xmlReader)
/*  85:    */  {
/*  86: 86 */    this.xmlReader = xmlReader;
/*  87:    */  }
/*  88:    */  
/*  98:    */  public SAXModifier(XMLReader xmlReader, boolean pruneElements)
/*  99:    */  {
/* 100:100 */    this.xmlReader = xmlReader;
/* 101:    */  }
/* 102:    */  
/* 114:    */  public Document modify(File source)
/* 115:    */    throws DocumentException
/* 116:    */  {
/* 117:    */    try
/* 118:    */    {
/* 119:119 */      return installModifyReader().read(source);
/* 120:    */    } catch (SAXModifyException ex) {
/* 121:121 */      Throwable cause = ex.getCause();
/* 122:122 */      throw new DocumentException(cause.getMessage(), cause);
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 137:    */  public Document modify(InputSource source)
/* 138:    */    throws DocumentException
/* 139:    */  {
/* 140:    */    try
/* 141:    */    {
/* 142:142 */      return installModifyReader().read(source);
/* 143:    */    } catch (SAXModifyException ex) {
/* 144:144 */      Throwable cause = ex.getCause();
/* 145:145 */      throw new DocumentException(cause.getMessage(), cause);
/* 146:    */    }
/* 147:    */  }
/* 148:    */  
/* 160:    */  public Document modify(InputStream source)
/* 161:    */    throws DocumentException
/* 162:    */  {
/* 163:    */    try
/* 164:    */    {
/* 165:165 */      return installModifyReader().read(source);
/* 166:    */    } catch (SAXModifyException ex) {
/* 167:167 */      Throwable cause = ex.getCause();
/* 168:168 */      throw new DocumentException(cause.getMessage(), cause);
/* 169:    */    }
/* 170:    */  }
/* 171:    */  
/* 186:    */  public Document modify(InputStream source, String systemId)
/* 187:    */    throws DocumentException
/* 188:    */  {
/* 189:    */    try
/* 190:    */    {
/* 191:191 */      return installModifyReader().read(source);
/* 192:    */    } catch (SAXModifyException ex) {
/* 193:193 */      Throwable cause = ex.getCause();
/* 194:194 */      throw new DocumentException(cause.getMessage(), cause);
/* 195:    */    }
/* 196:    */  }
/* 197:    */  
/* 209:    */  public Document modify(Reader source)
/* 210:    */    throws DocumentException
/* 211:    */  {
/* 212:    */    try
/* 213:    */    {
/* 214:214 */      return installModifyReader().read(source);
/* 215:    */    } catch (SAXModifyException ex) {
/* 216:216 */      Throwable cause = ex.getCause();
/* 217:217 */      throw new DocumentException(cause.getMessage(), cause);
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 235:    */  public Document modify(Reader source, String systemId)
/* 236:    */    throws DocumentException
/* 237:    */  {
/* 238:    */    try
/* 239:    */    {
/* 240:240 */      return installModifyReader().read(source);
/* 241:    */    } catch (SAXModifyException ex) {
/* 242:242 */      Throwable cause = ex.getCause();
/* 243:243 */      throw new DocumentException(cause.getMessage(), cause);
/* 244:    */    }
/* 245:    */  }
/* 246:    */  
/* 258:    */  public Document modify(URL source)
/* 259:    */    throws DocumentException
/* 260:    */  {
/* 261:    */    try
/* 262:    */    {
/* 263:263 */      return installModifyReader().read(source);
/* 264:    */    } catch (SAXModifyException ex) {
/* 265:265 */      Throwable cause = ex.getCause();
/* 266:266 */      throw new DocumentException(cause.getMessage(), cause);
/* 267:    */    }
/* 268:    */  }
/* 269:    */  
/* 281:    */  public Document modify(String source)
/* 282:    */    throws DocumentException
/* 283:    */  {
/* 284:    */    try
/* 285:    */    {
/* 286:286 */      return installModifyReader().read(source);
/* 287:    */    } catch (SAXModifyException ex) {
/* 288:288 */      Throwable cause = ex.getCause();
/* 289:289 */      throw new DocumentException(cause.getMessage(), cause);
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 302:    */  public void addModifier(String path, ElementModifier modifier)
/* 303:    */  {
/* 304:304 */    this.modifiers.put(path, modifier);
/* 305:    */  }
/* 306:    */  
/* 310:    */  public void resetModifiers()
/* 311:    */  {
/* 312:312 */    this.modifiers.clear();
/* 313:313 */    getSAXModifyReader().resetHandlers();
/* 314:    */  }
/* 315:    */  
/* 322:    */  public void removeModifier(String path)
/* 323:    */  {
/* 324:324 */    this.modifiers.remove(path);
/* 325:325 */    getSAXModifyReader().removeHandler(path);
/* 326:    */  }
/* 327:    */  
/* 333:    */  public DocumentFactory getDocumentFactory()
/* 334:    */  {
/* 335:335 */    return getSAXModifyReader().getDocumentFactory();
/* 336:    */  }
/* 337:    */  
/* 344:    */  public void setDocumentFactory(DocumentFactory factory)
/* 345:    */  {
/* 346:346 */    getSAXModifyReader().setDocumentFactory(factory);
/* 347:    */  }
/* 348:    */  
/* 353:    */  public XMLWriter getXMLWriter()
/* 354:    */  {
/* 355:355 */    return this.xmlWriter;
/* 356:    */  }
/* 357:    */  
/* 363:    */  public void setXMLWriter(XMLWriter writer)
/* 364:    */  {
/* 365:365 */    this.xmlWriter = writer;
/* 366:    */  }
/* 367:    */  
/* 373:    */  public boolean isPruneElements()
/* 374:    */  {
/* 375:375 */    return this.pruneElements;
/* 376:    */  }
/* 377:    */  
/* 378:    */  private SAXReader installModifyReader() throws DocumentException {
/* 379:    */    try {
/* 380:380 */      SAXModifyReader reader = getSAXModifyReader();
/* 381:    */      
/* 382:382 */      if (isPruneElements()) {
/* 383:383 */        this.modifyReader.setDispatchHandler(new PruningDispatchHandler());
/* 384:    */      }
/* 385:    */      
/* 386:386 */      reader.resetHandlers();
/* 387:    */      
/* 388:388 */      Iterator modifierIt = this.modifiers.entrySet().iterator();
/* 389:    */      
/* 390:390 */      while (modifierIt.hasNext()) {
/* 391:391 */        Map.Entry entry = (Map.Entry)modifierIt.next();
/* 392:    */        
/* 393:393 */        SAXModifyElementHandler handler = new SAXModifyElementHandler((ElementModifier)entry.getValue());
/* 394:    */        
/* 395:395 */        reader.addHandler((String)entry.getKey(), handler);
/* 396:    */      }
/* 397:    */      
/* 398:398 */      reader.setXMLWriter(getXMLWriter());
/* 399:399 */      reader.setXMLReader(getXMLReader());
/* 400:    */      
/* 401:401 */      return reader;
/* 402:    */    } catch (SAXException ex) {
/* 403:403 */      throw new DocumentException(ex.getMessage(), ex);
/* 404:    */    }
/* 405:    */  }
/* 406:    */  
/* 407:    */  private XMLReader getXMLReader() throws SAXException {
/* 408:408 */    if (this.xmlReader == null) {
/* 409:409 */      this.xmlReader = SAXHelper.createXMLReader(false);
/* 410:    */    }
/* 411:    */    
/* 412:412 */    return this.xmlReader;
/* 413:    */  }
/* 414:    */  
/* 415:    */  private SAXModifyReader getSAXModifyReader() {
/* 416:416 */    if (this.modifyReader == null) {
/* 417:417 */      this.modifyReader = new SAXModifyReader();
/* 418:    */    }
/* 419:    */    
/* 420:420 */    return this.modifyReader;
/* 421:    */  }
/* 422:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */