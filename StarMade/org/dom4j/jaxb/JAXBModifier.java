/*   1:    */package org.dom4j.jaxb;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileInputStream;
/*   5:    */import java.io.FileNotFoundException;
/*   6:    */import java.io.FileOutputStream;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.InputStream;
/*   9:    */import java.io.InputStreamReader;
/*  10:    */import java.io.OutputStream;
/*  11:    */import java.io.Reader;
/*  12:    */import java.io.Writer;
/*  13:    */import java.net.URL;
/*  14:    */import java.nio.charset.Charset;
/*  15:    */import java.util.HashMap;
/*  16:    */import java.util.Iterator;
/*  17:    */import java.util.Map.Entry;
/*  18:    */import java.util.Set;
/*  19:    */import org.dom4j.Document;
/*  20:    */import org.dom4j.DocumentException;
/*  21:    */import org.dom4j.io.ElementModifier;
/*  22:    */import org.dom4j.io.OutputFormat;
/*  23:    */import org.dom4j.io.SAXModifier;
/*  24:    */import org.dom4j.io.XMLWriter;
/*  25:    */import org.xml.sax.InputSource;
/*  26:    */
/*  47:    */public class JAXBModifier
/*  48:    */  extends JAXBSupport
/*  49:    */{
/*  50:    */  private SAXModifier modifier;
/*  51:    */  private XMLWriter xmlWriter;
/*  52:    */  private boolean pruneElements;
/*  53:    */  private OutputFormat outputFormat;
/*  54: 54 */  private HashMap modifiers = new HashMap();
/*  55:    */  
/*  65:    */  public JAXBModifier(String contextPath)
/*  66:    */  {
/*  67: 67 */    super(contextPath);
/*  68: 68 */    this.outputFormat = new OutputFormat();
/*  69:    */  }
/*  70:    */  
/*  83:    */  public JAXBModifier(String contextPath, ClassLoader classloader)
/*  84:    */  {
/*  85: 85 */    super(contextPath, classloader);
/*  86: 86 */    this.outputFormat = new OutputFormat();
/*  87:    */  }
/*  88:    */  
/* 100:    */  public JAXBModifier(String contextPath, OutputFormat outputFormat)
/* 101:    */  {
/* 102:102 */    super(contextPath);
/* 103:103 */    this.outputFormat = outputFormat;
/* 104:    */  }
/* 105:    */  
/* 120:    */  public JAXBModifier(String contextPath, ClassLoader classloader, OutputFormat outputFormat)
/* 121:    */  {
/* 122:122 */    super(contextPath, classloader);
/* 123:123 */    this.outputFormat = outputFormat;
/* 124:    */  }
/* 125:    */  
/* 138:    */  public Document modify(File source)
/* 139:    */    throws DocumentException, IOException
/* 140:    */  {
/* 141:141 */    return installModifier().modify(source);
/* 142:    */  }
/* 143:    */  
/* 159:    */  public Document modify(File source, Charset charset)
/* 160:    */    throws DocumentException, IOException
/* 161:    */  {
/* 162:    */    try
/* 163:    */    {
/* 164:164 */      Reader reader = new InputStreamReader(new FileInputStream(source), charset);
/* 165:    */      
/* 167:167 */      return installModifier().modify(reader);
/* 168:    */    } catch (JAXBRuntimeException ex) {
/* 169:169 */      Throwable cause = ex.getCause();
/* 170:170 */      throw new DocumentException(cause.getMessage(), cause);
/* 171:    */    } catch (FileNotFoundException ex) {
/* 172:172 */      throw new DocumentException(ex.getMessage(), ex);
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 188:    */  public Document modify(InputSource source)
/* 189:    */    throws DocumentException, IOException
/* 190:    */  {
/* 191:    */    try
/* 192:    */    {
/* 193:193 */      return installModifier().modify(source);
/* 194:    */    } catch (JAXBRuntimeException ex) {
/* 195:195 */      Throwable cause = ex.getCause();
/* 196:196 */      throw new DocumentException(cause.getMessage(), cause);
/* 197:    */    }
/* 198:    */  }
/* 199:    */  
/* 212:    */  public Document modify(InputStream source)
/* 213:    */    throws DocumentException, IOException
/* 214:    */  {
/* 215:    */    try
/* 216:    */    {
/* 217:217 */      return installModifier().modify(source);
/* 218:    */    } catch (JAXBRuntimeException ex) {
/* 219:219 */      Throwable cause = ex.getCause();
/* 220:220 */      throw new DocumentException(cause.getMessage(), cause);
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 238:    */  public Document modify(InputStream source, String systemId)
/* 239:    */    throws DocumentException, IOException
/* 240:    */  {
/* 241:    */    try
/* 242:    */    {
/* 243:243 */      return installModifier().modify(source);
/* 244:    */    } catch (JAXBRuntimeException ex) {
/* 245:245 */      Throwable cause = ex.getCause();
/* 246:246 */      throw new DocumentException(cause.getMessage(), cause);
/* 247:    */    }
/* 248:    */  }
/* 249:    */  
/* 261:    */  public Document modify(Reader r)
/* 262:    */    throws DocumentException, IOException
/* 263:    */  {
/* 264:    */    try
/* 265:    */    {
/* 266:266 */      return installModifier().modify(r);
/* 267:    */    } catch (JAXBRuntimeException ex) {
/* 268:268 */      Throwable cause = ex.getCause();
/* 269:269 */      throw new DocumentException(cause.getMessage(), cause);
/* 270:    */    }
/* 271:    */  }
/* 272:    */  
/* 287:    */  public Document modify(Reader source, String systemId)
/* 288:    */    throws DocumentException, IOException
/* 289:    */  {
/* 290:    */    try
/* 291:    */    {
/* 292:292 */      return installModifier().modify(source);
/* 293:    */    } catch (JAXBRuntimeException ex) {
/* 294:294 */      Throwable cause = ex.getCause();
/* 295:295 */      throw new DocumentException(cause.getMessage(), cause);
/* 296:    */    }
/* 297:    */  }
/* 298:    */  
/* 310:    */  public Document modify(String url)
/* 311:    */    throws DocumentException, IOException
/* 312:    */  {
/* 313:    */    try
/* 314:    */    {
/* 315:315 */      return installModifier().modify(url);
/* 316:    */    } catch (JAXBRuntimeException ex) {
/* 317:317 */      Throwable cause = ex.getCause();
/* 318:318 */      throw new DocumentException(cause.getMessage(), cause);
/* 319:    */    }
/* 320:    */  }
/* 321:    */  
/* 333:    */  public Document modify(URL source)
/* 334:    */    throws DocumentException, IOException
/* 335:    */  {
/* 336:    */    try
/* 337:    */    {
/* 338:338 */      return installModifier().modify(source);
/* 339:    */    } catch (JAXBRuntimeException ex) {
/* 340:340 */      Throwable cause = ex.getCause();
/* 341:341 */      throw new DocumentException(cause.getMessage(), cause);
/* 342:    */    }
/* 343:    */  }
/* 344:    */  
/* 352:    */  public void setOutput(File file)
/* 353:    */    throws IOException
/* 354:    */  {
/* 355:355 */    createXMLWriter().setOutputStream(new FileOutputStream(file));
/* 356:    */  }
/* 357:    */  
/* 365:    */  public void setOutput(OutputStream outputStream)
/* 366:    */    throws IOException
/* 367:    */  {
/* 368:368 */    createXMLWriter().setOutputStream(outputStream);
/* 369:    */  }
/* 370:    */  
/* 378:    */  public void setOutput(Writer writer)
/* 379:    */    throws IOException
/* 380:    */  {
/* 381:381 */    createXMLWriter().setWriter(writer);
/* 382:    */  }
/* 383:    */  
/* 392:    */  public void addObjectModifier(String path, JAXBObjectModifier mod)
/* 393:    */  {
/* 394:394 */    this.modifiers.put(path, mod);
/* 395:    */  }
/* 396:    */  
/* 403:    */  public void removeObjectModifier(String path)
/* 404:    */  {
/* 405:405 */    this.modifiers.remove(path);
/* 406:406 */    getModifier().removeModifier(path);
/* 407:    */  }
/* 408:    */  
/* 412:    */  public void resetObjectModifiers()
/* 413:    */  {
/* 414:414 */    this.modifiers.clear();
/* 415:415 */    getModifier().resetModifiers();
/* 416:    */  }
/* 417:    */  
/* 423:    */  public boolean isPruneElements()
/* 424:    */  {
/* 425:425 */    return this.pruneElements;
/* 426:    */  }
/* 427:    */  
/* 434:    */  public void setPruneElements(boolean pruneElements)
/* 435:    */  {
/* 436:436 */    this.pruneElements = pruneElements;
/* 437:    */  }
/* 438:    */  
/* 439:    */  private SAXModifier installModifier() throws IOException {
/* 440:440 */    this.modifier = new SAXModifier(isPruneElements());
/* 441:    */    
/* 442:442 */    this.modifier.resetModifiers();
/* 443:    */    
/* 444:444 */    Iterator modifierIt = this.modifiers.entrySet().iterator();
/* 445:    */    
/* 446:446 */    while (modifierIt.hasNext()) {
/* 447:447 */      Map.Entry entry = (Map.Entry)modifierIt.next();
/* 448:448 */      ElementModifier mod = new JAXBElementModifier(this, (JAXBObjectModifier)entry.getValue());
/* 449:    */      
/* 450:450 */      getModifier().addModifier((String)entry.getKey(), mod);
/* 451:    */    }
/* 452:    */    
/* 453:453 */    this.modifier.setXMLWriter(getXMLWriter());
/* 454:    */    
/* 455:455 */    return this.modifier;
/* 456:    */  }
/* 457:    */  
/* 458:    */  private SAXModifier getModifier() {
/* 459:459 */    if (this.modifier == null) {
/* 460:460 */      this.modifier = new SAXModifier(isPruneElements());
/* 461:    */    }
/* 462:    */    
/* 463:463 */    return this.modifier;
/* 464:    */  }
/* 465:    */  
/* 466:    */  private XMLWriter getXMLWriter() {
/* 467:467 */    return this.xmlWriter;
/* 468:    */  }
/* 469:    */  
/* 470:    */  private XMLWriter createXMLWriter() throws IOException {
/* 471:471 */    if (this.xmlWriter == null) {
/* 472:472 */      this.xmlWriter = new XMLWriter(this.outputFormat);
/* 473:    */    }
/* 474:    */    
/* 475:475 */    return this.xmlWriter;
/* 476:    */  }
/* 477:    */  
/* 478:    */  private class JAXBElementModifier implements ElementModifier
/* 479:    */  {
/* 480:    */    private JAXBModifier jaxbModifier;
/* 481:    */    private JAXBObjectModifier objectModifier;
/* 482:    */    
/* 483:    */    public JAXBElementModifier(JAXBModifier jaxbModifier, JAXBObjectModifier objectModifier)
/* 484:    */    {
/* 485:485 */      this.jaxbModifier = jaxbModifier;
/* 486:486 */      this.objectModifier = objectModifier;
/* 487:    */    }
/* 488:    */    
/* 489:    */    public org.dom4j.Element modifyElement(org.dom4j.Element element) throws Exception
/* 490:    */    {
/* 491:491 */      javax.xml.bind.Element originalObject = this.jaxbModifier.unmarshal(element);
/* 492:    */      
/* 493:493 */      javax.xml.bind.Element modifiedObject = this.objectModifier.modifyObject(originalObject);
/* 494:    */      
/* 496:496 */      return this.jaxbModifier.marshal(modifiedObject);
/* 497:    */    }
/* 498:    */  }
/* 499:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBModifier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */