/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.io.Reader;
/*   5:    */import java.util.Iterator;
/*   6:    */import javax.xml.stream.XMLEventReader;
/*   7:    */import javax.xml.stream.XMLInputFactory;
/*   8:    */import javax.xml.stream.XMLStreamException;
/*   9:    */import javax.xml.stream.events.Characters;
/*  10:    */import javax.xml.stream.events.EndElement;
/*  11:    */import javax.xml.stream.events.EntityDeclaration;
/*  12:    */import javax.xml.stream.events.EntityReference;
/*  13:    */import javax.xml.stream.events.StartDocument;
/*  14:    */import javax.xml.stream.events.StartElement;
/*  15:    */import javax.xml.stream.events.XMLEvent;
/*  16:    */import org.dom4j.CharacterData;
/*  17:    */import org.dom4j.Document;
/*  18:    */import org.dom4j.DocumentFactory;
/*  19:    */import org.dom4j.Element;
/*  20:    */import org.dom4j.Entity;
/*  21:    */import org.dom4j.Node;
/*  22:    */
/*  45:    */public class STAXEventReader
/*  46:    */{
/*  47:    */  private DocumentFactory factory;
/*  48: 48 */  private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
/*  49:    */  
/*  53:    */  public STAXEventReader()
/*  54:    */  {
/*  55: 55 */    this.factory = DocumentFactory.getInstance();
/*  56:    */  }
/*  57:    */  
/*  65:    */  public STAXEventReader(DocumentFactory factory)
/*  66:    */  {
/*  67: 67 */    if (factory != null) {
/*  68: 68 */      this.factory = factory;
/*  69:    */    } else {
/*  70: 70 */      this.factory = DocumentFactory.getInstance();
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  80:    */  public void setDocumentFactory(DocumentFactory documentFactory)
/*  81:    */  {
/*  82: 82 */    if (documentFactory != null) {
/*  83: 83 */      this.factory = documentFactory;
/*  84:    */    } else {
/*  85: 85 */      this.factory = DocumentFactory.getInstance();
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  99:    */  public Document readDocument(InputStream is)
/* 100:    */    throws XMLStreamException
/* 101:    */  {
/* 102:102 */    return readDocument(is, null);
/* 103:    */  }
/* 104:    */  
/* 115:    */  public Document readDocument(Reader reader)
/* 116:    */    throws XMLStreamException
/* 117:    */  {
/* 118:118 */    return readDocument(reader, null);
/* 119:    */  }
/* 120:    */  
/* 134:    */  public Document readDocument(InputStream is, String systemId)
/* 135:    */    throws XMLStreamException
/* 136:    */  {
/* 137:137 */    XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, is);
/* 138:    */    
/* 139:    */    try
/* 140:    */    {
/* 141:141 */      return readDocument(eventReader);
/* 142:    */    } finally {
/* 143:143 */      eventReader.close();
/* 144:    */    }
/* 145:    */  }
/* 146:    */  
/* 160:    */  public Document readDocument(Reader reader, String systemId)
/* 161:    */    throws XMLStreamException
/* 162:    */  {
/* 163:163 */    XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, reader);
/* 164:    */    
/* 165:    */    try
/* 166:    */    {
/* 167:167 */      return readDocument(eventReader);
/* 168:    */    } finally {
/* 169:169 */      eventReader.close();
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 192:    */  public Node readNode(XMLEventReader reader)
/* 193:    */    throws XMLStreamException
/* 194:    */  {
/* 195:195 */    XMLEvent event = reader.peek();
/* 196:    */    
/* 197:197 */    if (event.isStartElement())
/* 198:198 */      return readElement(reader);
/* 199:199 */    if (event.isCharacters())
/* 200:200 */      return readCharacters(reader);
/* 201:201 */    if (event.isStartDocument())
/* 202:202 */      return readDocument(reader);
/* 203:203 */    if (event.isProcessingInstruction())
/* 204:204 */      return readProcessingInstruction(reader);
/* 205:205 */    if (event.isEntityReference())
/* 206:206 */      return readEntityReference(reader);
/* 207:207 */    if (event.isAttribute())
/* 208:208 */      return readAttribute(reader);
/* 209:209 */    if (event.isNamespace()) {
/* 210:210 */      return readNamespace(reader);
/* 211:    */    }
/* 212:212 */    throw new XMLStreamException("Unsupported event: " + event);
/* 213:    */  }
/* 214:    */  
/* 228:    */  public Document readDocument(XMLEventReader reader)
/* 229:    */    throws XMLStreamException
/* 230:    */  {
/* 231:231 */    Document doc = null;
/* 232:    */    
/* 233:233 */    while (reader.hasNext()) {
/* 234:234 */      XMLEvent nextEvent = reader.peek();
/* 235:235 */      int type = nextEvent.getEventType();
/* 236:    */      
/* 237:237 */      switch (type)
/* 238:    */      {
/* 239:    */      case 7: 
/* 240:240 */        StartDocument event = (StartDocument)reader.nextEvent();
/* 241:    */        
/* 242:242 */        if (doc == null)
/* 243:    */        {
/* 244:244 */          if (event.encodingSet()) {
/* 245:245 */            String encodingScheme = event.getCharacterEncodingScheme();
/* 246:    */            
/* 247:247 */            doc = this.factory.createDocument(encodingScheme);
/* 248:    */          } else {
/* 249:249 */            doc = this.factory.createDocument();
/* 250:    */          }
/* 251:    */        }
/* 252:    */        else {
/* 253:253 */          String msg = "Unexpected StartDocument event";
/* 254:254 */          throw new XMLStreamException(msg, event.getLocation());
/* 255:    */        }
/* 256:    */        
/* 260:    */        break;
/* 261:    */      case 4: 
/* 262:    */      case 6: 
/* 263:    */      case 8: 
/* 264:264 */        reader.nextEvent();
/* 265:    */        
/* 266:266 */        break;
/* 267:    */      
/* 268:    */      case 5: 
/* 269:    */      default: 
/* 270:270 */        if (doc == null)
/* 271:    */        {
/* 272:272 */          doc = this.factory.createDocument();
/* 273:    */        }
/* 274:    */        
/* 275:275 */        Node n = readNode(reader);
/* 276:276 */        doc.add(n);
/* 277:    */      }
/* 278:    */      
/* 279:    */    }
/* 280:280 */    return doc;
/* 281:    */  }
/* 282:    */  
/* 297:    */  public Element readElement(XMLEventReader eventReader)
/* 298:    */    throws XMLStreamException
/* 299:    */  {
/* 300:300 */    XMLEvent event = eventReader.peek();
/* 301:    */    
/* 302:302 */    if (event.isStartElement())
/* 303:    */    {
/* 304:304 */      StartElement startTag = eventReader.nextEvent().asStartElement();
/* 305:305 */      Element elem = createElement(startTag);
/* 306:    */      
/* 307:    */      for (;;)
/* 308:    */      {
/* 309:309 */        if (!eventReader.hasNext()) {
/* 310:310 */          String msg = "Unexpected end of stream while reading element content";
/* 311:    */          
/* 312:312 */          throw new XMLStreamException(msg);
/* 313:    */        }
/* 314:    */        
/* 315:315 */        XMLEvent nextEvent = eventReader.peek();
/* 316:    */        
/* 317:317 */        if (nextEvent.isEndElement()) {
/* 318:318 */          EndElement endElem = eventReader.nextEvent().asEndElement();
/* 319:    */          
/* 320:320 */          if (endElem.getName().equals(startTag.getName())) break;
/* 321:321 */          throw new XMLStreamException("Expected " + startTag.getName() + " end-tag, but found" + endElem.getName());
/* 322:    */        }
/* 323:    */        
/* 329:329 */        Node child = readNode(eventReader);
/* 330:330 */        elem.add(child);
/* 331:    */      }
/* 332:    */      
/* 333:333 */      return elem;
/* 334:    */    }
/* 335:335 */    throw new XMLStreamException("Expected Element event, found: " + event);
/* 336:    */  }
/* 337:    */  
/* 352:    */  public org.dom4j.Attribute readAttribute(XMLEventReader reader)
/* 353:    */    throws XMLStreamException
/* 354:    */  {
/* 355:355 */    XMLEvent event = reader.peek();
/* 356:    */    
/* 357:357 */    if (event.isAttribute()) {
/* 358:358 */      javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)reader.nextEvent();
/* 359:    */      
/* 360:360 */      return createAttribute(null, attr);
/* 361:    */    }
/* 362:362 */    throw new XMLStreamException("Expected Attribute event, found: " + event);
/* 363:    */  }
/* 364:    */  
/* 379:    */  public org.dom4j.Namespace readNamespace(XMLEventReader reader)
/* 380:    */    throws XMLStreamException
/* 381:    */  {
/* 382:382 */    XMLEvent event = reader.peek();
/* 383:    */    
/* 384:384 */    if (event.isNamespace()) {
/* 385:385 */      javax.xml.stream.events.Namespace ns = (javax.xml.stream.events.Namespace)reader.nextEvent();
/* 386:    */      
/* 387:387 */      return createNamespace(ns);
/* 388:    */    }
/* 389:389 */    throw new XMLStreamException("Expected Namespace event, found: " + event);
/* 390:    */  }
/* 391:    */  
/* 406:    */  public CharacterData readCharacters(XMLEventReader reader)
/* 407:    */    throws XMLStreamException
/* 408:    */  {
/* 409:409 */    XMLEvent event = reader.peek();
/* 410:    */    
/* 411:411 */    if (event.isCharacters()) {
/* 412:412 */      Characters characters = reader.nextEvent().asCharacters();
/* 413:    */      
/* 414:414 */      return createCharacterData(characters);
/* 415:    */    }
/* 416:416 */    throw new XMLStreamException("Expected Characters event, found: " + event);
/* 417:    */  }
/* 418:    */  
/* 433:    */  public org.dom4j.Comment readComment(XMLEventReader reader)
/* 434:    */    throws XMLStreamException
/* 435:    */  {
/* 436:436 */    XMLEvent event = reader.peek();
/* 437:    */    
/* 438:438 */    if ((event instanceof javax.xml.stream.events.Comment)) {
/* 439:439 */      return createComment((javax.xml.stream.events.Comment)reader.nextEvent());
/* 440:    */    }
/* 441:441 */    throw new XMLStreamException("Expected Comment event, found: " + event);
/* 442:    */  }
/* 443:    */  
/* 460:    */  public Entity readEntityReference(XMLEventReader reader)
/* 461:    */    throws XMLStreamException
/* 462:    */  {
/* 463:463 */    XMLEvent event = reader.peek();
/* 464:    */    
/* 465:465 */    if (event.isEntityReference()) {
/* 466:466 */      EntityReference entityRef = (EntityReference)reader.nextEvent();
/* 467:    */      
/* 468:468 */      return createEntity(entityRef);
/* 469:    */    }
/* 470:470 */    throw new XMLStreamException("Expected EntityRef event, found: " + event);
/* 471:    */  }
/* 472:    */  
/* 489:    */  public org.dom4j.ProcessingInstruction readProcessingInstruction(XMLEventReader reader)
/* 490:    */    throws XMLStreamException
/* 491:    */  {
/* 492:492 */    XMLEvent event = reader.peek();
/* 493:    */    
/* 494:494 */    if (event.isProcessingInstruction()) {
/* 495:495 */      javax.xml.stream.events.ProcessingInstruction pi = (javax.xml.stream.events.ProcessingInstruction)reader.nextEvent();
/* 496:    */      
/* 498:498 */      return createProcessingInstruction(pi);
/* 499:    */    }
/* 500:500 */    throw new XMLStreamException("Expected PI event, found: " + event);
/* 501:    */  }
/* 502:    */  
/* 513:    */  public Element createElement(StartElement startEvent)
/* 514:    */  {
/* 515:515 */    javax.xml.namespace.QName qname = startEvent.getName();
/* 516:516 */    org.dom4j.QName elemName = createQName(qname);
/* 517:    */    
/* 518:518 */    Element elem = this.factory.createElement(elemName);
/* 519:    */    
/* 521:521 */    for (Iterator i = startEvent.getAttributes(); i.hasNext();) {
/* 522:522 */      javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)i.next();
/* 523:523 */      elem.addAttribute(createQName(attr.getName()), attr.getValue());
/* 524:    */    }
/* 525:    */    
/* 527:527 */    for (Iterator i = startEvent.getNamespaces(); i.hasNext();) {
/* 528:528 */      javax.xml.stream.events.Namespace ns = (javax.xml.stream.events.Namespace)i.next();
/* 529:529 */      elem.addNamespace(ns.getPrefix(), ns.getNamespaceURI());
/* 530:    */    }
/* 531:    */    
/* 532:532 */    return elem;
/* 533:    */  }
/* 534:    */  
/* 545:    */  public org.dom4j.Attribute createAttribute(Element elem, javax.xml.stream.events.Attribute attr)
/* 546:    */  {
/* 547:547 */    return this.factory.createAttribute(elem, createQName(attr.getName()), attr.getValue());
/* 548:    */  }
/* 549:    */  
/* 559:    */  public org.dom4j.Namespace createNamespace(javax.xml.stream.events.Namespace ns)
/* 560:    */  {
/* 561:561 */    return this.factory.createNamespace(ns.getPrefix(), ns.getNamespaceURI());
/* 562:    */  }
/* 563:    */  
/* 574:    */  public CharacterData createCharacterData(Characters characters)
/* 575:    */  {
/* 576:576 */    String data = characters.getData();
/* 577:    */    
/* 578:578 */    if (characters.isCData()) {
/* 579:579 */      return this.factory.createCDATA(data);
/* 580:    */    }
/* 581:581 */    return this.factory.createText(data);
/* 582:    */  }
/* 583:    */  
/* 593:    */  public org.dom4j.Comment createComment(javax.xml.stream.events.Comment comment)
/* 594:    */  {
/* 595:595 */    return this.factory.createComment(comment.getText());
/* 596:    */  }
/* 597:    */  
/* 607:    */  public Entity createEntity(EntityReference entityRef)
/* 608:    */  {
/* 609:609 */    return this.factory.createEntity(entityRef.getName(), entityRef.getDeclaration().getReplacementText());
/* 610:    */  }
/* 611:    */  
/* 624:    */  public org.dom4j.ProcessingInstruction createProcessingInstruction(javax.xml.stream.events.ProcessingInstruction pi)
/* 625:    */  {
/* 626:626 */    return this.factory.createProcessingInstruction(pi.getTarget(), pi.getData());
/* 627:    */  }
/* 628:    */  
/* 637:    */  public org.dom4j.QName createQName(javax.xml.namespace.QName qname)
/* 638:    */  {
/* 639:639 */    return this.factory.createQName(qname.getLocalPart(), qname.getPrefix(), qname.getNamespaceURI());
/* 640:    */  }
/* 641:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.STAXEventReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */