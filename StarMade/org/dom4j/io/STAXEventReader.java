package org.dom4j.io;

import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.dom4j.CharacterData;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Node;

public class STAXEventReader
{
  private DocumentFactory factory;
  private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
  
  public STAXEventReader()
  {
    this.factory = DocumentFactory.getInstance();
  }
  
  public STAXEventReader(DocumentFactory factory)
  {
    if (factory != null) {
      this.factory = factory;
    } else {
      this.factory = DocumentFactory.getInstance();
    }
  }
  
  public void setDocumentFactory(DocumentFactory documentFactory)
  {
    if (documentFactory != null) {
      this.factory = documentFactory;
    } else {
      this.factory = DocumentFactory.getInstance();
    }
  }
  
  public Document readDocument(InputStream local_is)
    throws XMLStreamException
  {
    return readDocument(local_is, null);
  }
  
  public Document readDocument(Reader reader)
    throws XMLStreamException
  {
    return readDocument(reader, null);
  }
  
  public Document readDocument(InputStream local_is, String systemId)
    throws XMLStreamException
  {
    XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, local_is);
    try
    {
      Document localDocument = readDocument(eventReader);
      return localDocument;
    }
    finally
    {
      eventReader.close();
    }
  }
  
  public Document readDocument(Reader reader, String systemId)
    throws XMLStreamException
  {
    XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, reader);
    try
    {
      Document localDocument = readDocument(eventReader);
      return localDocument;
    }
    finally
    {
      eventReader.close();
    }
  }
  
  public Node readNode(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if (event.isStartElement()) {
      return readElement(reader);
    }
    if (event.isCharacters()) {
      return readCharacters(reader);
    }
    if (event.isStartDocument()) {
      return readDocument(reader);
    }
    if (event.isProcessingInstruction()) {
      return readProcessingInstruction(reader);
    }
    if (event.isEntityReference()) {
      return readEntityReference(reader);
    }
    if (event.isAttribute()) {
      return readAttribute(reader);
    }
    if (event.isNamespace()) {
      return readNamespace(reader);
    }
    throw new XMLStreamException("Unsupported event: " + event);
  }
  
  public Document readDocument(XMLEventReader reader)
    throws XMLStreamException
  {
    Document doc = null;
    while (reader.hasNext())
    {
      XMLEvent nextEvent = reader.peek();
      int type = nextEvent.getEventType();
      switch (type)
      {
      case 7: 
        StartDocument event = (StartDocument)reader.nextEvent();
        if (doc == null)
        {
          if (event.encodingSet())
          {
            String encodingScheme = event.getCharacterEncodingScheme();
            doc = this.factory.createDocument(encodingScheme);
          }
          else
          {
            doc = this.factory.createDocument();
          }
        }
        else
        {
          String encodingScheme = "Unexpected StartDocument event";
          throw new XMLStreamException(encodingScheme, event.getLocation());
        }
        break;
      case 4: 
      case 6: 
      case 8: 
        reader.nextEvent();
        break;
      case 5: 
      default: 
        if (doc == null) {
          doc = this.factory.createDocument();
        }
        Node encodingScheme = readNode(reader);
        doc.add(encodingScheme);
      }
    }
    return doc;
  }
  
  public Element readElement(XMLEventReader eventReader)
    throws XMLStreamException
  {
    XMLEvent event = eventReader.peek();
    if (event.isStartElement())
    {
      StartElement startTag = eventReader.nextEvent().asStartElement();
      Element elem = createElement(startTag);
      for (;;)
      {
        if (!eventReader.hasNext())
        {
          String msg = "Unexpected end of stream while reading element content";
          throw new XMLStreamException(msg);
        }
        XMLEvent msg = eventReader.peek();
        if (msg.isEndElement())
        {
          EndElement endElem = eventReader.nextEvent().asEndElement();
          if (endElem.getName().equals(startTag.getName())) {
            break;
          }
          throw new XMLStreamException("Expected " + startTag.getName() + " end-tag, but found" + endElem.getName());
        }
        Node endElem = readNode(eventReader);
        elem.add(endElem);
      }
      return elem;
    }
    throw new XMLStreamException("Expected Element event, found: " + event);
  }
  
  public org.dom4j.Attribute readAttribute(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if (event.isAttribute())
    {
      javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)reader.nextEvent();
      return createAttribute(null, attr);
    }
    throw new XMLStreamException("Expected Attribute event, found: " + event);
  }
  
  public org.dom4j.Namespace readNamespace(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if (event.isNamespace())
    {
      javax.xml.stream.events.Namespace local_ns = (javax.xml.stream.events.Namespace)reader.nextEvent();
      return createNamespace(local_ns);
    }
    throw new XMLStreamException("Expected Namespace event, found: " + event);
  }
  
  public CharacterData readCharacters(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if (event.isCharacters())
    {
      Characters characters = reader.nextEvent().asCharacters();
      return createCharacterData(characters);
    }
    throw new XMLStreamException("Expected Characters event, found: " + event);
  }
  
  public org.dom4j.Comment readComment(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if ((event instanceof javax.xml.stream.events.Comment)) {
      return createComment((javax.xml.stream.events.Comment)reader.nextEvent());
    }
    throw new XMLStreamException("Expected Comment event, found: " + event);
  }
  
  public Entity readEntityReference(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if (event.isEntityReference())
    {
      EntityReference entityRef = (EntityReference)reader.nextEvent();
      return createEntity(entityRef);
    }
    throw new XMLStreamException("Expected EntityRef event, found: " + event);
  }
  
  public org.dom4j.ProcessingInstruction readProcessingInstruction(XMLEventReader reader)
    throws XMLStreamException
  {
    XMLEvent event = reader.peek();
    if (event.isProcessingInstruction())
    {
      javax.xml.stream.events.ProcessingInstruction local_pi = (javax.xml.stream.events.ProcessingInstruction)reader.nextEvent();
      return createProcessingInstruction(local_pi);
    }
    throw new XMLStreamException("Expected PI event, found: " + event);
  }
  
  public Element createElement(StartElement startEvent)
  {
    javax.xml.namespace.QName qname = startEvent.getName();
    org.dom4j.QName elemName = createQName(qname);
    Element elem = this.factory.createElement(elemName);
    Iterator local_i = startEvent.getAttributes();
    while (local_i.hasNext())
    {
      javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)local_i.next();
      elem.addAttribute(createQName(attr.getName()), attr.getValue());
    }
    Iterator local_i = startEvent.getNamespaces();
    while (local_i.hasNext())
    {
      javax.xml.stream.events.Namespace attr = (javax.xml.stream.events.Namespace)local_i.next();
      elem.addNamespace(attr.getPrefix(), attr.getNamespaceURI());
    }
    return elem;
  }
  
  public org.dom4j.Attribute createAttribute(Element elem, javax.xml.stream.events.Attribute attr)
  {
    return this.factory.createAttribute(elem, createQName(attr.getName()), attr.getValue());
  }
  
  public org.dom4j.Namespace createNamespace(javax.xml.stream.events.Namespace local_ns)
  {
    return this.factory.createNamespace(local_ns.getPrefix(), local_ns.getNamespaceURI());
  }
  
  public CharacterData createCharacterData(Characters characters)
  {
    String data = characters.getData();
    if (characters.isCData()) {
      return this.factory.createCDATA(data);
    }
    return this.factory.createText(data);
  }
  
  public org.dom4j.Comment createComment(javax.xml.stream.events.Comment comment)
  {
    return this.factory.createComment(comment.getText());
  }
  
  public Entity createEntity(EntityReference entityRef)
  {
    return this.factory.createEntity(entityRef.getName(), entityRef.getDeclaration().getReplacementText());
  }
  
  public org.dom4j.ProcessingInstruction createProcessingInstruction(javax.xml.stream.events.ProcessingInstruction local_pi)
  {
    return this.factory.createProcessingInstruction(local_pi.getTarget(), local_pi.getData());
  }
  
  public org.dom4j.QName createQName(javax.xml.namespace.QName qname)
  {
    return this.factory.createQName(qname.getLocalPart(), qname.getPrefix(), qname.getNamespaceURI());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.STAXEventReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */