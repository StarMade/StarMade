package org.dom4j.tree;

import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightComment
  extends AbstractComment
  implements Comment
{
  protected String text;
  
  public FlyweightComment(String text)
  {
    this.text = text;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  protected Node createXPathResult(Element parent)
  {
    return new DefaultComment(parent, getText());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.FlyweightComment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */