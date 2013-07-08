/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileReader;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.InputStreamReader;
/*   9:    */import java.io.Reader;
/*  10:    */import java.net.URL;
/*  11:    */import org.dom4j.Document;
/*  12:    */import org.dom4j.DocumentException;
/*  13:    */import org.dom4j.DocumentFactory;
/*  14:    */import org.dom4j.Element;
/*  15:    */import org.dom4j.ElementHandler;
/*  16:    */import org.dom4j.xpp.ProxyXmlStartTag;
/*  17:    */import org.gjt.xpp.XmlEndTag;
/*  18:    */import org.gjt.xpp.XmlPullParser;
/*  19:    */import org.gjt.xpp.XmlPullParserException;
/*  20:    */import org.gjt.xpp.XmlPullParserFactory;
/*  21:    */
/*  48:    */public class XPPReader
/*  49:    */{
/*  50:    */  private DocumentFactory factory;
/*  51:    */  private XmlPullParser xppParser;
/*  52:    */  private XmlPullParserFactory xppFactory;
/*  53:    */  private DispatchHandler dispatchHandler;
/*  54:    */  
/*  55:    */  public XPPReader() {}
/*  56:    */  
/*  57:    */  public XPPReader(DocumentFactory factory)
/*  58:    */  {
/*  59: 59 */    this.factory = factory;
/*  60:    */  }
/*  61:    */  
/*  78:    */  public Document read(File file)
/*  79:    */    throws DocumentException, IOException, XmlPullParserException
/*  80:    */  {
/*  81: 81 */    String systemID = file.getAbsolutePath();
/*  82:    */    
/*  83: 83 */    return read(new BufferedReader(new FileReader(file)), systemID);
/*  84:    */  }
/*  85:    */  
/* 102:    */  public Document read(URL url)
/* 103:    */    throws DocumentException, IOException, XmlPullParserException
/* 104:    */  {
/* 105:105 */    String systemID = url.toExternalForm();
/* 106:    */    
/* 107:107 */    return read(createReader(url.openStream()), systemID);
/* 108:    */  }
/* 109:    */  
/* 134:    */  public Document read(String systemID)
/* 135:    */    throws DocumentException, IOException, XmlPullParserException
/* 136:    */  {
/* 137:137 */    if (systemID.indexOf(':') >= 0)
/* 138:    */    {
/* 139:139 */      return read(new URL(systemID));
/* 140:    */    }
/* 141:    */    
/* 142:142 */    return read(new File(systemID));
/* 143:    */  }
/* 144:    */  
/* 162:    */  public Document read(InputStream in)
/* 163:    */    throws DocumentException, IOException, XmlPullParserException
/* 164:    */  {
/* 165:165 */    return read(createReader(in));
/* 166:    */  }
/* 167:    */  
/* 184:    */  public Document read(Reader reader)
/* 185:    */    throws DocumentException, IOException, XmlPullParserException
/* 186:    */  {
/* 187:187 */    getXPPParser().setInput(reader);
/* 188:    */    
/* 189:189 */    return parseDocument();
/* 190:    */  }
/* 191:    */  
/* 208:    */  public Document read(char[] text)
/* 209:    */    throws DocumentException, IOException, XmlPullParserException
/* 210:    */  {
/* 211:211 */    getXPPParser().setInput(text);
/* 212:    */    
/* 213:213 */    return parseDocument();
/* 214:    */  }
/* 215:    */  
/* 234:    */  public Document read(InputStream in, String systemID)
/* 235:    */    throws DocumentException, IOException, XmlPullParserException
/* 236:    */  {
/* 237:237 */    return read(createReader(in), systemID);
/* 238:    */  }
/* 239:    */  
/* 258:    */  public Document read(Reader reader, String systemID)
/* 259:    */    throws DocumentException, IOException, XmlPullParserException
/* 260:    */  {
/* 261:261 */    Document document = read(reader);
/* 262:262 */    document.setName(systemID);
/* 263:    */    
/* 264:264 */    return document;
/* 265:    */  }
/* 266:    */  
/* 267:    */  public XmlPullParser getXPPParser()
/* 268:    */    throws XmlPullParserException
/* 269:    */  {
/* 270:270 */    if (this.xppParser == null) {
/* 271:271 */      this.xppParser = getXPPFactory().newPullParser();
/* 272:    */    }
/* 273:    */    
/* 274:274 */    return this.xppParser;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public XmlPullParserFactory getXPPFactory() throws XmlPullParserException {
/* 278:278 */    if (this.xppFactory == null) {
/* 279:279 */      this.xppFactory = XmlPullParserFactory.newInstance();
/* 280:    */    }
/* 281:    */    
/* 282:282 */    return this.xppFactory;
/* 283:    */  }
/* 284:    */  
/* 285:    */  public void setXPPFactory(XmlPullParserFactory xPPFactory) {
/* 286:286 */    this.xppFactory = xPPFactory;
/* 287:    */  }
/* 288:    */  
/* 294:    */  public DocumentFactory getDocumentFactory()
/* 295:    */  {
/* 296:296 */    if (this.factory == null) {
/* 297:297 */      this.factory = DocumentFactory.getInstance();
/* 298:    */    }
/* 299:    */    
/* 300:300 */    return this.factory;
/* 301:    */  }
/* 302:    */  
/* 313:    */  public void setDocumentFactory(DocumentFactory documentFactory)
/* 314:    */  {
/* 315:315 */    this.factory = documentFactory;
/* 316:    */  }
/* 317:    */  
/* 327:    */  public void addHandler(String path, ElementHandler handler)
/* 328:    */  {
/* 329:329 */    getDispatchHandler().addHandler(path, handler);
/* 330:    */  }
/* 331:    */  
/* 338:    */  public void removeHandler(String path)
/* 339:    */  {
/* 340:340 */    getDispatchHandler().removeHandler(path);
/* 341:    */  }
/* 342:    */  
/* 351:    */  public void setDefaultHandler(ElementHandler handler)
/* 352:    */  {
/* 353:353 */    getDispatchHandler().setDefaultHandler(handler);
/* 354:    */  }
/* 355:    */  
/* 357:    */  protected Document parseDocument()
/* 358:    */    throws DocumentException, IOException, XmlPullParserException
/* 359:    */  {
/* 360:360 */    Document document = getDocumentFactory().createDocument();
/* 361:361 */    Element parent = null;
/* 362:362 */    XmlPullParser parser = getXPPParser();
/* 363:363 */    parser.setNamespaceAware(true);
/* 364:    */    
/* 365:365 */    ProxyXmlStartTag startTag = new ProxyXmlStartTag();
/* 366:366 */    XmlEndTag endTag = this.xppFactory.newEndTag();
/* 367:    */    for (;;)
/* 368:    */    {
/* 369:369 */      int type = parser.next();
/* 370:    */      
/* 371:371 */      switch (type) {
/* 372:    */      case 1: 
/* 373:373 */        return document;
/* 374:    */      
/* 375:    */      case 2: 
/* 376:376 */        parser.readStartTag(startTag);
/* 377:    */        
/* 378:378 */        Element newElement = startTag.getElement();
/* 379:    */        
/* 380:380 */        if (parent != null) {
/* 381:381 */          parent.add(newElement);
/* 382:    */        } else {
/* 383:383 */          document.add(newElement);
/* 384:    */        }
/* 385:    */        
/* 386:386 */        parent = newElement;
/* 387:    */        
/* 388:388 */        break;
/* 389:    */      
/* 391:    */      case 3: 
/* 392:392 */        parser.readEndTag(endTag);
/* 393:    */        
/* 394:394 */        if (parent != null)
/* 395:395 */          parent = parent.getParent(); break;
/* 396:    */      
/* 401:    */      case 4: 
/* 402:402 */        String text = parser.readContent();
/* 403:    */        
/* 404:404 */        if (parent != null) {
/* 405:405 */          parent.addText(text);
/* 406:    */        } else {
/* 407:407 */          String msg = "Cannot have text content outside of the root document";
/* 408:    */          
/* 409:409 */          throw new DocumentException(msg);
/* 410:    */        }
/* 411:    */        
/* 414:    */        break;
/* 415:    */      default: 
/* 416:416 */        throw new DocumentException("Error: unknown type: " + type);
/* 417:    */      }
/* 418:    */    }
/* 419:    */  }
/* 420:    */  
/* 421:    */  protected DispatchHandler getDispatchHandler() {
/* 422:422 */    if (this.dispatchHandler == null) {
/* 423:423 */      this.dispatchHandler = new DispatchHandler();
/* 424:    */    }
/* 425:    */    
/* 426:426 */    return this.dispatchHandler;
/* 427:    */  }
/* 428:    */  
/* 429:    */  protected void setDispatchHandler(DispatchHandler dispatchHandler) {
/* 430:430 */    this.dispatchHandler = dispatchHandler;
/* 431:    */  }
/* 432:    */  
/* 442:    */  protected Reader createReader(InputStream in)
/* 443:    */    throws IOException
/* 444:    */  {
/* 445:445 */    return new BufferedReader(new InputStreamReader(in));
/* 446:    */  }
/* 447:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XPPReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */