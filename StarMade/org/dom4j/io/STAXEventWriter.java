package org.dom4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.util.XMLEventConsumer;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Node;
import org.dom4j.Text;

public class STAXEventWriter
{
  private XMLEventConsumer consumer;
  private XMLEventFactory factory = XMLEventFactory.newInstance();
  private XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
  
  public STAXEventWriter() {}
  
  public STAXEventWriter(File file)
    throws XMLStreamException, IOException
  {
    this.consumer = this.outputFactory.createXMLEventWriter(new FileWriter(file));
  }
  
  public STAXEventWriter(Writer writer)
    throws XMLStreamException
  {
    this.consumer = this.outputFactory.createXMLEventWriter(writer);
  }
  
  public STAXEventWriter(OutputStream stream)
    throws XMLStreamException
  {
    this.consumer = this.outputFactory.createXMLEventWriter(stream);
  }
  
  public STAXEventWriter(XMLEventConsumer consumer)
  {
    this.consumer = consumer;
  }
  
  public XMLEventConsumer getConsumer()
  {
    return this.consumer;
  }
  
  public void setConsumer(XMLEventConsumer consumer)
  {
    this.consumer = consumer;
  }
  
  public XMLEventFactory getEventFactory()
  {
    return this.factory;
  }
  
  public void setEventFactory(XMLEventFactory eventFactory)
  {
    this.factory = eventFactory;
  }
  
  public void writeNode(Node local_n)
    throws XMLStreamException
  {
    switch (local_n.getNodeType())
    {
    case 1: 
      writeElement((Element)local_n);
      break;
    case 3: 
      writeText((Text)local_n);
      break;
    case 2: 
      writeAttribute((org.dom4j.Attribute)local_n);
      break;
    case 13: 
      writeNamespace((org.dom4j.Namespace)local_n);
      break;
    case 8: 
      writeComment((org.dom4j.Comment)local_n);
      break;
    case 4: 
      writeCDATA((CDATA)local_n);
      break;
    case 7: 
      writeProcessingInstruction((org.dom4j.ProcessingInstruction)local_n);
      break;
    case 5: 
      writeEntity((Entity)local_n);
      break;
    case 9: 
      writeDocument((Document)local_n);
      break;
    case 10: 
      writeDocumentType((DocumentType)local_n);
      break;
    case 6: 
    case 11: 
    case 12: 
    default: 
      throw new XMLStreamException("Unsupported DOM4J Node: " + local_n);
    }
  }
  
  public void writeChildNodes(Branch branch)
    throws XMLStreamException
  {
    int local_i = 0;
    int local_s = branch.nodeCount();
    while (local_i < local_s)
    {
      Node local_n = branch.node(local_i);
      writeNode(local_n);
      local_i++;
    }
  }
  
  public void writeElement(Element elem)
    throws XMLStreamException
  {
    this.consumer.add(createStartElement(elem));
    writeChildNodes(elem);
    this.consumer.add(createEndElement(elem));
  }
  
  public StartElement createStartElement(Element elem)
  {
    javax.xml.namespace.QName tagName = createQName(elem.getQName());
    Iterator attrIter = new AttributeIterator(elem.attributeIterator());
    Iterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
    return this.factory.createStartElement(tagName, attrIter, nsIter);
  }
  
  public EndElement createEndElement(Element elem)
  {
    javax.xml.namespace.QName tagName = createQName(elem.getQName());
    Iterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
    return this.factory.createEndElement(tagName, nsIter);
  }
  
  public void writeAttribute(org.dom4j.Attribute attr)
    throws XMLStreamException
  {
    this.consumer.add(createAttribute(attr));
  }
  
  public javax.xml.stream.events.Attribute createAttribute(org.dom4j.Attribute attr)
  {
    javax.xml.namespace.QName attrName = createQName(attr.getQName());
    String value = attr.getValue();
    return this.factory.createAttribute(attrName, value);
  }
  
  public void writeNamespace(org.dom4j.Namespace local_ns)
    throws XMLStreamException
  {
    this.consumer.add(createNamespace(local_ns));
  }
  
  public javax.xml.stream.events.Namespace createNamespace(org.dom4j.Namespace local_ns)
  {
    String prefix = local_ns.getPrefix();
    String uri = local_ns.getURI();
    return this.factory.createNamespace(prefix, uri);
  }
  
  public void writeText(Text text)
    throws XMLStreamException
  {
    this.consumer.add(createCharacters(text));
  }
  
  public Characters createCharacters(Text text)
  {
    return this.factory.createCharacters(text.getText());
  }
  
  public void writeCDATA(CDATA cdata)
    throws XMLStreamException
  {
    this.consumer.add(createCharacters(cdata));
  }
  
  public Characters createCharacters(CDATA cdata)
  {
    return this.factory.createCData(cdata.getText());
  }
  
  public void writeComment(org.dom4j.Comment comment)
    throws XMLStreamException
  {
    this.consumer.add(createComment(comment));
  }
  
  public javax.xml.stream.events.Comment createComment(org.dom4j.Comment comment)
  {
    return this.factory.createComment(comment.getText());
  }
  
  public void writeProcessingInstruction(org.dom4j.ProcessingInstruction local_pi)
    throws XMLStreamException
  {
    this.consumer.add(createProcessingInstruction(local_pi));
  }
  
  public javax.xml.stream.events.ProcessingInstruction createProcessingInstruction(org.dom4j.ProcessingInstruction local_pi)
  {
    String target = local_pi.getTarget();
    String data = local_pi.getText();
    return this.factory.createProcessingInstruction(target, data);
  }
  
  public void writeEntity(Entity entity)
    throws XMLStreamException
  {
    this.consumer.add(createEntityReference(entity));
  }
  
  private EntityReference createEntityReference(Entity entity)
  {
    return this.factory.createEntityReference(entity.getName(), null);
  }
  
  public void writeDocumentType(DocumentType docType)
    throws XMLStreamException
  {
    this.consumer.add(createDTD(docType));
  }
  
  public DTD createDTD(DocumentType docType)
  {
    StringWriter decl = new StringWriter();
    try
    {
      docType.write(decl);
    }
    catch (IOException local_e)
    {
      throw new RuntimeException("Error writing DTD", local_e);
    }
    return this.factory.createDTD(decl.toString());
  }
  
  public void writeDocument(Document doc)
    throws XMLStreamException
  {
    this.consumer.add(createStartDocument(doc));
    writeChildNodes(doc);
    this.consumer.add(createEndDocument(doc));
  }
  
  public StartDocument createStartDocument(Document doc)
  {
    String encoding = doc.getXMLEncoding();
    if (encoding != null) {
      return this.factory.createStartDocument(encoding);
    }
    return this.factory.createStartDocument();
  }
  
  public EndDocument createEndDocument(Document doc)
  {
    return this.factory.createEndDocument();
  }
  
  public javax.xml.namespace.QName createQName(org.dom4j.QName qname)
  {
    return new javax.xml.namespace.QName(qname.getNamespaceURI(), qname.getName(), qname.getNamespacePrefix());
  }
  
  private class NamespaceIterator
    implements Iterator
  {
    private Iterator iter;
    
    public NamespaceIterator(Iterator iter)
    {
      this.iter = iter;
    }
    
    public boolean hasNext()
    {
      return this.iter.hasNext();
    }
    
    public Object next()
    {
      org.dom4j.Namespace local_ns = (org.dom4j.Namespace)this.iter.next();
      String prefix = local_ns.getPrefix();
      String nsURI = local_ns.getURI();
      return STAXEventWriter.this.factory.createNamespace(prefix, nsURI);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class AttributeIterator
    implements Iterator
  {
    private Iterator iter;
    
    public AttributeIterator(Iterator iter)
    {
      this.iter = iter;
    }
    
    public boolean hasNext()
    {
      return this.iter.hasNext();
    }
    
    public Object next()
    {
      org.dom4j.Attribute attr = (org.dom4j.Attribute)this.iter.next();
      javax.xml.namespace.QName attrName = STAXEventWriter.this.createQName(attr.getQName());
      String value = attr.getValue();
      return STAXEventWriter.this.factory.createAttribute(attrName, value);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.STAXEventWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */