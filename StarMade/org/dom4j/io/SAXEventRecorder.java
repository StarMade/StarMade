package org.dom4j.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class SAXEventRecorder
  extends DefaultHandler
  implements LexicalHandler, DeclHandler, DTDHandler, Externalizable
{
  public static final long serialVersionUID = 1L;
  private static final byte STRING = 0;
  private static final byte OBJECT = 1;
  private static final byte NULL = 2;
  private List events = new ArrayList();
  private Map prefixMappings = new HashMap();
  private static final String XMLNS = "xmlns";
  private static final String EMPTY_STRING = "";
  
  public void replay(ContentHandler handler)
    throws SAXException
  {
    Iterator itr = this.events.iterator();
    while (itr.hasNext())
    {
      SAXEvent saxEvent = (SAXEvent)itr.next();
      switch (saxEvent.event)
      {
      case 1: 
        handler.processingInstruction((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
        break;
      case 2: 
        handler.startPrefixMapping((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
        break;
      case 3: 
        handler.endPrefixMapping((String)saxEvent.getParm(0));
        break;
      case 4: 
        handler.startDocument();
        break;
      case 5: 
        handler.endDocument();
        break;
      case 6: 
        AttributesImpl attributes = new AttributesImpl();
        List attParmList = (List)saxEvent.getParm(3);
        if (attParmList != null)
        {
          Iterator attsItr = attParmList.iterator();
          while (attsItr.hasNext())
          {
            String[] attParms = (String[])attsItr.next();
            attributes.addAttribute(attParms[0], attParms[1], attParms[2], attParms[3], attParms[4]);
          }
        }
        handler.startElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), attributes);
        break;
      case 7: 
        handler.endElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
        break;
      case 8: 
        char[] attsItr = (char[])saxEvent.getParm(0);
        int attParms = ((Integer)saxEvent.getParm(1)).intValue();
        int end = ((Integer)saxEvent.getParm(2)).intValue();
        handler.characters(attsItr, attParms, end);
        break;
      case 9: 
        ((LexicalHandler)handler).startDTD((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
        break;
      case 10: 
        ((LexicalHandler)handler).endDTD();
        break;
      case 11: 
        ((LexicalHandler)handler).startEntity((String)saxEvent.getParm(0));
        break;
      case 12: 
        ((LexicalHandler)handler).endEntity((String)saxEvent.getParm(0));
        break;
      case 13: 
        ((LexicalHandler)handler).startCDATA();
        break;
      case 14: 
        ((LexicalHandler)handler).endCDATA();
        break;
      case 15: 
        char[] cchars = (char[])saxEvent.getParm(0);
        int cstart = ((Integer)saxEvent.getParm(1)).intValue();
        int cend = ((Integer)saxEvent.getParm(2)).intValue();
        ((LexicalHandler)handler).comment(cchars, cstart, cend);
        break;
      case 16: 
        ((DeclHandler)handler).elementDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
        break;
      case 17: 
        ((DeclHandler)handler).attributeDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), (String)saxEvent.getParm(3), (String)saxEvent.getParm(4));
        break;
      case 18: 
        ((DeclHandler)handler).internalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
        break;
      case 19: 
        ((DeclHandler)handler).externalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
        break;
      default: 
        throw new SAXException("Unrecognized event: " + saxEvent.event);
      }
    }
  }
  
  public void processingInstruction(String target, String data)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)1);
    saxEvent.addParm(target);
    saxEvent.addParm(data);
    this.events.add(saxEvent);
  }
  
  public void startPrefixMapping(String prefix, String uri)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)2);
    saxEvent.addParm(prefix);
    saxEvent.addParm(uri);
    this.events.add(saxEvent);
  }
  
  public void endPrefixMapping(String prefix)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)3);
    saxEvent.addParm(prefix);
    this.events.add(saxEvent);
  }
  
  public void startDocument()
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)4);
    this.events.add(saxEvent);
  }
  
  public void endDocument()
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)5);
    this.events.add(saxEvent);
  }
  
  public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)6);
    saxEvent.addParm(namespaceURI);
    saxEvent.addParm(localName);
    saxEvent.addParm(qualifiedName);
    QName qName = null;
    if (namespaceURI != null) {
      qName = new QName(localName, Namespace.get(namespaceURI));
    } else {
      qName = new QName(localName);
    }
    if ((attributes != null) && (attributes.getLength() > 0))
    {
      List attParmList = new ArrayList(attributes.getLength());
      String[] attParms = null;
      for (int local_i = 0; local_i < attributes.getLength(); local_i++)
      {
        String attLocalName = attributes.getLocalName(local_i);
        if (attLocalName.startsWith("xmlns"))
        {
          String prefix = null;
          if (attLocalName.length() > 5) {
            prefix = attLocalName.substring(6);
          } else {
            prefix = "";
          }
          SAXEvent prefixEvent = new SAXEvent((byte)2);
          prefixEvent.addParm(prefix);
          prefixEvent.addParm(attributes.getValue(local_i));
          this.events.add(prefixEvent);
          List prefixes = (List)this.prefixMappings.get(qName);
          if (prefixes == null)
          {
            prefixes = new ArrayList();
            this.prefixMappings.put(qName, prefixes);
          }
          prefixes.add(prefix);
        }
        else
        {
          attParms = new String[5];
          attParms[0] = attributes.getURI(local_i);
          attParms[1] = attLocalName;
          attParms[2] = attributes.getQName(local_i);
          attParms[3] = attributes.getType(local_i);
          attParms[4] = attributes.getValue(local_i);
          attParmList.add(attParms);
        }
      }
      saxEvent.addParm(attParmList);
    }
    this.events.add(saxEvent);
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)7);
    saxEvent.addParm(namespaceURI);
    saxEvent.addParm(localName);
    saxEvent.addParm(qName);
    this.events.add(saxEvent);
    QName elementName = null;
    if (namespaceURI != null) {
      elementName = new QName(localName, Namespace.get(namespaceURI));
    } else {
      elementName = new QName(localName);
    }
    List prefixes = (List)this.prefixMappings.get(elementName);
    if (prefixes != null)
    {
      Iterator itr = prefixes.iterator();
      while (itr.hasNext())
      {
        SAXEvent prefixEvent = new SAXEvent((byte)3);
        prefixEvent.addParm(itr.next());
        this.events.add(prefixEvent);
      }
    }
  }
  
  public void characters(char[] local_ch, int start, int end)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)8);
    saxEvent.addParm(local_ch);
    saxEvent.addParm(new Integer(start));
    saxEvent.addParm(new Integer(end));
    this.events.add(saxEvent);
  }
  
  public void startDTD(String name, String publicId, String systemId)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)9);
    saxEvent.addParm(name);
    saxEvent.addParm(publicId);
    saxEvent.addParm(systemId);
    this.events.add(saxEvent);
  }
  
  public void endDTD()
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)10);
    this.events.add(saxEvent);
  }
  
  public void startEntity(String name)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)11);
    saxEvent.addParm(name);
    this.events.add(saxEvent);
  }
  
  public void endEntity(String name)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)12);
    saxEvent.addParm(name);
    this.events.add(saxEvent);
  }
  
  public void startCDATA()
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)13);
    this.events.add(saxEvent);
  }
  
  public void endCDATA()
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)14);
    this.events.add(saxEvent);
  }
  
  public void comment(char[] local_ch, int start, int end)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)15);
    saxEvent.addParm(local_ch);
    saxEvent.addParm(new Integer(start));
    saxEvent.addParm(new Integer(end));
    this.events.add(saxEvent);
  }
  
  public void elementDecl(String name, String model)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)16);
    saxEvent.addParm(name);
    saxEvent.addParm(model);
    this.events.add(saxEvent);
  }
  
  public void attributeDecl(String eName, String aName, String type, String valueDefault, String value)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)17);
    saxEvent.addParm(eName);
    saxEvent.addParm(aName);
    saxEvent.addParm(type);
    saxEvent.addParm(valueDefault);
    saxEvent.addParm(value);
    this.events.add(saxEvent);
  }
  
  public void internalEntityDecl(String name, String value)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)18);
    saxEvent.addParm(name);
    saxEvent.addParm(value);
    this.events.add(saxEvent);
  }
  
  public void externalEntityDecl(String name, String publicId, String sysId)
    throws SAXException
  {
    SAXEvent saxEvent = new SAXEvent((byte)19);
    saxEvent.addParm(name);
    saxEvent.addParm(publicId);
    saxEvent.addParm(sysId);
    this.events.add(saxEvent);
  }
  
  public void writeExternal(ObjectOutput out)
    throws IOException
  {
    if (this.events == null)
    {
      out.writeByte(2);
    }
    else
    {
      out.writeByte(1);
      out.writeObject(this.events);
    }
  }
  
  public void readExternal(ObjectInput local_in)
    throws ClassNotFoundException, IOException
  {
    if (local_in.readByte() != 2) {
      this.events = ((List)local_in.readObject());
    }
  }
  
  static class SAXEvent
    implements Externalizable
  {
    public static final long serialVersionUID = 1L;
    static final byte PROCESSING_INSTRUCTION = 1;
    static final byte START_PREFIX_MAPPING = 2;
    static final byte END_PREFIX_MAPPING = 3;
    static final byte START_DOCUMENT = 4;
    static final byte END_DOCUMENT = 5;
    static final byte START_ELEMENT = 6;
    static final byte END_ELEMENT = 7;
    static final byte CHARACTERS = 8;
    static final byte START_DTD = 9;
    static final byte END_DTD = 10;
    static final byte START_ENTITY = 11;
    static final byte END_ENTITY = 12;
    static final byte START_CDATA = 13;
    static final byte END_CDATA = 14;
    static final byte COMMENT = 15;
    static final byte ELEMENT_DECL = 16;
    static final byte ATTRIBUTE_DECL = 17;
    static final byte INTERNAL_ENTITY_DECL = 18;
    static final byte EXTERNAL_ENTITY_DECL = 19;
    protected byte event;
    protected List parms;
    
    public SAXEvent() {}
    
    SAXEvent(byte event)
    {
      this.event = event;
    }
    
    void addParm(Object parm)
    {
      if (this.parms == null) {
        this.parms = new ArrayList(3);
      }
      this.parms.add(parm);
    }
    
    Object getParm(int index)
    {
      if ((this.parms != null) && (index < this.parms.size())) {
        return this.parms.get(index);
      }
      return null;
    }
    
    public void writeExternal(ObjectOutput out)
      throws IOException
    {
      out.writeByte(this.event);
      if (this.parms == null)
      {
        out.writeByte(2);
      }
      else
      {
        out.writeByte(1);
        out.writeObject(this.parms);
      }
    }
    
    public void readExternal(ObjectInput local_in)
      throws ClassNotFoundException, IOException
    {
      this.event = local_in.readByte();
      if (local_in.readByte() != 2) {
        this.parms = ((List)local_in.readObject());
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.SAXEventRecorder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */