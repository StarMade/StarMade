/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.stream.XMLEventFactory;
/*     */ import javax.xml.stream.XMLOutputFactory;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import javax.xml.stream.events.DTD;
/*     */ import javax.xml.stream.events.EndDocument;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.EntityReference;
/*     */ import javax.xml.stream.events.StartDocument;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.util.XMLEventConsumer;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Entity;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.Text;
/*     */ 
/*     */ public class STAXEventWriter
/*     */ {
/*     */   private XMLEventConsumer consumer;
/*  56 */   private XMLEventFactory factory = XMLEventFactory.newInstance();
/*     */ 
/*  58 */   private XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
/*     */ 
/*     */   public STAXEventWriter()
/*     */   {
/*     */   }
/*     */ 
/*     */   public STAXEventWriter(File file)
/*     */     throws XMLStreamException, IOException
/*     */   {
/*  76 */     this.consumer = this.outputFactory.createXMLEventWriter(new FileWriter(file));
/*     */   }
/*     */ 
/*     */   public STAXEventWriter(Writer writer)
/*     */     throws XMLStreamException
/*     */   {
/*  91 */     this.consumer = this.outputFactory.createXMLEventWriter(writer);
/*     */   }
/*     */ 
/*     */   public STAXEventWriter(OutputStream stream)
/*     */     throws XMLStreamException
/*     */   {
/* 106 */     this.consumer = this.outputFactory.createXMLEventWriter(stream);
/*     */   }
/*     */ 
/*     */   public STAXEventWriter(XMLEventConsumer consumer)
/*     */   {
/* 117 */     this.consumer = consumer;
/*     */   }
/*     */ 
/*     */   public XMLEventConsumer getConsumer()
/*     */   {
/* 127 */     return this.consumer;
/*     */   }
/*     */ 
/*     */   public void setConsumer(XMLEventConsumer consumer)
/*     */   {
/* 137 */     this.consumer = consumer;
/*     */   }
/*     */ 
/*     */   public XMLEventFactory getEventFactory()
/*     */   {
/* 146 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public void setEventFactory(XMLEventFactory eventFactory)
/*     */   {
/* 156 */     this.factory = eventFactory;
/*     */   }
/*     */ 
/*     */   public void writeNode(Node n)
/*     */     throws XMLStreamException
/*     */   {
/* 170 */     switch (n.getNodeType()) {
/*     */     case 1:
/* 172 */       writeElement((Element)n);
/*     */ 
/* 174 */       break;
/*     */     case 3:
/* 177 */       writeText((Text)n);
/*     */ 
/* 179 */       break;
/*     */     case 2:
/* 182 */       writeAttribute((org.dom4j.Attribute)n);
/*     */ 
/* 184 */       break;
/*     */     case 13:
/* 187 */       writeNamespace((org.dom4j.Namespace)n);
/*     */ 
/* 189 */       break;
/*     */     case 8:
/* 192 */       writeComment((org.dom4j.Comment)n);
/*     */ 
/* 194 */       break;
/*     */     case 4:
/* 197 */       writeCDATA((CDATA)n);
/*     */ 
/* 199 */       break;
/*     */     case 7:
/* 202 */       writeProcessingInstruction((org.dom4j.ProcessingInstruction)n);
/*     */ 
/* 204 */       break;
/*     */     case 5:
/* 207 */       writeEntity((Entity)n);
/*     */ 
/* 209 */       break;
/*     */     case 9:
/* 212 */       writeDocument((Document)n);
/*     */ 
/* 214 */       break;
/*     */     case 10:
/* 217 */       writeDocumentType((DocumentType)n);
/*     */ 
/* 219 */       break;
/*     */     case 6:
/*     */     case 11:
/*     */     case 12:
/*     */     default:
/* 222 */       throw new XMLStreamException("Unsupported DOM4J Node: " + n);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeChildNodes(Branch branch)
/*     */     throws XMLStreamException
/*     */   {
/* 238 */     int i = 0; for (int s = branch.nodeCount(); i < s; i++) {
/* 239 */       Node n = branch.node(i);
/* 240 */       writeNode(n);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeElement(Element elem)
/*     */     throws XMLStreamException
/*     */   {
/* 254 */     this.consumer.add(createStartElement(elem));
/* 255 */     writeChildNodes(elem);
/* 256 */     this.consumer.add(createEndElement(elem));
/*     */   }
/*     */ 
/*     */   public StartElement createStartElement(Element elem)
/*     */   {
/* 270 */     javax.xml.namespace.QName tagName = createQName(elem.getQName());
/*     */ 
/* 273 */     Iterator attrIter = new AttributeIterator(elem.attributeIterator());
/* 274 */     Iterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
/*     */ 
/* 278 */     return this.factory.createStartElement(tagName, attrIter, nsIter);
/*     */   }
/*     */ 
/*     */   public EndElement createEndElement(Element elem)
/*     */   {
/* 290 */     javax.xml.namespace.QName tagName = createQName(elem.getQName());
/* 291 */     Iterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
/*     */ 
/* 294 */     return this.factory.createEndElement(tagName, nsIter);
/*     */   }
/*     */ 
/*     */   public void writeAttribute(org.dom4j.Attribute attr)
/*     */     throws XMLStreamException
/*     */   {
/* 307 */     this.consumer.add(createAttribute(attr));
/*     */   }
/*     */ 
/*     */   public javax.xml.stream.events.Attribute createAttribute(org.dom4j.Attribute attr)
/*     */   {
/* 321 */     javax.xml.namespace.QName attrName = createQName(attr.getQName());
/* 322 */     String value = attr.getValue();
/*     */ 
/* 324 */     return this.factory.createAttribute(attrName, value);
/*     */   }
/*     */ 
/*     */   public void writeNamespace(org.dom4j.Namespace ns)
/*     */     throws XMLStreamException
/*     */   {
/* 337 */     this.consumer.add(createNamespace(ns));
/*     */   }
/*     */ 
/*     */   public javax.xml.stream.events.Namespace createNamespace(org.dom4j.Namespace ns)
/*     */   {
/* 350 */     String prefix = ns.getPrefix();
/* 351 */     String uri = ns.getURI();
/*     */ 
/* 353 */     return this.factory.createNamespace(prefix, uri);
/*     */   }
/*     */ 
/*     */   public void writeText(Text text)
/*     */     throws XMLStreamException
/*     */   {
/* 366 */     this.consumer.add(createCharacters(text));
/*     */   }
/*     */ 
/*     */   public Characters createCharacters(Text text)
/*     */   {
/* 378 */     return this.factory.createCharacters(text.getText());
/*     */   }
/*     */ 
/*     */   public void writeCDATA(CDATA cdata)
/*     */     throws XMLStreamException
/*     */   {
/* 391 */     this.consumer.add(createCharacters(cdata));
/*     */   }
/*     */ 
/*     */   public Characters createCharacters(CDATA cdata)
/*     */   {
/* 403 */     return this.factory.createCData(cdata.getText());
/*     */   }
/*     */ 
/*     */   public void writeComment(org.dom4j.Comment comment)
/*     */     throws XMLStreamException
/*     */   {
/* 416 */     this.consumer.add(createComment(comment));
/*     */   }
/*     */ 
/*     */   public javax.xml.stream.events.Comment createComment(org.dom4j.Comment comment)
/*     */   {
/* 429 */     return this.factory.createComment(comment.getText());
/*     */   }
/*     */ 
/*     */   public void writeProcessingInstruction(org.dom4j.ProcessingInstruction pi)
/*     */     throws XMLStreamException
/*     */   {
/* 443 */     this.consumer.add(createProcessingInstruction(pi));
/*     */   }
/*     */ 
/*     */   public javax.xml.stream.events.ProcessingInstruction createProcessingInstruction(org.dom4j.ProcessingInstruction pi)
/*     */   {
/* 459 */     String target = pi.getTarget();
/* 460 */     String data = pi.getText();
/*     */ 
/* 462 */     return this.factory.createProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public void writeEntity(Entity entity)
/*     */     throws XMLStreamException
/*     */   {
/* 475 */     this.consumer.add(createEntityReference(entity));
/*     */   }
/*     */ 
/*     */   private EntityReference createEntityReference(Entity entity)
/*     */   {
/* 488 */     return this.factory.createEntityReference(entity.getName(), null);
/*     */   }
/*     */ 
/*     */   public void writeDocumentType(DocumentType docType)
/*     */     throws XMLStreamException
/*     */   {
/* 502 */     this.consumer.add(createDTD(docType));
/*     */   }
/*     */ 
/*     */   public DTD createDTD(DocumentType docType)
/*     */   {
/* 517 */     StringWriter decl = new StringWriter();
/*     */     try
/*     */     {
/* 520 */       docType.write(decl);
/*     */     } catch (IOException e) {
/* 522 */       throw new RuntimeException("Error writing DTD", e);
/*     */     }
/*     */ 
/* 525 */     return this.factory.createDTD(decl.toString());
/*     */   }
/*     */ 
/*     */   public void writeDocument(Document doc)
/*     */     throws XMLStreamException
/*     */   {
/* 539 */     this.consumer.add(createStartDocument(doc));
/*     */ 
/* 541 */     writeChildNodes(doc);
/*     */ 
/* 543 */     this.consumer.add(createEndDocument(doc));
/*     */   }
/*     */ 
/*     */   public StartDocument createStartDocument(Document doc)
/*     */   {
/* 556 */     String encoding = doc.getXMLEncoding();
/*     */ 
/* 558 */     if (encoding != null) {
/* 559 */       return this.factory.createStartDocument(encoding);
/*     */     }
/* 561 */     return this.factory.createStartDocument();
/*     */   }
/*     */ 
/*     */   public EndDocument createEndDocument(Document doc)
/*     */   {
/* 575 */     return this.factory.createEndDocument();
/*     */   }
/*     */ 
/*     */   public javax.xml.namespace.QName createQName(org.dom4j.QName qname)
/*     */   {
/* 588 */     return new javax.xml.namespace.QName(qname.getNamespaceURI(), qname.getName(), qname.getNamespacePrefix());
/*     */   }
/*     */ 
/*     */   private class NamespaceIterator
/*     */     implements Iterator
/*     */   {
/*     */     private Iterator iter;
/*     */ 
/*     */     public NamespaceIterator(Iterator iter)
/*     */     {
/* 629 */       this.iter = iter;
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 633 */       return this.iter.hasNext();
/*     */     }
/*     */ 
/*     */     public Object next() {
/* 637 */       org.dom4j.Namespace ns = (org.dom4j.Namespace)this.iter.next();
/* 638 */       String prefix = ns.getPrefix();
/* 639 */       String nsURI = ns.getURI();
/*     */ 
/* 641 */       return STAXEventWriter.this.factory.createNamespace(prefix, nsURI);
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 645 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class AttributeIterator
/*     */     implements Iterator
/*     */   {
/*     */     private Iterator iter;
/*     */ 
/*     */     public AttributeIterator(Iterator iter)
/*     */     {
/* 601 */       this.iter = iter;
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 605 */       return this.iter.hasNext();
/*     */     }
/*     */ 
/*     */     public Object next() {
/* 609 */       org.dom4j.Attribute attr = (org.dom4j.Attribute)this.iter.next();
/* 610 */       javax.xml.namespace.QName attrName = STAXEventWriter.this.createQName(attr.getQName());
/* 611 */       String value = attr.getValue();
/*     */ 
/* 613 */       return STAXEventWriter.this.factory.createAttribute(attrName, value);
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 617 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.STAXEventWriter
 * JD-Core Version:    0.6.2
 */