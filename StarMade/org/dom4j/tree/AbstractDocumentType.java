package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Visitor;

public abstract class AbstractDocumentType
  extends AbstractNode
  implements DocumentType
{
  public short getNodeType()
  {
    return 10;
  }
  
  public String getName()
  {
    return getElementName();
  }
  
  public void setName(String name)
  {
    setElementName(name);
  }
  
  public String getPath(Element context)
  {
    return "";
  }
  
  public String getUniquePath(Element context)
  {
    return "";
  }
  
  public String getText()
  {
    List list = getInternalDeclarations();
    if ((list != null) && (list.size() > 0))
    {
      StringBuffer buffer = new StringBuffer();
      Iterator iter = list.iterator();
      if (iter.hasNext())
      {
        Object decl = iter.next();
        buffer.append(decl.toString());
        while (iter.hasNext())
        {
          decl = iter.next();
          buffer.append("\n");
          buffer.append(decl.toString());
        }
      }
      return buffer.toString();
    }
    return "";
  }
  
  public String toString()
  {
    return super.toString() + " [DocumentType: " + asXML() + "]";
  }
  
  public String asXML()
  {
    StringBuffer buffer = new StringBuffer("<!DOCTYPE ");
    buffer.append(getElementName());
    boolean hasPublicID = false;
    String publicID = getPublicID();
    if ((publicID != null) && (publicID.length() > 0))
    {
      buffer.append(" PUBLIC \"");
      buffer.append(publicID);
      buffer.append("\"");
      hasPublicID = true;
    }
    String systemID = getSystemID();
    if ((systemID != null) && (systemID.length() > 0))
    {
      if (!hasPublicID) {
        buffer.append(" SYSTEM");
      }
      buffer.append(" \"");
      buffer.append(systemID);
      buffer.append("\"");
    }
    buffer.append(">");
    return buffer.toString();
  }
  
  public void write(Writer writer)
    throws IOException
  {
    writer.write("<!DOCTYPE ");
    writer.write(getElementName());
    boolean hasPublicID = false;
    String publicID = getPublicID();
    if ((publicID != null) && (publicID.length() > 0))
    {
      writer.write(" PUBLIC \"");
      writer.write(publicID);
      writer.write("\"");
      hasPublicID = true;
    }
    String systemID = getSystemID();
    if ((systemID != null) && (systemID.length() > 0))
    {
      if (!hasPublicID) {
        writer.write(" SYSTEM");
      }
      writer.write(" \"");
      writer.write(systemID);
      writer.write("\"");
    }
    List list = getInternalDeclarations();
    if ((list != null) && (list.size() > 0))
    {
      writer.write(" [");
      Iterator iter = list.iterator();
      while (iter.hasNext())
      {
        Object decl = iter.next();
        writer.write("\n  ");
        writer.write(decl.toString());
      }
      writer.write("\n]");
    }
    writer.write(">");
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractDocumentType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */