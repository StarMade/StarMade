package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.dom4j.CDATA;
import org.dom4j.Visitor;

public abstract class AbstractCDATA
  extends AbstractCharacterData
  implements CDATA
{
  public short getNodeType()
  {
    return 4;
  }
  
  public String toString()
  {
    return super.toString() + " [CDATA: \"" + getText() + "\"]";
  }
  
  public String asXML()
  {
    StringWriter writer = new StringWriter();
    try
    {
      write(writer);
    }
    catch (IOException local_e) {}
    return writer.toString();
  }
  
  public void write(Writer writer)
    throws IOException
  {
    writer.write("<![CDATA[");
    if (getText() != null) {
      writer.write(getText());
    }
    writer.write("]]>");
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractCDATA
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */