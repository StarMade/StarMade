/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.Externalizable;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.ObjectInput;
/*   6:    */import java.io.ObjectOutput;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.List;
/*  11:    */import java.util.Map;
/*  12:    */import org.dom4j.Namespace;
/*  13:    */import org.dom4j.QName;
/*  14:    */import org.xml.sax.Attributes;
/*  15:    */import org.xml.sax.ContentHandler;
/*  16:    */import org.xml.sax.DTDHandler;
/*  17:    */import org.xml.sax.SAXException;
/*  18:    */import org.xml.sax.ext.DeclHandler;
/*  19:    */import org.xml.sax.ext.LexicalHandler;
/*  20:    */import org.xml.sax.helpers.AttributesImpl;
/*  21:    */import org.xml.sax.helpers.DefaultHandler;
/*  22:    */
/*  62:    */public class SAXEventRecorder
/*  63:    */  extends DefaultHandler
/*  64:    */  implements LexicalHandler, DeclHandler, DTDHandler, Externalizable
/*  65:    */{
/*  66:    */  public static final long serialVersionUID = 1L;
/*  67:    */  private static final byte STRING = 0;
/*  68:    */  private static final byte OBJECT = 1;
/*  69:    */  private static final byte NULL = 2;
/*  70: 70 */  private List events = new ArrayList();
/*  71:    */  
/*  72: 72 */  private Map prefixMappings = new HashMap();
/*  73:    */  
/*  75:    */  private static final String XMLNS = "xmlns";
/*  76:    */  
/*  77:    */  private static final String EMPTY_STRING = "";
/*  78:    */  
/*  80:    */  public void replay(ContentHandler handler)
/*  81:    */    throws SAXException
/*  82:    */  {
/*  83: 83 */    Iterator itr = this.events.iterator();
/*  84:    */    
/*  85: 85 */    while (itr.hasNext()) {
/*  86: 86 */      SAXEvent saxEvent = (SAXEvent)itr.next();
/*  87:    */      
/*  88: 88 */      switch (saxEvent.event)
/*  89:    */      {
/*  90:    */      case 1: 
/*  91: 91 */        handler.processingInstruction((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/*  92:    */        
/*  94: 94 */        break;
/*  95:    */      
/*  96:    */      case 2: 
/*  97: 97 */        handler.startPrefixMapping((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/*  98:    */        
/* 100:100 */        break;
/* 101:    */      
/* 102:    */      case 3: 
/* 103:103 */        handler.endPrefixMapping((String)saxEvent.getParm(0));
/* 104:    */        
/* 105:105 */        break;
/* 106:    */      
/* 107:    */      case 4: 
/* 108:108 */        handler.startDocument();
/* 109:    */        
/* 110:110 */        break;
/* 111:    */      
/* 112:    */      case 5: 
/* 113:113 */        handler.endDocument();
/* 114:    */        
/* 115:115 */        break;
/* 116:    */      
/* 118:    */      case 6: 
/* 119:119 */        AttributesImpl attributes = new AttributesImpl();
/* 120:120 */        List attParmList = (List)saxEvent.getParm(3);
/* 121:    */        
/* 122:122 */        if (attParmList != null) {
/* 123:123 */          Iterator attsItr = attParmList.iterator();
/* 124:    */          
/* 125:125 */          while (attsItr.hasNext()) {
/* 126:126 */            String[] attParms = (String[])attsItr.next();
/* 127:127 */            attributes.addAttribute(attParms[0], attParms[1], attParms[2], attParms[3], attParms[4]);
/* 128:    */          }
/* 129:    */        }
/* 130:    */        
/* 132:132 */        handler.startElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), attributes);
/* 133:    */        
/* 136:136 */        break;
/* 137:    */      
/* 138:    */      case 7: 
/* 139:139 */        handler.endElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
/* 140:    */        
/* 143:143 */        break;
/* 144:    */      
/* 146:    */      case 8: 
/* 147:147 */        char[] chars = (char[])saxEvent.getParm(0);
/* 148:148 */        int start = ((Integer)saxEvent.getParm(1)).intValue();
/* 149:149 */        int end = ((Integer)saxEvent.getParm(2)).intValue();
/* 150:150 */        handler.characters(chars, start, end);
/* 151:    */        
/* 152:152 */        break;
/* 153:    */      
/* 155:    */      case 9: 
/* 156:156 */        ((LexicalHandler)handler).startDTD((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
/* 157:    */        
/* 160:160 */        break;
/* 161:    */      
/* 162:    */      case 10: 
/* 163:163 */        ((LexicalHandler)handler).endDTD();
/* 164:    */        
/* 165:165 */        break;
/* 166:    */      
/* 167:    */      case 11: 
/* 168:168 */        ((LexicalHandler)handler).startEntity((String)saxEvent.getParm(0));
/* 169:    */        
/* 171:171 */        break;
/* 172:    */      
/* 173:    */      case 12: 
/* 174:174 */        ((LexicalHandler)handler).endEntity((String)saxEvent.getParm(0));
/* 175:    */        
/* 177:177 */        break;
/* 178:    */      
/* 179:    */      case 13: 
/* 180:180 */        ((LexicalHandler)handler).startCDATA();
/* 181:    */        
/* 182:182 */        break;
/* 183:    */      
/* 184:    */      case 14: 
/* 185:185 */        ((LexicalHandler)handler).endCDATA();
/* 186:    */        
/* 187:187 */        break;
/* 188:    */      
/* 190:    */      case 15: 
/* 191:191 */        char[] cchars = (char[])saxEvent.getParm(0);
/* 192:192 */        int cstart = ((Integer)saxEvent.getParm(1)).intValue();
/* 193:193 */        int cend = ((Integer)saxEvent.getParm(2)).intValue();
/* 194:194 */        ((LexicalHandler)handler).comment(cchars, cstart, cend);
/* 195:    */        
/* 196:196 */        break;
/* 197:    */      
/* 199:    */      case 16: 
/* 200:200 */        ((DeclHandler)handler).elementDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/* 201:    */        
/* 203:203 */        break;
/* 204:    */      
/* 205:    */      case 17: 
/* 206:206 */        ((DeclHandler)handler).attributeDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), (String)saxEvent.getParm(3), (String)saxEvent.getParm(4));
/* 207:    */        
/* 211:211 */        break;
/* 212:    */      
/* 213:    */      case 18: 
/* 214:214 */        ((DeclHandler)handler).internalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/* 215:    */        
/* 218:218 */        break;
/* 219:    */      
/* 220:    */      case 19: 
/* 221:221 */        ((DeclHandler)handler).externalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
/* 222:    */        
/* 225:225 */        break;
/* 226:    */      
/* 227:    */      default: 
/* 228:228 */        throw new SAXException("Unrecognized event: " + saxEvent.event);
/* 229:    */      }
/* 230:    */      
/* 231:    */    }
/* 232:    */  }
/* 233:    */  
/* 235:    */  public void processingInstruction(String target, String data)
/* 236:    */    throws SAXException
/* 237:    */  {
/* 238:238 */    SAXEvent saxEvent = new SAXEvent((byte)1);
/* 239:239 */    saxEvent.addParm(target);
/* 240:240 */    saxEvent.addParm(data);
/* 241:241 */    this.events.add(saxEvent);
/* 242:    */  }
/* 243:    */  
/* 244:    */  public void startPrefixMapping(String prefix, String uri) throws SAXException
/* 245:    */  {
/* 246:246 */    SAXEvent saxEvent = new SAXEvent((byte)2);
/* 247:247 */    saxEvent.addParm(prefix);
/* 248:248 */    saxEvent.addParm(uri);
/* 249:249 */    this.events.add(saxEvent);
/* 250:    */  }
/* 251:    */  
/* 252:    */  public void endPrefixMapping(String prefix) throws SAXException {
/* 253:253 */    SAXEvent saxEvent = new SAXEvent((byte)3);
/* 254:254 */    saxEvent.addParm(prefix);
/* 255:255 */    this.events.add(saxEvent);
/* 256:    */  }
/* 257:    */  
/* 258:    */  public void startDocument() throws SAXException {
/* 259:259 */    SAXEvent saxEvent = new SAXEvent((byte)4);
/* 260:260 */    this.events.add(saxEvent);
/* 261:    */  }
/* 262:    */  
/* 263:    */  public void endDocument() throws SAXException {
/* 264:264 */    SAXEvent saxEvent = new SAXEvent((byte)5);
/* 265:265 */    this.events.add(saxEvent);
/* 266:    */  }
/* 267:    */  
/* 268:    */  public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException
/* 269:    */  {
/* 270:270 */    SAXEvent saxEvent = new SAXEvent((byte)6);
/* 271:271 */    saxEvent.addParm(namespaceURI);
/* 272:272 */    saxEvent.addParm(localName);
/* 273:273 */    saxEvent.addParm(qualifiedName);
/* 274:    */    
/* 275:275 */    QName qName = null;
/* 276:276 */    if (namespaceURI != null) {
/* 277:277 */      qName = new QName(localName, Namespace.get(namespaceURI));
/* 278:    */    } else {
/* 279:279 */      qName = new QName(localName);
/* 280:    */    }
/* 281:    */    
/* 282:282 */    if ((attributes != null) && (attributes.getLength() > 0)) {
/* 283:283 */      List attParmList = new ArrayList(attributes.getLength());
/* 284:284 */      String[] attParms = null;
/* 285:    */      
/* 286:286 */      for (int i = 0; i < attributes.getLength(); i++)
/* 287:    */      {
/* 288:288 */        String attLocalName = attributes.getLocalName(i);
/* 289:    */        
/* 290:290 */        if (attLocalName.startsWith("xmlns"))
/* 291:    */        {
/* 295:295 */          String prefix = null;
/* 296:296 */          if (attLocalName.length() > 5) {
/* 297:297 */            prefix = attLocalName.substring(6);
/* 298:    */          } else {
/* 299:299 */            prefix = "";
/* 300:    */          }
/* 301:    */          
/* 302:302 */          SAXEvent prefixEvent = new SAXEvent((byte)2);
/* 303:    */          
/* 304:304 */          prefixEvent.addParm(prefix);
/* 305:305 */          prefixEvent.addParm(attributes.getValue(i));
/* 306:306 */          this.events.add(prefixEvent);
/* 307:    */          
/* 310:310 */          List prefixes = (List)this.prefixMappings.get(qName);
/* 311:311 */          if (prefixes == null) {
/* 312:312 */            prefixes = new ArrayList();
/* 313:313 */            this.prefixMappings.put(qName, prefixes);
/* 314:    */          }
/* 315:315 */          prefixes.add(prefix);
/* 316:    */        }
/* 317:    */        else
/* 318:    */        {
/* 319:319 */          attParms = new String[5];
/* 320:320 */          attParms[0] = attributes.getURI(i);
/* 321:321 */          attParms[1] = attLocalName;
/* 322:322 */          attParms[2] = attributes.getQName(i);
/* 323:323 */          attParms[3] = attributes.getType(i);
/* 324:324 */          attParms[4] = attributes.getValue(i);
/* 325:325 */          attParmList.add(attParms);
/* 326:    */        }
/* 327:    */      }
/* 328:    */      
/* 331:331 */      saxEvent.addParm(attParmList);
/* 332:    */    }
/* 333:    */    
/* 334:334 */    this.events.add(saxEvent);
/* 335:    */  }
/* 336:    */  
/* 337:    */  public void endElement(String namespaceURI, String localName, String qName)
/* 338:    */    throws SAXException
/* 339:    */  {
/* 340:340 */    SAXEvent saxEvent = new SAXEvent((byte)7);
/* 341:341 */    saxEvent.addParm(namespaceURI);
/* 342:342 */    saxEvent.addParm(localName);
/* 343:343 */    saxEvent.addParm(qName);
/* 344:344 */    this.events.add(saxEvent);
/* 345:    */    
/* 349:349 */    QName elementName = null;
/* 350:350 */    if (namespaceURI != null) {
/* 351:351 */      elementName = new QName(localName, Namespace.get(namespaceURI));
/* 352:    */    } else {
/* 353:353 */      elementName = new QName(localName);
/* 354:    */    }
/* 355:    */    
/* 356:356 */    List prefixes = (List)this.prefixMappings.get(elementName);
/* 357:357 */    if (prefixes != null) {
/* 358:358 */      Iterator itr = prefixes.iterator();
/* 359:359 */      while (itr.hasNext()) {
/* 360:360 */        SAXEvent prefixEvent = new SAXEvent((byte)3);
/* 361:    */        
/* 362:362 */        prefixEvent.addParm(itr.next());
/* 363:363 */        this.events.add(prefixEvent);
/* 364:    */      }
/* 365:    */    }
/* 366:    */  }
/* 367:    */  
/* 368:    */  public void characters(char[] ch, int start, int end) throws SAXException
/* 369:    */  {
/* 370:370 */    SAXEvent saxEvent = new SAXEvent((byte)8);
/* 371:371 */    saxEvent.addParm(ch);
/* 372:372 */    saxEvent.addParm(new Integer(start));
/* 373:373 */    saxEvent.addParm(new Integer(end));
/* 374:374 */    this.events.add(saxEvent);
/* 375:    */  }
/* 376:    */  
/* 378:    */  public void startDTD(String name, String publicId, String systemId)
/* 379:    */    throws SAXException
/* 380:    */  {
/* 381:381 */    SAXEvent saxEvent = new SAXEvent((byte)9);
/* 382:382 */    saxEvent.addParm(name);
/* 383:383 */    saxEvent.addParm(publicId);
/* 384:384 */    saxEvent.addParm(systemId);
/* 385:385 */    this.events.add(saxEvent);
/* 386:    */  }
/* 387:    */  
/* 388:    */  public void endDTD() throws SAXException {
/* 389:389 */    SAXEvent saxEvent = new SAXEvent((byte)10);
/* 390:390 */    this.events.add(saxEvent);
/* 391:    */  }
/* 392:    */  
/* 393:    */  public void startEntity(String name) throws SAXException {
/* 394:394 */    SAXEvent saxEvent = new SAXEvent((byte)11);
/* 395:395 */    saxEvent.addParm(name);
/* 396:396 */    this.events.add(saxEvent);
/* 397:    */  }
/* 398:    */  
/* 399:    */  public void endEntity(String name) throws SAXException {
/* 400:400 */    SAXEvent saxEvent = new SAXEvent((byte)12);
/* 401:401 */    saxEvent.addParm(name);
/* 402:402 */    this.events.add(saxEvent);
/* 403:    */  }
/* 404:    */  
/* 405:    */  public void startCDATA() throws SAXException {
/* 406:406 */    SAXEvent saxEvent = new SAXEvent((byte)13);
/* 407:407 */    this.events.add(saxEvent);
/* 408:    */  }
/* 409:    */  
/* 410:    */  public void endCDATA() throws SAXException {
/* 411:411 */    SAXEvent saxEvent = new SAXEvent((byte)14);
/* 412:412 */    this.events.add(saxEvent);
/* 413:    */  }
/* 414:    */  
/* 415:    */  public void comment(char[] ch, int start, int end) throws SAXException {
/* 416:416 */    SAXEvent saxEvent = new SAXEvent((byte)15);
/* 417:417 */    saxEvent.addParm(ch);
/* 418:418 */    saxEvent.addParm(new Integer(start));
/* 419:419 */    saxEvent.addParm(new Integer(end));
/* 420:420 */    this.events.add(saxEvent);
/* 421:    */  }
/* 422:    */  
/* 423:    */  public void elementDecl(String name, String model)
/* 424:    */    throws SAXException
/* 425:    */  {
/* 426:426 */    SAXEvent saxEvent = new SAXEvent((byte)16);
/* 427:427 */    saxEvent.addParm(name);
/* 428:428 */    saxEvent.addParm(model);
/* 429:429 */    this.events.add(saxEvent);
/* 430:    */  }
/* 431:    */  
/* 432:    */  public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException
/* 433:    */  {
/* 434:434 */    SAXEvent saxEvent = new SAXEvent((byte)17);
/* 435:435 */    saxEvent.addParm(eName);
/* 436:436 */    saxEvent.addParm(aName);
/* 437:437 */    saxEvent.addParm(type);
/* 438:438 */    saxEvent.addParm(valueDefault);
/* 439:439 */    saxEvent.addParm(value);
/* 440:440 */    this.events.add(saxEvent);
/* 441:    */  }
/* 442:    */  
/* 443:    */  public void internalEntityDecl(String name, String value) throws SAXException
/* 444:    */  {
/* 445:445 */    SAXEvent saxEvent = new SAXEvent((byte)18);
/* 446:446 */    saxEvent.addParm(name);
/* 447:447 */    saxEvent.addParm(value);
/* 448:448 */    this.events.add(saxEvent);
/* 449:    */  }
/* 450:    */  
/* 451:    */  public void externalEntityDecl(String name, String publicId, String sysId) throws SAXException
/* 452:    */  {
/* 453:453 */    SAXEvent saxEvent = new SAXEvent((byte)19);
/* 454:454 */    saxEvent.addParm(name);
/* 455:455 */    saxEvent.addParm(publicId);
/* 456:456 */    saxEvent.addParm(sysId);
/* 457:457 */    this.events.add(saxEvent);
/* 458:    */  }
/* 459:    */  
/* 460:    */  public void writeExternal(ObjectOutput out) throws IOException {
/* 461:461 */    if (this.events == null) {
/* 462:462 */      out.writeByte(2);
/* 463:    */    } else {
/* 464:464 */      out.writeByte(1);
/* 465:465 */      out.writeObject(this.events);
/* 466:    */    }
/* 467:    */  }
/* 468:    */  
/* 469:    */  public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException
/* 470:    */  {
/* 471:471 */    if (in.readByte() != 2) {
/* 472:472 */      this.events = ((List)in.readObject());
/* 473:    */    }
/* 474:    */  }
/* 475:    */  
/* 477:    */  static class SAXEvent
/* 478:    */    implements Externalizable
/* 479:    */  {
/* 480:    */    public static final long serialVersionUID = 1L;
/* 481:    */    
/* 482:    */    static final byte PROCESSING_INSTRUCTION = 1;
/* 483:    */    
/* 484:    */    static final byte START_PREFIX_MAPPING = 2;
/* 485:    */    
/* 486:    */    static final byte END_PREFIX_MAPPING = 3;
/* 487:    */    
/* 488:    */    static final byte START_DOCUMENT = 4;
/* 489:    */    
/* 490:    */    static final byte END_DOCUMENT = 5;
/* 491:    */    
/* 492:    */    static final byte START_ELEMENT = 6;
/* 493:    */    
/* 494:    */    static final byte END_ELEMENT = 7;
/* 495:    */    
/* 496:    */    static final byte CHARACTERS = 8;
/* 497:    */    
/* 498:    */    static final byte START_DTD = 9;
/* 499:    */    
/* 500:    */    static final byte END_DTD = 10;
/* 501:    */    
/* 502:    */    static final byte START_ENTITY = 11;
/* 503:    */    
/* 504:    */    static final byte END_ENTITY = 12;
/* 505:    */    
/* 506:    */    static final byte START_CDATA = 13;
/* 507:    */    
/* 508:    */    static final byte END_CDATA = 14;
/* 509:    */    
/* 510:    */    static final byte COMMENT = 15;
/* 511:    */    
/* 512:    */    static final byte ELEMENT_DECL = 16;
/* 513:    */    
/* 514:    */    static final byte ATTRIBUTE_DECL = 17;
/* 515:    */    
/* 516:    */    static final byte INTERNAL_ENTITY_DECL = 18;
/* 517:    */    
/* 518:    */    static final byte EXTERNAL_ENTITY_DECL = 19;
/* 519:    */    
/* 520:    */    protected byte event;
/* 521:    */    protected List parms;
/* 522:    */    
/* 523:    */    public SAXEvent() {}
/* 524:    */    
/* 525:    */    SAXEvent(byte event)
/* 526:    */    {
/* 527:527 */      this.event = event;
/* 528:    */    }
/* 529:    */    
/* 530:    */    void addParm(Object parm) {
/* 531:531 */      if (this.parms == null) {
/* 532:532 */        this.parms = new ArrayList(3);
/* 533:    */      }
/* 534:    */      
/* 535:535 */      this.parms.add(parm);
/* 536:    */    }
/* 537:    */    
/* 538:    */    Object getParm(int index) {
/* 539:539 */      if ((this.parms != null) && (index < this.parms.size())) {
/* 540:540 */        return this.parms.get(index);
/* 541:    */      }
/* 542:542 */      return null;
/* 543:    */    }
/* 544:    */    
/* 545:    */    public void writeExternal(ObjectOutput out) throws IOException
/* 546:    */    {
/* 547:547 */      out.writeByte(this.event);
/* 548:    */      
/* 549:549 */      if (this.parms == null) {
/* 550:550 */        out.writeByte(2);
/* 551:    */      } else {
/* 552:552 */        out.writeByte(1);
/* 553:553 */        out.writeObject(this.parms);
/* 554:    */      }
/* 555:    */    }
/* 556:    */    
/* 557:    */    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException
/* 558:    */    {
/* 559:559 */      this.event = in.readByte();
/* 560:    */      
/* 561:561 */      if (in.readByte() != 2) {
/* 562:562 */        this.parms = ((List)in.readObject());
/* 563:    */      }
/* 564:    */    }
/* 565:    */  }
/* 566:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXEventRecorder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */