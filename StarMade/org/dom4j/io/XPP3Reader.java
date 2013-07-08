/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.CharArrayReader;
/*   5:    */import java.io.File;
/*   6:    */import java.io.FileReader;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.InputStream;
/*   9:    */import java.io.InputStreamReader;
/*  10:    */import java.io.Reader;
/*  11:    */import java.net.URL;
/*  12:    */import org.dom4j.Document;
/*  13:    */import org.dom4j.DocumentException;
/*  14:    */import org.dom4j.DocumentFactory;
/*  15:    */import org.dom4j.Element;
/*  16:    */import org.dom4j.ElementHandler;
/*  17:    */import org.dom4j.QName;
/*  18:    */import org.xmlpull.v1.XmlPullParser;
/*  19:    */import org.xmlpull.v1.XmlPullParserException;
/*  20:    */import org.xmlpull.v1.XmlPullParserFactory;
/*  21:    */
/*  48:    */public class XPP3Reader
/*  49:    */{
/*  50:    */  private DocumentFactory factory;
/*  51:    */  private XmlPullParser xppParser;
/*  52:    */  private XmlPullParserFactory xppFactory;
/*  53:    */  private DispatchHandler dispatchHandler;
/*  54:    */  
/*  55:    */  public XPP3Reader() {}
/*  56:    */  
/*  57:    */  public XPP3Reader(DocumentFactory factory)
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
/* 211:211 */    getXPPParser().setInput(new CharArrayReader(text));
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
/* 282:282 */    this.xppFactory.setNamespaceAware(true);
/* 283:    */    
/* 284:284 */    return this.xppFactory;
/* 285:    */  }
/* 286:    */  
/* 287:    */  public void setXPPFactory(XmlPullParserFactory xPPfactory) {
/* 288:288 */    this.xppFactory = xPPfactory;
/* 289:    */  }
/* 290:    */  
/* 296:    */  public DocumentFactory getDocumentFactory()
/* 297:    */  {
/* 298:298 */    if (this.factory == null) {
/* 299:299 */      this.factory = DocumentFactory.getInstance();
/* 300:    */    }
/* 301:    */    
/* 302:302 */    return this.factory;
/* 303:    */  }
/* 304:    */  
/* 315:    */  public void setDocumentFactory(DocumentFactory documentFactory)
/* 316:    */  {
/* 317:317 */    this.factory = documentFactory;
/* 318:    */  }
/* 319:    */  
/* 329:    */  public void addHandler(String path, ElementHandler handler)
/* 330:    */  {
/* 331:331 */    getDispatchHandler().addHandler(path, handler);
/* 332:    */  }
/* 333:    */  
/* 340:    */  public void removeHandler(String path)
/* 341:    */  {
/* 342:342 */    getDispatchHandler().removeHandler(path);
/* 343:    */  }
/* 344:    */  
/* 353:    */  public void setDefaultHandler(ElementHandler handler)
/* 354:    */  {
/* 355:355 */    getDispatchHandler().setDefaultHandler(handler);
/* 356:    */  }
/* 357:    */  
/* 359:    */  protected Document parseDocument()
/* 360:    */    throws DocumentException, IOException, XmlPullParserException
/* 361:    */  {
/* 362:362 */    DocumentFactory df = getDocumentFactory();
/* 363:363 */    Document document = df.createDocument();
/* 364:364 */    Element parent = null;
/* 365:365 */    XmlPullParser pp = getXPPParser();
/* 366:366 */    pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
/* 367:    */    for (;;)
/* 368:    */    {
/* 369:369 */      int type = pp.nextToken();
/* 370:    */      
/* 371:371 */      switch (type) {
/* 372:    */      case 8: 
/* 373:373 */        String text = pp.getText();
/* 374:374 */        int loc = text.indexOf(" ");
/* 375:    */        
/* 376:376 */        if (loc >= 0) {
/* 377:377 */          String target = text.substring(0, loc);
/* 378:378 */          String txt = text.substring(loc + 1);
/* 379:379 */          document.addProcessingInstruction(target, txt);
/* 380:    */        } else {
/* 381:381 */          document.addProcessingInstruction(text, "");
/* 382:    */        }
/* 383:    */        
/* 384:384 */        break;
/* 385:    */      
/* 387:    */      case 9: 
/* 388:388 */        if (parent != null) {
/* 389:389 */          parent.addComment(pp.getText());
/* 390:    */        } else {
/* 391:391 */          document.addComment(pp.getText());
/* 392:    */        }
/* 393:    */        
/* 394:394 */        break;
/* 395:    */      
/* 397:    */      case 5: 
/* 398:398 */        if (parent != null) {
/* 399:399 */          parent.addCDATA(pp.getText());
/* 400:    */        } else {
/* 401:401 */          String msg = "Cannot have text content outside of the root document";
/* 402:    */          
/* 403:403 */          throw new DocumentException(msg);
/* 404:    */        }
/* 405:    */        
/* 408:    */        break;
/* 409:    */      case 6: 
/* 410:410 */        break;
/* 411:    */      
/* 412:    */      case 1: 
/* 413:413 */        return document;
/* 414:    */      
/* 415:    */      case 2: 
/* 416:416 */        QName qname = pp.getPrefix() == null ? df.createQName(pp.getName(), pp.getNamespace()) : df.createQName(pp.getName(), pp.getPrefix(), pp.getNamespace());
/* 417:    */        
/* 419:419 */        Element newElement = df.createElement(qname);
/* 420:420 */        int nsStart = pp.getNamespaceCount(pp.getDepth() - 1);
/* 421:421 */        int nsEnd = pp.getNamespaceCount(pp.getDepth());
/* 422:    */        
/* 423:423 */        for (int i = nsStart; i < nsEnd; i++) {
/* 424:424 */          if (pp.getNamespacePrefix(i) != null) {
/* 425:425 */            newElement.addNamespace(pp.getNamespacePrefix(i), pp.getNamespaceUri(i));
/* 426:    */          }
/* 427:    */        }
/* 428:    */        
/* 430:430 */        for (int i = 0; i < pp.getAttributeCount(); i++) {
/* 431:431 */          QName qa = pp.getAttributePrefix(i) == null ? df.createQName(pp.getAttributeName(i)) : df.createQName(pp.getAttributeName(i), pp.getAttributePrefix(i), pp.getAttributeNamespace(i));
/* 432:    */          
/* 436:436 */          newElement.addAttribute(qa, pp.getAttributeValue(i));
/* 437:    */        }
/* 438:    */        
/* 439:439 */        if (parent != null) {
/* 440:440 */          parent.add(newElement);
/* 441:    */        } else {
/* 442:442 */          document.add(newElement);
/* 443:    */        }
/* 444:    */        
/* 445:445 */        parent = newElement;
/* 446:    */        
/* 447:447 */        break;
/* 448:    */      
/* 450:    */      case 3: 
/* 451:451 */        if (parent != null)
/* 452:452 */          parent = parent.getParent(); break;
/* 453:    */      
/* 458:    */      case 4: 
/* 459:459 */        String text = pp.getText();
/* 460:    */        
/* 461:461 */        if (parent != null) {
/* 462:462 */          parent.addText(text);
/* 463:    */        } else {
/* 464:464 */          String msg = "Cannot have text content outside of the root document";
/* 465:    */          
/* 466:466 */          throw new DocumentException(msg);
/* 467:    */        }
/* 468:    */        
/* 470:    */        break;
/* 471:    */      }
/* 472:    */      
/* 473:    */    }
/* 474:    */  }
/* 475:    */  
/* 477:    */  protected DispatchHandler getDispatchHandler()
/* 478:    */  {
/* 479:479 */    if (this.dispatchHandler == null) {
/* 480:480 */      this.dispatchHandler = new DispatchHandler();
/* 481:    */    }
/* 482:    */    
/* 483:483 */    return this.dispatchHandler;
/* 484:    */  }
/* 485:    */  
/* 486:    */  protected void setDispatchHandler(DispatchHandler dispatchHandler) {
/* 487:487 */    this.dispatchHandler = dispatchHandler;
/* 488:    */  }
/* 489:    */  
/* 499:    */  protected Reader createReader(InputStream in)
/* 500:    */    throws IOException
/* 501:    */  {
/* 502:502 */    return new BufferedReader(new InputStreamReader(in));
/* 503:    */  }
/* 504:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XPP3Reader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */