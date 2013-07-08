/*   1:    */package org.dom4j.jaxb;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileInputStream;
/*   5:    */import java.io.FileNotFoundException;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.io.InputStreamReader;
/*   8:    */import java.io.Reader;
/*   9:    */import java.net.URL;
/*  10:    */import java.nio.charset.Charset;
/*  11:    */import org.dom4j.Document;
/*  12:    */import org.dom4j.DocumentException;
/*  13:    */import org.dom4j.ElementHandler;
/*  14:    */import org.dom4j.ElementPath;
/*  15:    */import org.dom4j.io.SAXReader;
/*  16:    */import org.xml.sax.InputSource;
/*  17:    */
/*  47:    */public class JAXBReader
/*  48:    */  extends JAXBSupport
/*  49:    */{
/*  50:    */  private SAXReader reader;
/*  51:    */  private boolean pruneElements;
/*  52:    */  
/*  53:    */  public JAXBReader(String contextPath)
/*  54:    */  {
/*  55: 55 */    super(contextPath);
/*  56:    */  }
/*  57:    */  
/*  70:    */  public JAXBReader(String contextPath, ClassLoader classloader)
/*  71:    */  {
/*  72: 72 */    super(contextPath, classloader);
/*  73:    */  }
/*  74:    */  
/*  84:    */  public Document read(File source)
/*  85:    */    throws DocumentException
/*  86:    */  {
/*  87: 87 */    return getReader().read(source);
/*  88:    */  }
/*  89:    */  
/* 101:    */  public Document read(File file, Charset charset)
/* 102:    */    throws DocumentException
/* 103:    */  {
/* 104:    */    try
/* 105:    */    {
/* 106:106 */      Reader xmlReader = new InputStreamReader(new FileInputStream(file), charset);
/* 107:    */      
/* 109:109 */      return getReader().read(xmlReader);
/* 110:    */    } catch (JAXBRuntimeException ex) {
/* 111:111 */      Throwable cause = ex.getCause();
/* 112:112 */      throw new DocumentException(cause.getMessage(), cause);
/* 113:    */    } catch (FileNotFoundException ex) {
/* 114:114 */      throw new DocumentException(ex.getMessage(), ex);
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 126:    */  public Document read(InputSource source)
/* 127:    */    throws DocumentException
/* 128:    */  {
/* 129:    */    try
/* 130:    */    {
/* 131:131 */      return getReader().read(source);
/* 132:    */    } catch (JAXBRuntimeException ex) {
/* 133:133 */      Throwable cause = ex.getCause();
/* 134:134 */      throw new DocumentException(cause.getMessage(), cause);
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 146:    */  public Document read(InputStream source)
/* 147:    */    throws DocumentException
/* 148:    */  {
/* 149:    */    try
/* 150:    */    {
/* 151:151 */      return getReader().read(source);
/* 152:    */    } catch (JAXBRuntimeException ex) {
/* 153:153 */      Throwable cause = ex.getCause();
/* 154:154 */      throw new DocumentException(cause.getMessage(), cause);
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 169:    */  public Document read(InputStream source, String systemId)
/* 170:    */    throws DocumentException
/* 171:    */  {
/* 172:    */    try
/* 173:    */    {
/* 174:174 */      return getReader().read(source);
/* 175:    */    } catch (JAXBRuntimeException ex) {
/* 176:176 */      Throwable cause = ex.getCause();
/* 177:177 */      throw new DocumentException(cause.getMessage(), cause);
/* 178:    */    }
/* 179:    */  }
/* 180:    */  
/* 189:    */  public Document read(Reader source)
/* 190:    */    throws DocumentException
/* 191:    */  {
/* 192:    */    try
/* 193:    */    {
/* 194:194 */      return getReader().read(source);
/* 195:    */    } catch (JAXBRuntimeException ex) {
/* 196:196 */      Throwable cause = ex.getCause();
/* 197:197 */      throw new DocumentException(cause.getMessage(), cause);
/* 198:    */    }
/* 199:    */  }
/* 200:    */  
/* 212:    */  public Document read(Reader source, String systemId)
/* 213:    */    throws DocumentException
/* 214:    */  {
/* 215:    */    try
/* 216:    */    {
/* 217:217 */      return getReader().read(source);
/* 218:    */    } catch (JAXBRuntimeException ex) {
/* 219:219 */      Throwable cause = ex.getCause();
/* 220:220 */      throw new DocumentException(cause.getMessage(), cause);
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 232:    */  public Document read(String source)
/* 233:    */    throws DocumentException
/* 234:    */  {
/* 235:    */    try
/* 236:    */    {
/* 237:237 */      return getReader().read(source);
/* 238:    */    } catch (JAXBRuntimeException ex) {
/* 239:239 */      Throwable cause = ex.getCause();
/* 240:240 */      throw new DocumentException(cause.getMessage(), cause);
/* 241:    */    }
/* 242:    */  }
/* 243:    */  
/* 252:    */  public Document read(URL source)
/* 253:    */    throws DocumentException
/* 254:    */  {
/* 255:    */    try
/* 256:    */    {
/* 257:257 */      return getReader().read(source);
/* 258:    */    } catch (JAXBRuntimeException ex) {
/* 259:259 */      Throwable cause = ex.getCause();
/* 260:260 */      throw new DocumentException(cause.getMessage(), cause);
/* 261:    */    }
/* 262:    */  }
/* 263:    */  
/* 273:    */  public void addObjectHandler(String path, JAXBObjectHandler handler)
/* 274:    */  {
/* 275:275 */    ElementHandler eHandler = new UnmarshalElementHandler(this, handler);
/* 276:276 */    getReader().addHandler(path, eHandler);
/* 277:    */  }
/* 278:    */  
/* 285:    */  public void removeObjectHandler(String path)
/* 286:    */  {
/* 287:287 */    getReader().removeHandler(path);
/* 288:    */  }
/* 289:    */  
/* 299:    */  public void addHandler(String path, ElementHandler handler)
/* 300:    */  {
/* 301:301 */    getReader().addHandler(path, handler);
/* 302:    */  }
/* 303:    */  
/* 310:    */  public void removeHandler(String path)
/* 311:    */  {
/* 312:312 */    getReader().removeHandler(path);
/* 313:    */  }
/* 314:    */  
/* 318:    */  public void resetHandlers()
/* 319:    */  {
/* 320:320 */    getReader().resetHandlers();
/* 321:    */  }
/* 322:    */  
/* 327:    */  public boolean isPruneElements()
/* 328:    */  {
/* 329:329 */    return this.pruneElements;
/* 330:    */  }
/* 331:    */  
/* 337:    */  public void setPruneElements(boolean pruneElements)
/* 338:    */  {
/* 339:339 */    this.pruneElements = pruneElements;
/* 340:    */    
/* 341:341 */    if (pruneElements) {
/* 342:342 */      getReader().setDefaultHandler(new PruningElementHandler());
/* 343:    */    }
/* 344:    */  }
/* 345:    */  
/* 346:    */  private SAXReader getReader() {
/* 347:347 */    if (this.reader == null) {
/* 348:348 */      this.reader = new SAXReader();
/* 349:    */    }
/* 350:    */    
/* 351:351 */    return this.reader;
/* 352:    */  }
/* 353:    */  
/* 354:    */  private class UnmarshalElementHandler implements ElementHandler
/* 355:    */  {
/* 356:    */    private JAXBReader jaxbReader;
/* 357:    */    private JAXBObjectHandler handler;
/* 358:    */    
/* 359:    */    public UnmarshalElementHandler(JAXBReader documentReader, JAXBObjectHandler handler)
/* 360:    */    {
/* 361:361 */      this.jaxbReader = documentReader;
/* 362:362 */      this.handler = handler;
/* 363:    */    }
/* 364:    */    
/* 365:    */    public void onStart(ElementPath elementPath) {}
/* 366:    */    
/* 367:    */    public void onEnd(ElementPath elementPath)
/* 368:    */    {
/* 369:    */      try {
/* 370:370 */        org.dom4j.Element elem = elementPath.getCurrent();
/* 371:    */        
/* 372:372 */        javax.xml.bind.Element jaxbObject = this.jaxbReader.unmarshal(elem);
/* 373:    */        
/* 375:375 */        if (this.jaxbReader.isPruneElements()) {
/* 376:376 */          elem.detach();
/* 377:    */        }
/* 378:    */        
/* 379:379 */        this.handler.handleObject(jaxbObject);
/* 380:    */      } catch (Exception ex) {
/* 381:381 */        throw new JAXBRuntimeException(ex);
/* 382:    */      }
/* 383:    */    }
/* 384:    */  }
/* 385:    */  
/* 386:    */  private class PruningElementHandler implements ElementHandler
/* 387:    */  {
/* 388:    */    public PruningElementHandler() {}
/* 389:    */    
/* 390:    */    public void onStart(ElementPath parm1) {}
/* 391:    */    
/* 392:    */    public void onEnd(ElementPath elementPath)
/* 393:    */    {
/* 394:394 */      org.dom4j.Element elem = elementPath.getCurrent();
/* 395:395 */      elem.detach();
/* 396:396 */      elem = null;
/* 397:    */    }
/* 398:    */  }
/* 399:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */