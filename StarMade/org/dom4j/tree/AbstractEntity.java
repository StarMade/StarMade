package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Visitor;

public abstract class AbstractEntity
  extends AbstractNode
  implements Entity
{
  public short getNodeType()
  {
    return 5;
  }
  
  public String getPath(Element context)
  {
    Element parent = getParent();
    return (parent != null) && (parent != context) ? parent.getPath(context) + "/text()" : "text()";
  }
  
  public String getUniquePath(Element context)
  {
    Element parent = getParent();
    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/text()" : "text()";
  }
  
  public String toString()
  {
    return super.toString() + " [Entity: &" + getName() + ";]";
  }
  
  public String getStringValue()
  {
    return "&" + getName() + ";";
  }
  
  public String asXML()
  {
    return "&" + getName() + ";";
  }
  
  public void write(Writer writer)
    throws IOException
  {
    writer.write("&");
    writer.write(getName());
    writer.write(";");
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */