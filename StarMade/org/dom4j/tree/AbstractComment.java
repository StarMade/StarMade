package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Visitor;

public abstract class AbstractComment
  extends AbstractCharacterData
  implements Comment
{
  public short getNodeType()
  {
    return 8;
  }
  
  public String getPath(Element context)
  {
    Element parent = getParent();
    return (parent != null) && (parent != context) ? parent.getPath(context) + "/comment()" : "comment()";
  }
  
  public String getUniquePath(Element context)
  {
    Element parent = getParent();
    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/comment()" : "comment()";
  }
  
  public String toString()
  {
    return super.toString() + " [Comment: \"" + getText() + "\"]";
  }
  
  public String asXML()
  {
    return "<!--" + getText() + "-->";
  }
  
  public void write(Writer writer)
    throws IOException
  {
    writer.write("<!--");
    writer.write(getText());
    writer.write("-->");
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractComment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */