/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.XMLInputFactory;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.EntityDeclaration;
/*     */ import javax.xml.stream.events.EntityReference;
/*     */ import javax.xml.stream.events.StartDocument;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import org.dom4j.CharacterData;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Entity;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class STAXEventReader
/*     */ {
/*     */   private DocumentFactory factory;
/*  48 */   private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
/*     */ 
/*     */   public STAXEventReader()
/*     */   {
/*  55 */     this.factory = DocumentFactory.getInstance();
/*     */   }
/*     */ 
/*     */   public STAXEventReader(DocumentFactory factory)
/*     */   {
/*  67 */     if (factory != null)
/*  68 */       this.factory = factory;
/*     */     else
/*  70 */       this.factory = DocumentFactory.getInstance();
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory)
/*     */   {
/*  82 */     if (documentFactory != null)
/*  83 */       this.factory = documentFactory;
/*     */     else
/*  85 */       this.factory = DocumentFactory.getInstance();
/*     */   }
/*     */ 
/*     */   public Document readDocument(InputStream is)
/*     */     throws XMLStreamException
/*     */   {
/* 102 */     return readDocument(is, null);
/*     */   }
/*     */ 
/*     */   public Document readDocument(Reader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 118 */     return readDocument(reader, null);
/*     */   }
/*     */ 
/*     */   public Document readDocument(InputStream is, String systemId)
/*     */     throws XMLStreamException
/*     */   {
/* 137 */     XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, is);
/*     */     try
/*     */     {
/* 141 */       return readDocument(eventReader);
/*     */     } finally {
/* 143 */       eventReader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document readDocument(Reader reader, String systemId)
/*     */     throws XMLStreamException
/*     */   {
/* 163 */     XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, reader);
/*     */     try
/*     */     {
/* 167 */       return readDocument(eventReader);
/*     */     } finally {
/* 169 */       eventReader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Node readNode(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 195 */     XMLEvent event = reader.peek();
/*     */ 
/* 197 */     if (event.isStartElement())
/* 198 */       return readElement(reader);
/* 199 */     if (event.isCharacters())
/* 200 */       return readCharacters(reader);
/* 201 */     if (event.isStartDocument())
/* 202 */       return readDocument(reader);
/* 203 */     if (event.isProcessingInstruction())
/* 204 */       return readProcessingInstruction(reader);
/* 205 */     if (event.isEntityReference())
/* 206 */       return readEntityReference(reader);
/* 207 */     if (event.isAttribute())
/* 208 */       return readAttribute(reader);
/* 209 */     if (event.isNamespace()) {
/* 210 */       return readNamespace(reader);
/*     */     }
/* 212 */     throw new XMLStreamException("Unsupported event: " + event);
/*     */   }
/*     */ 
/*     */   public Document readDocument(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 231 */     Document doc = null;
/*     */ 
/* 233 */     while (reader.hasNext()) {
/* 234 */       XMLEvent nextEvent = reader.peek();
/* 235 */       int type = nextEvent.getEventType();
/*     */ 
/* 237 */       switch (type)
/*     */       {
/*     */       case 7:
/* 240 */         StartDocument event = (StartDocument)reader.nextEvent();
/*     */ 
/* 242 */         if (doc == null)
/*     */         {
/* 244 */           if (event.encodingSet()) {
/* 245 */             String encodingScheme = event.getCharacterEncodingScheme();
/*     */ 
/* 247 */             doc = this.factory.createDocument(encodingScheme);
/*     */           } else {
/* 249 */             doc = this.factory.createDocument();
/*     */           }
/*     */         }
/*     */         else {
/* 253 */           String msg = "Unexpected StartDocument event";
/* 254 */           throw new XMLStreamException(msg, event.getLocation());
/*     */         }
/*     */ 
/*     */         break;
/*     */       case 4:
/*     */       case 6:
/*     */       case 8:
/* 264 */         reader.nextEvent();
/*     */ 
/* 266 */         break;
/*     */       case 5:
/*     */       default:
/* 270 */         if (doc == null)
/*     */         {
/* 272 */           doc = this.factory.createDocument();
/*     */         }
/*     */ 
/* 275 */         Node n = readNode(reader);
/* 276 */         doc.add(n);
/*     */       }
/*     */     }
/*     */ 
/* 280 */     return doc;
/*     */   }
/*     */ 
/*     */   public Element readElement(XMLEventReader eventReader)
/*     */     throws XMLStreamException
/*     */   {
/* 300 */     XMLEvent event = eventReader.peek();
/*     */ 
/* 302 */     if (event.isStartElement())
/*     */     {
/* 304 */       StartElement startTag = eventReader.nextEvent().asStartElement();
/* 305 */       Element elem = createElement(startTag);
/*     */       while (true)
/*     */       {
/* 309 */         if (!eventReader.hasNext()) {
/* 310 */           String msg = "Unexpected end of stream while reading element content";
/*     */ 
/* 312 */           throw new XMLStreamException(msg);
/*     */         }
/*     */ 
/* 315 */         XMLEvent nextEvent = eventReader.peek();
/*     */ 
/* 317 */         if (nextEvent.isEndElement()) {
/* 318 */           EndElement endElem = eventReader.nextEvent().asEndElement();
/*     */ 
/* 320 */           if (endElem.getName().equals(startTag.getName())) break;
/* 321 */           throw new XMLStreamException("Expected " + startTag.getName() + " end-tag, but found" + endElem.getName());
/*     */         }
/*     */ 
/* 329 */         Node child = readNode(eventReader);
/* 330 */         elem.add(child);
/*     */       }
/*     */ 
/* 333 */       return elem;
/*     */     }
/* 335 */     throw new XMLStreamException("Expected Element event, found: " + event);
/*     */   }
/*     */ 
/*     */   public org.dom4j.Attribute readAttribute(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 355 */     XMLEvent event = reader.peek();
/*     */ 
/* 357 */     if (event.isAttribute()) {
/* 358 */       javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)reader.nextEvent();
/*     */ 
/* 360 */       return createAttribute(null, attr);
/*     */     }
/* 362 */     throw new XMLStreamException("Expected Attribute event, found: " + event);
/*     */   }
/*     */ 
/*     */   public org.dom4j.Namespace readNamespace(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 382 */     XMLEvent event = reader.peek();
/*     */ 
/* 384 */     if (event.isNamespace()) {
/* 385 */       javax.xml.stream.events.Namespace ns = (javax.xml.stream.events.Namespace)reader.nextEvent();
/*     */ 
/* 387 */       return createNamespace(ns);
/*     */     }
/* 389 */     throw new XMLStreamException("Expected Namespace event, found: " + event);
/*     */   }
/*     */ 
/*     */   public CharacterData readCharacters(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 409 */     XMLEvent event = reader.peek();
/*     */ 
/* 411 */     if (event.isCharacters()) {
/* 412 */       Characters characters = reader.nextEvent().asCharacters();
/*     */ 
/* 414 */       return createCharacterData(characters);
/*     */     }
/* 416 */     throw new XMLStreamException("Expected Characters event, found: " + event);
/*     */   }
/*     */ 
/*     */   public org.dom4j.Comment readComment(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 436 */     XMLEvent event = reader.peek();
/*     */ 
/* 438 */     if ((event instanceof javax.xml.stream.events.Comment)) {
/* 439 */       return createComment((javax.xml.stream.events.Comment)reader.nextEvent());
/*     */     }
/* 441 */     throw new XMLStreamException("Expected Comment event, found: " + event);
/*     */   }
/*     */ 
/*     */   public Entity readEntityReference(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 463 */     XMLEvent event = reader.peek();
/*     */ 
/* 465 */     if (event.isEntityReference()) {
/* 466 */       EntityReference entityRef = (EntityReference)reader.nextEvent();
/*     */ 
/* 468 */       return createEntity(entityRef);
/*     */     }
/* 470 */     throw new XMLStreamException("Expected EntityRef event, found: " + event);
/*     */   }
/*     */ 
/*     */   public org.dom4j.ProcessingInstruction readProcessingInstruction(XMLEventReader reader)
/*     */     throws XMLStreamException
/*     */   {
/* 492 */     XMLEvent event = reader.peek();
/*     */ 
/* 494 */     if (event.isProcessingInstruction()) {
/* 495 */       javax.xml.stream.events.ProcessingInstruction pi = (javax.xml.stream.events.ProcessingInstruction)reader.nextEvent();
/*     */ 
/* 498 */       return createProcessingInstruction(pi);
/*     */     }
/* 500 */     throw new XMLStreamException("Expected PI event, found: " + event);
/*     */   }
/*     */ 
/*     */   public Element createElement(StartElement startEvent)
/*     */   {
/* 515 */     javax.xml.namespace.QName qname = startEvent.getName();
/* 516 */     org.dom4j.QName elemName = createQName(qname);
/*     */ 
/* 518 */     Element elem = this.factory.createElement(elemName);
/*     */ 
/* 521 */     for (Iterator i = startEvent.getAttributes(); i.hasNext(); ) {
/* 522 */       javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)i.next();
/* 523 */       elem.addAttribute(createQName(attr.getName()), attr.getValue());
/*     */     }
/*     */ 
/* 527 */     for (Iterator i = startEvent.getNamespaces(); i.hasNext(); ) {
/* 528 */       javax.xml.stream.events.Namespace ns = (javax.xml.stream.events.Namespace)i.next();
/* 529 */       elem.addNamespace(ns.getPrefix(), ns.getNamespaceURI());
/*     */     }
/*     */ 
/* 532 */     return elem;
/*     */   }
/*     */ 
/*     */   public org.dom4j.Attribute createAttribute(Element elem, javax.xml.stream.events.Attribute attr)
/*     */   {
/* 547 */     return this.factory.createAttribute(elem, createQName(attr.getName()), attr.getValue());
/*     */   }
/*     */ 
/*     */   public org.dom4j.Namespace createNamespace(javax.xml.stream.events.Namespace ns)
/*     */   {
/* 561 */     return this.factory.createNamespace(ns.getPrefix(), ns.getNamespaceURI());
/*     */   }
/*     */ 
/*     */   public CharacterData createCharacterData(Characters characters)
/*     */   {
/* 576 */     String data = characters.getData();
/*     */ 
/* 578 */     if (characters.isCData()) {
/* 579 */       return this.factory.createCDATA(data);
/*     */     }
/* 581 */     return this.factory.createText(data);
/*     */   }
/*     */ 
/*     */   public org.dom4j.Comment createComment(javax.xml.stream.events.Comment comment)
/*     */   {
/* 595 */     return this.factory.createComment(comment.getText());
/*     */   }
/*     */ 
/*     */   public Entity createEntity(EntityReference entityRef)
/*     */   {
/* 609 */     return this.factory.createEntity(entityRef.getName(), entityRef.getDeclaration().getReplacementText());
/*     */   }
/*     */ 
/*     */   public org.dom4j.ProcessingInstruction createProcessingInstruction(javax.xml.stream.events.ProcessingInstruction pi)
/*     */   {
/* 626 */     return this.factory.createProcessingInstruction(pi.getTarget(), pi.getData());
/*     */   }
/*     */ 
/*     */   public org.dom4j.QName createQName(javax.xml.namespace.QName qname)
/*     */   {
/* 639 */     return this.factory.createQName(qname.getLocalPart(), qname.getPrefix(), qname.getNamespaceURI());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.STAXEventReader
 * JD-Core Version:    0.6.2
 */