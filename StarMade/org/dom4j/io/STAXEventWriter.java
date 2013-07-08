/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileWriter;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.OutputStream;
/*   7:    */import java.io.StringWriter;
/*   8:    */import java.io.Writer;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.List;
/*  11:    */import javax.xml.stream.XMLEventFactory;
/*  12:    */import javax.xml.stream.XMLOutputFactory;
/*  13:    */import javax.xml.stream.XMLStreamException;
/*  14:    */import javax.xml.stream.events.Characters;
/*  15:    */import javax.xml.stream.events.DTD;
/*  16:    */import javax.xml.stream.events.EndDocument;
/*  17:    */import javax.xml.stream.events.EndElement;
/*  18:    */import javax.xml.stream.events.EntityReference;
/*  19:    */import javax.xml.stream.events.StartDocument;
/*  20:    */import javax.xml.stream.events.StartElement;
/*  21:    */import javax.xml.stream.util.XMLEventConsumer;
/*  22:    */import org.dom4j.Branch;
/*  23:    */import org.dom4j.CDATA;
/*  24:    */import org.dom4j.Document;
/*  25:    */import org.dom4j.DocumentType;
/*  26:    */import org.dom4j.Element;
/*  27:    */import org.dom4j.Entity;
/*  28:    */import org.dom4j.Node;
/*  29:    */import org.dom4j.Text;
/*  30:    */
/*  53:    */public class STAXEventWriter
/*  54:    */{
/*  55:    */  private XMLEventConsumer consumer;
/*  56: 56 */  private XMLEventFactory factory = XMLEventFactory.newInstance();
/*  57:    */  
/*  58: 58 */  private XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
/*  59:    */  
/*  66:    */  public STAXEventWriter() {}
/*  67:    */  
/*  73:    */  public STAXEventWriter(File file)
/*  74:    */    throws XMLStreamException, IOException
/*  75:    */  {
/*  76: 76 */    this.consumer = this.outputFactory.createXMLEventWriter(new FileWriter(file));
/*  77:    */  }
/*  78:    */  
/*  88:    */  public STAXEventWriter(Writer writer)
/*  89:    */    throws XMLStreamException
/*  90:    */  {
/*  91: 91 */    this.consumer = this.outputFactory.createXMLEventWriter(writer);
/*  92:    */  }
/*  93:    */  
/* 103:    */  public STAXEventWriter(OutputStream stream)
/* 104:    */    throws XMLStreamException
/* 105:    */  {
/* 106:106 */    this.consumer = this.outputFactory.createXMLEventWriter(stream);
/* 107:    */  }
/* 108:    */  
/* 115:    */  public STAXEventWriter(XMLEventConsumer consumer)
/* 116:    */  {
/* 117:117 */    this.consumer = consumer;
/* 118:    */  }
/* 119:    */  
/* 125:    */  public XMLEventConsumer getConsumer()
/* 126:    */  {
/* 127:127 */    return this.consumer;
/* 128:    */  }
/* 129:    */  
/* 135:    */  public void setConsumer(XMLEventConsumer consumer)
/* 136:    */  {
/* 137:137 */    this.consumer = consumer;
/* 138:    */  }
/* 139:    */  
/* 144:    */  public XMLEventFactory getEventFactory()
/* 145:    */  {
/* 146:146 */    return this.factory;
/* 147:    */  }
/* 148:    */  
/* 154:    */  public void setEventFactory(XMLEventFactory eventFactory)
/* 155:    */  {
/* 156:156 */    this.factory = eventFactory;
/* 157:    */  }
/* 158:    */  
/* 167:    */  public void writeNode(Node n)
/* 168:    */    throws XMLStreamException
/* 169:    */  {
/* 170:170 */    switch (n.getNodeType()) {
/* 171:    */    case 1: 
/* 172:172 */      writeElement((Element)n);
/* 173:    */      
/* 174:174 */      break;
/* 175:    */    
/* 176:    */    case 3: 
/* 177:177 */      writeText((Text)n);
/* 178:    */      
/* 179:179 */      break;
/* 180:    */    
/* 181:    */    case 2: 
/* 182:182 */      writeAttribute((org.dom4j.Attribute)n);
/* 183:    */      
/* 184:184 */      break;
/* 185:    */    
/* 186:    */    case 13: 
/* 187:187 */      writeNamespace((org.dom4j.Namespace)n);
/* 188:    */      
/* 189:189 */      break;
/* 190:    */    
/* 191:    */    case 8: 
/* 192:192 */      writeComment((org.dom4j.Comment)n);
/* 193:    */      
/* 194:194 */      break;
/* 195:    */    
/* 196:    */    case 4: 
/* 197:197 */      writeCDATA((CDATA)n);
/* 198:    */      
/* 199:199 */      break;
/* 200:    */    
/* 201:    */    case 7: 
/* 202:202 */      writeProcessingInstruction((org.dom4j.ProcessingInstruction)n);
/* 203:    */      
/* 204:204 */      break;
/* 205:    */    
/* 206:    */    case 5: 
/* 207:207 */      writeEntity((Entity)n);
/* 208:    */      
/* 209:209 */      break;
/* 210:    */    
/* 211:    */    case 9: 
/* 212:212 */      writeDocument((Document)n);
/* 213:    */      
/* 214:214 */      break;
/* 215:    */    
/* 216:    */    case 10: 
/* 217:217 */      writeDocumentType((DocumentType)n);
/* 218:    */      
/* 219:219 */      break;
/* 220:    */    case 6: case 11: 
/* 221:    */    case 12: default: 
/* 222:222 */      throw new XMLStreamException("Unsupported DOM4J Node: " + n);
/* 223:    */    }
/* 224:    */    
/* 225:    */  }
/* 226:    */  
/* 235:    */  public void writeChildNodes(Branch branch)
/* 236:    */    throws XMLStreamException
/* 237:    */  {
/* 238:238 */    int i = 0; for (int s = branch.nodeCount(); i < s; i++) {
/* 239:239 */      Node n = branch.node(i);
/* 240:240 */      writeNode(n);
/* 241:    */    }
/* 242:    */  }
/* 243:    */  
/* 251:    */  public void writeElement(Element elem)
/* 252:    */    throws XMLStreamException
/* 253:    */  {
/* 254:254 */    this.consumer.add(createStartElement(elem));
/* 255:255 */    writeChildNodes(elem);
/* 256:256 */    this.consumer.add(createEndElement(elem));
/* 257:    */  }
/* 258:    */  
/* 268:    */  public StartElement createStartElement(Element elem)
/* 269:    */  {
/* 270:270 */    javax.xml.namespace.QName tagName = createQName(elem.getQName());
/* 271:    */    
/* 273:273 */    Iterator attrIter = new AttributeIterator(elem.attributeIterator());
/* 274:274 */    Iterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
/* 275:    */    
/* 278:278 */    return this.factory.createStartElement(tagName, attrIter, nsIter);
/* 279:    */  }
/* 280:    */  
/* 288:    */  public EndElement createEndElement(Element elem)
/* 289:    */  {
/* 290:290 */    javax.xml.namespace.QName tagName = createQName(elem.getQName());
/* 291:291 */    Iterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
/* 292:    */    
/* 294:294 */    return this.factory.createEndElement(tagName, nsIter);
/* 295:    */  }
/* 296:    */  
/* 304:    */  public void writeAttribute(org.dom4j.Attribute attr)
/* 305:    */    throws XMLStreamException
/* 306:    */  {
/* 307:307 */    this.consumer.add(createAttribute(attr));
/* 308:    */  }
/* 309:    */  
/* 319:    */  public javax.xml.stream.events.Attribute createAttribute(org.dom4j.Attribute attr)
/* 320:    */  {
/* 321:321 */    javax.xml.namespace.QName attrName = createQName(attr.getQName());
/* 322:322 */    String value = attr.getValue();
/* 323:    */    
/* 324:324 */    return this.factory.createAttribute(attrName, value);
/* 325:    */  }
/* 326:    */  
/* 334:    */  public void writeNamespace(org.dom4j.Namespace ns)
/* 335:    */    throws XMLStreamException
/* 336:    */  {
/* 337:337 */    this.consumer.add(createNamespace(ns));
/* 338:    */  }
/* 339:    */  
/* 348:    */  public javax.xml.stream.events.Namespace createNamespace(org.dom4j.Namespace ns)
/* 349:    */  {
/* 350:350 */    String prefix = ns.getPrefix();
/* 351:351 */    String uri = ns.getURI();
/* 352:    */    
/* 353:353 */    return this.factory.createNamespace(prefix, uri);
/* 354:    */  }
/* 355:    */  
/* 363:    */  public void writeText(Text text)
/* 364:    */    throws XMLStreamException
/* 365:    */  {
/* 366:366 */    this.consumer.add(createCharacters(text));
/* 367:    */  }
/* 368:    */  
/* 376:    */  public Characters createCharacters(Text text)
/* 377:    */  {
/* 378:378 */    return this.factory.createCharacters(text.getText());
/* 379:    */  }
/* 380:    */  
/* 388:    */  public void writeCDATA(CDATA cdata)
/* 389:    */    throws XMLStreamException
/* 390:    */  {
/* 391:391 */    this.consumer.add(createCharacters(cdata));
/* 392:    */  }
/* 393:    */  
/* 401:    */  public Characters createCharacters(CDATA cdata)
/* 402:    */  {
/* 403:403 */    return this.factory.createCData(cdata.getText());
/* 404:    */  }
/* 405:    */  
/* 413:    */  public void writeComment(org.dom4j.Comment comment)
/* 414:    */    throws XMLStreamException
/* 415:    */  {
/* 416:416 */    this.consumer.add(createComment(comment));
/* 417:    */  }
/* 418:    */  
/* 427:    */  public javax.xml.stream.events.Comment createComment(org.dom4j.Comment comment)
/* 428:    */  {
/* 429:429 */    return this.factory.createComment(comment.getText());
/* 430:    */  }
/* 431:    */  
/* 440:    */  public void writeProcessingInstruction(org.dom4j.ProcessingInstruction pi)
/* 441:    */    throws XMLStreamException
/* 442:    */  {
/* 443:443 */    this.consumer.add(createProcessingInstruction(pi));
/* 444:    */  }
/* 445:    */  
/* 457:    */  public javax.xml.stream.events.ProcessingInstruction createProcessingInstruction(org.dom4j.ProcessingInstruction pi)
/* 458:    */  {
/* 459:459 */    String target = pi.getTarget();
/* 460:460 */    String data = pi.getText();
/* 461:    */    
/* 462:462 */    return this.factory.createProcessingInstruction(target, data);
/* 463:    */  }
/* 464:    */  
/* 472:    */  public void writeEntity(Entity entity)
/* 473:    */    throws XMLStreamException
/* 474:    */  {
/* 475:475 */    this.consumer.add(createEntityReference(entity));
/* 476:    */  }
/* 477:    */  
/* 486:    */  private EntityReference createEntityReference(Entity entity)
/* 487:    */  {
/* 488:488 */    return this.factory.createEntityReference(entity.getName(), null);
/* 489:    */  }
/* 490:    */  
/* 499:    */  public void writeDocumentType(DocumentType docType)
/* 500:    */    throws XMLStreamException
/* 501:    */  {
/* 502:502 */    this.consumer.add(createDTD(docType));
/* 503:    */  }
/* 504:    */  
/* 515:    */  public DTD createDTD(DocumentType docType)
/* 516:    */  {
/* 517:517 */    StringWriter decl = new StringWriter();
/* 518:    */    try
/* 519:    */    {
/* 520:520 */      docType.write(decl);
/* 521:    */    } catch (IOException e) {
/* 522:522 */      throw new RuntimeException("Error writing DTD", e);
/* 523:    */    }
/* 524:    */    
/* 525:525 */    return this.factory.createDTD(decl.toString());
/* 526:    */  }
/* 527:    */  
/* 536:    */  public void writeDocument(Document doc)
/* 537:    */    throws XMLStreamException
/* 538:    */  {
/* 539:539 */    this.consumer.add(createStartDocument(doc));
/* 540:    */    
/* 541:541 */    writeChildNodes(doc);
/* 542:    */    
/* 543:543 */    this.consumer.add(createEndDocument(doc));
/* 544:    */  }
/* 545:    */  
/* 554:    */  public StartDocument createStartDocument(Document doc)
/* 555:    */  {
/* 556:556 */    String encoding = doc.getXMLEncoding();
/* 557:    */    
/* 558:558 */    if (encoding != null) {
/* 559:559 */      return this.factory.createStartDocument(encoding);
/* 560:    */    }
/* 561:561 */    return this.factory.createStartDocument();
/* 562:    */  }
/* 563:    */  
/* 573:    */  public EndDocument createEndDocument(Document doc)
/* 574:    */  {
/* 575:575 */    return this.factory.createEndDocument();
/* 576:    */  }
/* 577:    */  
/* 586:    */  public javax.xml.namespace.QName createQName(org.dom4j.QName qname)
/* 587:    */  {
/* 588:588 */    return new javax.xml.namespace.QName(qname.getNamespaceURI(), qname.getName(), qname.getNamespacePrefix());
/* 589:    */  }
/* 590:    */  
/* 593:    */  private class AttributeIterator
/* 594:    */    implements Iterator
/* 595:    */  {
/* 596:    */    private Iterator iter;
/* 597:    */    
/* 599:    */    public AttributeIterator(Iterator iter)
/* 600:    */    {
/* 601:601 */      this.iter = iter;
/* 602:    */    }
/* 603:    */    
/* 604:    */    public boolean hasNext() {
/* 605:605 */      return this.iter.hasNext();
/* 606:    */    }
/* 607:    */    
/* 608:    */    public Object next() {
/* 609:609 */      org.dom4j.Attribute attr = (org.dom4j.Attribute)this.iter.next();
/* 610:610 */      javax.xml.namespace.QName attrName = STAXEventWriter.this.createQName(attr.getQName());
/* 611:611 */      String value = attr.getValue();
/* 612:    */      
/* 613:613 */      return STAXEventWriter.this.factory.createAttribute(attrName, value);
/* 614:    */    }
/* 615:    */    
/* 616:    */    public void remove() {
/* 617:617 */      throw new UnsupportedOperationException();
/* 618:    */    }
/* 619:    */  }
/* 620:    */  
/* 622:    */  private class NamespaceIterator
/* 623:    */    implements Iterator
/* 624:    */  {
/* 625:    */    private Iterator iter;
/* 626:    */    
/* 627:    */    public NamespaceIterator(Iterator iter)
/* 628:    */    {
/* 629:629 */      this.iter = iter;
/* 630:    */    }
/* 631:    */    
/* 632:    */    public boolean hasNext() {
/* 633:633 */      return this.iter.hasNext();
/* 634:    */    }
/* 635:    */    
/* 636:    */    public Object next() {
/* 637:637 */      org.dom4j.Namespace ns = (org.dom4j.Namespace)this.iter.next();
/* 638:638 */      String prefix = ns.getPrefix();
/* 639:639 */      String nsURI = ns.getURI();
/* 640:    */      
/* 641:641 */      return STAXEventWriter.this.factory.createNamespace(prefix, nsURI);
/* 642:    */    }
/* 643:    */    
/* 644:    */    public void remove() {
/* 645:645 */      throw new UnsupportedOperationException();
/* 646:    */    }
/* 647:    */  }
/* 648:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.STAXEventWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */