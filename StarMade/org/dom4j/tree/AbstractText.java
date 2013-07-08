package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Text;
import org.dom4j.Visitor;

public abstract class AbstractText
  extends AbstractCharacterData
  implements Text
{
  public short getNodeType()
  {
    return 3;
  }
  
  public String toString()
  {
    return super.toString() + " [Text: \"" + getText() + "\"]";
  }
  
  public String asXML()
  {
    return getText();
  }
  
  public void write(Writer writer)
    throws IOException
  {
    writer.write(getText());
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */