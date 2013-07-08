package org.apache.ws.commons.serialize;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XMLWriterImpl
  implements XMLWriter
{
  private static final int STATE_OUTSIDE = 0;
  private static final int STATE_IN_START_ELEMENT = 1;
  private static final int STATE_IN_ELEMENT = 2;
  private String encoding;
  private String indentString;
  private String lineFeed;
  private Writer field_423;
  private Locator field_424;
  private Map delayedPrefixes;
  int curIndent = 0;
  private int state;
  private boolean declarating;
  private boolean indenting;
  private boolean flushing;
  
  public void setEncoding(String pEncoding)
  {
    this.encoding = pEncoding;
  }
  
  public String getEncoding()
  {
    return this.encoding;
  }
  
  public void setDeclarating(boolean pDeclarating)
  {
    this.declarating = pDeclarating;
  }
  
  public boolean isDeclarating()
  {
    return this.declarating;
  }
  
  public void setIndenting(boolean pIndenting)
  {
    this.indenting = pIndenting;
  }
  
  public boolean isIndenting()
  {
    return this.indenting;
  }
  
  public void setIndentString(String pIndentString)
  {
    this.indentString = pIndentString;
  }
  
  public String getIndentString()
  {
    return this.indentString;
  }
  
  public void setLineFeed(String pLineFeed)
  {
    this.lineFeed = pLineFeed;
  }
  
  public String getLineFeed()
  {
    return this.lineFeed;
  }
  
  public void setFlushing(boolean pFlushing)
  {
    this.flushing = pFlushing;
  }
  
  public boolean isFlushing()
  {
    return this.flushing;
  }
  
  public void setWriter(Writer pWriter)
  {
    this.field_423 = pWriter;
  }
  
  public Writer getWriter()
  {
    return this.field_423;
  }
  
  public void setDocumentLocator(Locator pLocator)
  {
    this.field_424 = pLocator;
  }
  
  public Locator getDocumentLocator()
  {
    return this.field_424;
  }
  
  public void startPrefixMapping(String prefix, String namespaceURI)
    throws SAXException
  {
    if (this.delayedPrefixes == null) {
      this.delayedPrefixes = new HashMap();
    }
    if ("".equals(prefix))
    {
      if (namespaceURI.equals(prefix)) {
        return;
      }
      prefix = "xmlns";
    }
    else
    {
      prefix = "xmlns:" + prefix;
    }
    this.delayedPrefixes.put(prefix, namespaceURI);
  }
  
  public void endPrefixMapping(String prefix)
    throws SAXException
  {
    if (this.delayedPrefixes != null)
    {
      if ("".equals(prefix)) {
        prefix = "xmlns";
      } else {
        prefix = "xmlns:" + prefix;
      }
      this.delayedPrefixes.remove(prefix);
    }
  }
  
  public void startDocument()
    throws SAXException
  {
    if (this.delayedPrefixes != null) {
      this.delayedPrefixes.clear();
    }
    this.state = 0;
    this.curIndent = 0;
    if ((isDeclarating()) && (this.field_423 != null)) {
      try
      {
        this.field_423.write("<?xml version=\"1.0\"");
        String enc = getEncoding();
        if (enc != null)
        {
          this.field_423.write(" encoding=\"");
          this.field_423.write(enc);
          this.field_423.write("\"");
        }
        this.field_423.write("?>");
        if (isIndenting())
        {
          String local_lf = getLineFeed();
          if (local_lf != null) {
            this.field_423.write(local_lf);
          }
        }
      }
      catch (IOException enc)
      {
        throw new SAXException("Failed to write XML declaration: " + enc.getMessage(), enc);
      }
    }
  }
  
  public void endDocument()
    throws SAXException
  {
    if ((isFlushing()) && (this.field_423 != null)) {
      try
      {
        this.field_423.flush();
      }
      catch (IOException local_e)
      {
        throw new SAXException("Failed to flush target writer: " + local_e.getMessage(), local_e);
      }
    }
  }
  
  public void ignorableWhitespace(char[] local_ch, int start, int length)
    throws SAXException
  {
    characters(local_ch, start, length);
  }
  
  private void stopTerminator()
    throws IOException
  {
    if (this.state == 1)
    {
      if (this.field_423 != null) {
        this.field_423.write(62);
      }
      this.state = 2;
    }
  }
  
  public void characters(char[] local_ch, int start, int length)
    throws SAXException
  {
    try
    {
      stopTerminator();
      if (this.field_423 == null) {
        return;
      }
      int end = start + length;
      for (int local_i = start; local_i < end; local_i++)
      {
        char local_c = local_ch[local_i];
        switch (local_c)
        {
        case '&': 
          this.field_423.write("&amp;");
          break;
        case '<': 
          this.field_423.write("&lt;");
          break;
        case '>': 
          this.field_423.write("&gt;");
          break;
        case '\t': 
        case '\n': 
        case '\r': 
          this.field_423.write(local_c);
          break;
        default: 
          if (canEncode(local_c))
          {
            this.field_423.write(local_c);
          }
          else
          {
            this.field_423.write("&#");
            this.field_423.write(Integer.toString(local_c));
            this.field_423.write(";");
          }
          break;
        }
      }
    }
    catch (IOException end)
    {
      throw new SAXException(end);
    }
  }
  
  public boolean canEncode(char local_c)
  {
    return (local_c == '\n') || ((local_c >= ' ') && (local_c < ''));
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException
  {
    if (isIndenting()) {
      this.curIndent -= 1;
    }
    if (this.field_423 != null) {
      try
      {
        if (this.state == 1)
        {
          this.field_423.write("/>");
          this.state = 0;
        }
        else
        {
          if (this.state == 0) {
            indentMe();
          }
          this.field_423.write("</");
          this.field_423.write(qName);
          this.field_423.write(62);
        }
        this.state = 0;
      }
      catch (IOException local_e)
      {
        throw new SAXException(local_e);
      }
    }
  }
  
  private void indentMe()
    throws IOException
  {
    if ((this.field_423 != null) && (isIndenting()))
    {
      String local_s = getLineFeed();
      if (local_s != null) {
        this.field_423.write(local_s);
      }
      local_s = getIndentString();
      if (local_s != null) {
        for (int local_i = 0; local_i < this.curIndent; local_i++) {
          this.field_423.write(local_s);
        }
      }
    }
  }
  
  private void writeCData(String local_v)
    throws IOException
  {
    int len = local_v.length();
    for (int local_j = 0; local_j < len; local_j++)
    {
      char local_c = local_v.charAt(local_j);
      switch (local_c)
      {
      case '&': 
        this.field_423.write("&amp;");
        break;
      case '<': 
        this.field_423.write("&lt;");
        break;
      case '>': 
        this.field_423.write("&gt;");
        break;
      case '\'': 
        this.field_423.write("&apos;");
        break;
      case '"': 
        this.field_423.write("&quot;");
        break;
      default: 
        if (canEncode(local_c))
        {
          this.field_423.write(local_c);
        }
        else
        {
          this.field_423.write("&#");
          this.field_423.write(Integer.toString(local_c));
          this.field_423.write(59);
        }
        break;
      }
    }
  }
  
  public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
    throws SAXException
  {
    try
    {
      stopTerminator();
      if (isIndenting())
      {
        if (this.curIndent > 0) {
          indentMe();
        }
        this.curIndent += 1;
      }
      if (this.field_423 != null)
      {
        this.field_423.write(60);
        this.field_423.write(qName);
        if (attr != null)
        {
          int local_i = attr.getLength();
          while (local_i > 0)
          {
            this.field_423.write(32);
            String name = attr.getQName(--local_i);
            this.field_423.write(name);
            if (this.delayedPrefixes != null) {
              this.delayedPrefixes.remove(name);
            }
            this.field_423.write("=\"");
            writeCData(attr.getValue(local_i));
            this.field_423.write(34);
          }
        }
        if ((this.delayedPrefixes != null) && (this.delayedPrefixes.size() > 0))
        {
          Iterator local_i = this.delayedPrefixes.entrySet().iterator();
          while (local_i.hasNext())
          {
            Map.Entry name = (Map.Entry)local_i.next();
            this.field_423.write(32);
            this.field_423.write((String)name.getKey());
            this.field_423.write("=\"");
            this.field_423.write((String)name.getValue());
            this.field_423.write(34);
          }
          this.delayedPrefixes.clear();
        }
      }
      this.state = 1;
    }
    catch (IOException local_i)
    {
      throw new SAXException(local_i);
    }
  }
  
  public void skippedEntity(String ent)
    throws SAXException
  {
    throw new SAXException("Don't know how to skip entities");
  }
  
  public void processingInstruction(String target, String data)
    throws SAXException
  {
    try
    {
      stopTerminator();
      if (this.field_423 != null)
      {
        this.field_423.write("<?");
        this.field_423.write(target);
        this.field_423.write(32);
        this.field_423.write(data);
        this.field_423.write("?>");
      }
    }
    catch (IOException local_e)
    {
      throw new SAXException(local_e);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.serialize.XMLWriterImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */